package com.javafx_voltech_cps.cameramonitoringapp;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamMonitoring;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            CamMonitoring monitoring = new CamMonitoring();
            Recorder record = new Recorder();
            record.getCameras().add(new Camera());
            monitoring.setRecorder(record);
            monitoring.start();
            Scene scene = new Scene(monitoring, 1000, 600);
            primaryStage.setTitle("Camera Switching App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir a pilha de exceções se houver um erro
        }
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_videoio_ffmpeg4100_64");
        launch(args); // Iniciar a aplicação JavaFX
    }
}