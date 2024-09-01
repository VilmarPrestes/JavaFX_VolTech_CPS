package com.javafx_voltech_cps.cameramonitoringapp.view.panels;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PanelAddRecorder extends PanelBase {

    @FXML
    private TextField txtName;


    @FXML
    public void initialize(){

    }

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtModel() {
        return null;
    }

    public Spinner<Integer> getSpinnerNumberChannels() {
        return null;
    }

    public TextField getTxtIP() {
        return null;
    }

    public ComboBox<String> getComboProtocol() {
        return null;
    }

    public Spinner<Integer> getSpinnerPort() {
        return null;
    }

    public TextField getLogin(){
        return null;
    }

    public PasswordField getPassword(){
        return null;
    }

    public Button getButtonCancel(){
        return null;
    }

    public Button getButtonTestConnection(){
        return null;
    }

    public Button getButtonAdd(){
        return null;
    }
}
