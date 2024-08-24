package com.javafx_voltech_cps.cameramonitoringapp;

import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamMonitoring;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            List<CamView> camViews = new ArrayList<>();
            camViews.add(new CamView());
            camViews.add(new CamView());
            CamMonitoring monitoring = new CamMonitoring();
            monitoring.setCameras(camViews);
            monitoring.loadFirstCamera(new CamView());
            monitoring.loadCameraGrid();
            Scene scene = new Scene(monitoring, 800, 600);
            primaryStage.setTitle("Camera Switching App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir a pilha de exceções se houver um erro
        }
    }

    public static void main(String[] args) {
        launch(args); // Iniciar a aplicação JavaFX
    }
}