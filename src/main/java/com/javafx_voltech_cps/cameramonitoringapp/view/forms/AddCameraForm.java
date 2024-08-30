package com.javafx_voltech_cps.cameramonitoringapp.view.forms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCameraForm {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtNumberChannels;

    @FXML
    public void initialize() {
    }

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtPassword() {
        return txtPassword;
    }

    public TextField getTxtNumberChannels() {
        return txtNumberChannels;
    }
}
