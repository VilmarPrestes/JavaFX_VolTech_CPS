package com.javafx_voltech_cps.cameramonitoringapp.controller;

import com.javafx_voltech_cps.cameramonitoringapp.model.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.Recorder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPanelController {

    @FXML
    private TextField cameraNomeField;
    @FXML
    private TextField cameraIpField;
    @FXML
    private TextField recorderNomeField;
    @FXML
    private TextField recorderLocalizacaoField;

    @FXML
    private Button addCameraButton;
    @FXML
    private Button addRecorderButton;

    private MainController mainController;

    public AddPanelController() {
        // Pega a instância do MainController
        this.mainController = MainController.getInstance();
    }

    @FXML
    public void initialize() {
        addCameraButton.setOnAction(event -> addCamera());
        addRecorderButton.setOnAction(event -> addRecorder());
    }

    private void addCamera() {
        String nome = cameraNomeField.getText();
        String ip = cameraIpField.getText();

        if (!nome.isEmpty() && !ip.isEmpty()) {
            Camera camera = new Camera(0, nome, ip);
            mainController.addCamera(camera);
            clearFields();
        }
    }

    private void addRecorder() {
        String nome = recorderNomeField.getText();
        String localizacao = recorderLocalizacaoField.getText();

        if (!nome.isEmpty() && !localizacao.isEmpty()) {
            Recorder recorder = new Recorder(0, nome, localizacao);
            mainController.addRecorder(recorder);
            clearFields();
        }
    }

    private void clearFields() {
        cameraNomeField.clear();
        cameraIpField.clear();
        recorderNomeField.clear();
        recorderLocalizacaoField.clear();
    }
}