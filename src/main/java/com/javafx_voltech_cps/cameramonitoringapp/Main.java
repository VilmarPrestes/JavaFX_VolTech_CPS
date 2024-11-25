package com.javafx_voltech_cps.cameramonitoringapp;

import com.javafx_voltech_cps.cameramonitoringapp.view.panels.MainWindowPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            MainWindowPanel root = new MainWindowPanel();
            Scene scene = new Scene(root, 1000, 600);
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