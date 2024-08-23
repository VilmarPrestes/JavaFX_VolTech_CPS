package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class CamView extends Service<Void> {

	public static final int PLAY = 0;
	public static final int PAUSE = 1;
	public static final int STOP = 3;
	private static final int TIMEOUT_SECONDS = 5;
	private static final String NOT_FOUND_VIDEO = "/com/javafx_voltech_cps/cameramonitoringapp/images/NotFoundVideo.png";

	private Camera camera;
	private boolean isOpen;
	private int state;
	private Image notFoundImage;
	private VideoCapture capture;

	@FXML
	private ImageView view;

	public CamView() {
		isOpen = false;
		state = STOP;
	}

	@FXML
	public void initialize() {
		this.notFoundImage = new Image(getClass().getResourceAsStream(NOT_FOUND_VIDEO));
		view.setImage(notFoundImage);
	}

	public void setCamera(Camera camera) throws Exception {
		this.camera = camera;
		if (isOpen) {

		}
	}

	public boolean play() throws Exception {
		return true;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<>() {
			@Override
			protected Void call() {
				if (isOpen) {
					capture.release();
					isOpen = false;
				}
				ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
				scheduler.schedule(() -> {
					if (!isOpen) {
						capture.release();
						Platform.runLater(() -> {
							view.setImage(notFoundImage);
						});
					}
					scheduler.shutdown();
				}, TIMEOUT_SECONDS, TimeUnit.SECONDS);
				isOpen = capture.open(camera.getSource());
				if (isOpen) {
					Mat frame = new Mat();
					while (capture.read(frame)) {
						try {
							if (frame.empty()) {
								continue;
							}
							Mat rgbImage = new Mat();
							Image image = matToImage(rgbImage);
							Platform.runLater(() -> view.setImage(image));
						} catch (Exception e) {
							messageError(e);
						}
					}
					capture.release();
				}
				return null;
			}
		};
	}
	
	public Image matToImage(Mat mat) throws Exception {
		if (mat.empty()) {
			throw new IllegalArgumentException("The Mat is empty");
		}
		int width = mat.cols();
		int height = mat.rows();
		WritableImage writableImage = new WritableImage(width, height);
		byte[] data = new byte[width * height * (int) mat.elemSize()];
		mat.get(0, 0, data);
		PixelWriter pixelWriter = writableImage.getPixelWriter();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int index = (y * width + x) * (int) mat.elemSize();
				byte b = data[index];
				byte g = data[index + 1];
				byte r = data[index + 2];
				Color color = Color.rgb((r & 0xFF), (g & 0xFF), (b & 0xFF));
				pixelWriter.setColor(x, y, color);
			}
		}
		return writableImage;
	}
	

	public void messageError(Exception e) {
	}
}
