package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.utils.FrameClock;
import javafx.application.Platform;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class CamView extends Service<Void> {

    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int STOP = 3;
    private static final int TIMEOUT_SECONDS = 10;
    private static final String NOT_FOUND_VIDEO = "/com/javafx_voltech_cps/cameramonitoringapp/images/NotFoundVideo.png";
    private static final String FINDING_VIDEO = "/com/javafx_voltech_cps/cameramonitoringapp/images/FindingVideo.png";
    private static final String STOP_VIDEO = "/com/javafx_voltech_cps/cameramonitoringapp/images/StopVideo.png";

    private Camera camera;
    private boolean isOpen;
    private boolean isShow;
    private int videoState;
    private Image notFoundImage;
    private Image findingVideoImage;
    private Image stopVideoImage;
    private VideoCapture capture;
    private int fps = 30;
    private final FrameClock timer;

    private final ImageView view;



    public CamView() {
        isOpen = false;
        view = new ImageView();
        timer = new FrameClock(fps){
            @Override
            public void run(){
                isShow = true;
            }
        };
        videoState = STOP;
        try {
            this.notFoundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(NOT_FOUND_VIDEO)));
            this.stopVideoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(STOP_VIDEO)));
            this.findingVideoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(FINDING_VIDEO)));
            System.out.println("TESTADO");
            view.setImage(findingVideoImage);
        } catch (Exception e) {
            messageError(e);
        }
    }

    public void setCamera(Camera camera) throws Exception {
        this.camera = camera;
        if (videoState == PLAY) {
            stop();
            play();
        }
    }

    public void stop() throws Exception {
        if (videoState != STOP || isRunning()) {
            videoState = STOP;
            timer.shutdown();
            cancel();
            capture.release();
            view.setImage(stopVideoImage);
        }
    }

    public void pause() throws Exception {
        if (videoState != PAUSE) {
            timer.pause();
            videoState = PAUSE;
        }
    }

    public void play() throws Exception {
        if (camera != null) {
            if (videoState != PLAY) {
                videoState = PLAY;
                timer.start();
                start();
            }
        } else {
            throw new Exception("Camera is null");
        }
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                try {
                    while (true) {
                        if (videoState == PLAY) {
                            if (isOpen) {
                                capture.release();
                                isOpen = false;
                            }
                            capture = new VideoCapture();
                            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                            scheduler.schedule(() -> {
                                if (!isOpen) {
                                    Platform.runLater(() -> view.setImage(notFoundImage));
                                    capture.release();
                                }
                            }, TIMEOUT_SECONDS, TimeUnit.SECONDS);
                            isOpen = capture.open(camera.getSource());
                            if (isOpen) {
                                if (!scheduler.isShutdown()) {
                                    scheduler.shutdownNow();
                                }
                                scheduler = Executors.newSingleThreadScheduledExecutor();
                                while ((videoState == PAUSE || videoState == PLAY)) {
                                    Future<?> timeoutTask = scheduler.schedule(() -> {
                                        isOpen = false;
                                        view.setImage(notFoundImage);
                                    }, TIMEOUT_SECONDS, TimeUnit.SECONDS);
                                    if (videoState == PLAY) {
                                        if (!loadFrame()) {
                                            break;
                                        } else {
                                            timeoutTask.cancel(true);
                                        }
                                        Thread.sleep(1000/fps);
                                    } else {
                                        scheduler.shutdownNow();
                                    }
                                }
                                scheduler.shutdown();
                            }
                            capture.release();
                        }
                    }
                } catch (Exception e) {
                    messageError(e);
                }
                return null;
            }
        };
    }

    private boolean loadFrame() throws Exception {
        Mat frame = new Mat();
        if (capture.read(frame)) {
            if (videoState == PLAY && isShow) {
                if (frame.empty()) {
                    return false;
                }
                int newWidth = 640;
                int newHeight = 360;
                Mat resizedFrame = new Mat();
                Imgproc.resize(frame, resizedFrame, new Size(newWidth, newHeight));

                Mat rgbImage = new Mat();
                Imgproc.cvtColor(resizedFrame, rgbImage, Imgproc.COLOR_BGR2RGB);
                Image image = matToImage(rgbImage);
                Platform.runLater(() -> view.setImage(image));
                isShow = false;
            }
            return true;
        }
        return false;
    }

    public Image matToImage(Mat mat) throws Exception {
        if (mat.empty()) {
            throw new IllegalArgumentException("The Mat is empty");
        }

        int width = mat.cols();
        int height = mat.rows();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        if (mat.channels() == 1) { // Escala de cinza
            byte[] data = new byte[width * height];
            mat.get(0, 0, data);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int index = y * width + x;
                    byte value = data[index];
                    Color color = Color.gray((value & 0xFF) / 255.0);
                    pixelWriter.setColor(x, y, color);
                }
            }
        } else if (mat.channels() == 3) { // RGB
            byte[] data = new byte[width * height * 3];
            mat.get(0, 0, data);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int index = (y * width + x) * 3;
                    byte r = data[index];
                    byte g = data[index + 1];
                    byte b = data[index + 2];
                    Color color = Color.rgb((r & 0xFF), (g & 0xFF), (b & 0xFF));
                    pixelWriter.setColor(x, y, color);
                }
            }
        } else {
            throw new IllegalArgumentException("Unsupported number of channels: " + mat.channels());
        }

        return writableImage;
    }

    public void messageError(Exception e) {
        e.printStackTrace();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setVideoState(int videoState) {
        this.videoState = videoState;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
        timer.setFps(this.fps);
    }

    public int getVideoState() {
        return videoState;
    }

    public ImageView getView() {
        return view;
    }
}
