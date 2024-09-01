package com.javafx_voltech_cps.cameramonitoringapp.view.forms;

import com.javafx_voltech_cps.cameramonitoringapp.controller.RecorderController;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import com.javafx_voltech_cps.cameramonitoringapp.view.panels.PanelAddRecorder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FormAddRecorder extends FormBase {

    private final RecorderController recorderController;
    private final Dialog<Void> dialog;
    private final PanelAddRecorder panel;

    public FormAddRecorder(Stage stage, RecorderController recorderController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx_voltech_cps/cameramonitoringapp/view/panels/PanelAddRecorder.fxml"));
        Parent root = loader.load();
        panelModel = loader.getController();
        this.panel = (PanelAddRecorder) panelModel;
        this.recorderController = recorderController;
        dialog = new Dialog<>();
        dialog.setTitle("Add Recorder");
        dialog.getDialogPane().setContent(root);
    }

    private void setCommands() {

        panel.getButtonCancel().setOnAction(event -> {
            close();
        });

        panel.getButtonTestConnection().setOnAction(event -> {
            addRecorder(false);
        });

        panel.getButtonAdd().setOnAction(event -> {
            addRecorder(true);
        });

    }

    public void close(){
        dialog.close();
    }

    private boolean ping(String ipAddress) {
        try {
            String command;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                command = "ping -n 1 " + ipAddress; // Para Windows
            } else {
                command = "ping -c 1 " + ipAddress; // Para Unix/Linux/MacOS
            }
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String outputLine;
            while ((outputLine = inputStream.readLine()) != null) {
                if (outputLine.contains("TTL=")) {
                    return true; // Ping bem-sucedido
                }
            }
            int exitCode = process.waitFor();
            return exitCode == 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addRecorder(boolean save) {
        try{
            validRecorder();
            Recorder recorder = getRecorder();
            if(!ping(recorder.getHost())){
                throw new Exception("Connection failed");
            }
            if(save){
                recorderController.create(recorder);
                close();
            }
        }
        catch (Exception e) {
            messageError(e);
        }
    }

    private void validRecorder() throws Exception {
        if (!panel.getTxtName().getText().isEmpty()) {
            if (!panel.getTxtModel().getText().isEmpty()) {
                if (!panel.getTxtIP().getText().isEmpty()) {
                    if (!panel.getLogin().getText().isEmpty()) {
                        if (!panel.getPassword().getText().isEmpty()) {
                            if (panel.getPassword().getText().contains("@")) {
                                return;
                            } else {
                                throw new Exception("The recorder password cannot contain @");
                            }
                        } else {
                            throw new Exception("Enter the password of the recorder");
                        }
                    } else {
                        throw new Exception("Enter the login of the recorder");
                    }
                } else {
                    throw new Exception("Enter the IP v.4 of the recorder");
                }
            } else {
                throw new Exception("Enter the model of the recorder");
            }
        } else {
            throw new Exception("Enter the name of the recorder");
        }
    }

    private Recorder getRecorder() {
        Recorder recorder = new Recorder();
        recorder.setName(panel.getTxtName().getText());
        recorder.setModel(panel.getTxtModel().getText());
        recorder.setNumberOfChannels(panel.getSpinnerNumberChannels().getValue());
        recorder.setProtocol(panel.getComboProtocol().getValue());
        recorder.setHost(panel.getTxtIP().getText());
        recorder.setPort(panel.getSpinnerPort().getValue());
        recorder.setLogin(panel.getLogin().getText());
        recorder.setPassword(panel.getPassword().getText());
        return recorder;

    }
}
