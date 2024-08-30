package com.javafx_voltech_cps.cameramonitoringapp.view.forms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRecorderForm {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtNumberChannels;

    @FXML
    public void initialize() {
    }

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtModel() {
        return txtModel;
    }

    public TextField getTxtNumberChannels() {
        return txtNumberChannels;
    }
}
