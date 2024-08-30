package com.javafx_voltech_cps.cameramonitoringapp.view.panels;

import com.javafx_voltech_cps.cameramonitoringapp.controller.RecorderController;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamDashboard;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.RecorderTreeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;

import java.io.IOException;

public class MainWindowPanel extends AnchorPane {

    private final RecorderTreeView recorderTreeView;
    private final CamDashboard camDashboard;
    private final RecorderController recorderController;
    private TabPane tabPane;

    public MainWindowPanel() {
        recorderController = new RecorderController();
        recorderTreeView = new RecorderTreeView();
        recorderTreeView.setPrefWidth(300);
        camDashboard = new CamDashboard();

        getChildren().addAll(recorderTreeView, camDashboard);
        setAnchors();
        setupTabPane();

        Camera c = new Camera();
        c.setPrincipal(true);
        Recorder record = new Recorder();
        record.getCameras().add(new Camera());
        record.getCameras().add(c);
        Recorder record2 = new Recorder();
        record2.getCameras().add(new Camera());
        record2.getCameras().add(c);
        Recorder record3 = new Recorder();
        record3.getCameras().add(new Camera());
        record3.getCameras().add(c);
        recorderTreeView.addRecorder(record);
        recorderTreeView.addRecorder(record2);
        record2.getCameras().add(new Camera());
        recorderTreeView.updateRecorder(record2);
    }

    private void setAnchors() {
        AnchorPane.setTopAnchor(recorderTreeView, 0.0);
        AnchorPane.setBottomAnchor(recorderTreeView, 0.0);
        AnchorPane.setLeftAnchor(recorderTreeView, 0.0);

        AnchorPane.setTopAnchor(camDashboard, 0.0);
        AnchorPane.setBottomAnchor(camDashboard, 0.0);
        AnchorPane.setLeftAnchor(camDashboard, 300.0);
        AnchorPane.setRightAnchor(camDashboard, 0.0);
    }

    private void setupTabPane() {
        tabPane = new TabPane();
        tabPane.setPrefSize(600, 400);

        getChildren().add(tabPane);
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 300.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);

        Button addButton = new Button("Adicionar Câmera");
        addButton.setOnAction(event -> showAddCameraForm());
        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setLeftAnchor(addButton, 10.0);
        getChildren().add(addButton);
    }

    private void showAddCameraForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafx_voltech_cps/cameramonitoringapp/view/forms/AddCameraForm.fxml"));
            Parent addCameraForm = loader.load();

            Tab tab = new Tab("Adicionar Câmera");
            tab.setContent(addCameraForm);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
