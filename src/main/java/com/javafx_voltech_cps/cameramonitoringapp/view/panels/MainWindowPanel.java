package com.javafx_voltech_cps.cameramonitoringapp.view.panels;

import com.javafx_voltech_cps.cameramonitoringapp.controller.CameraController;
import com.javafx_voltech_cps.cameramonitoringapp.controller.RecorderController;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamDashboard;
import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.RecorderTreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainWindowPanel extends AnchorPane {

    private final RecorderTreeView recorderTreeView;
    private final CamDashboard camDashboard;
    private final RecorderController recorderController;

    public MainWindowPanel() {
        recorderController = new RecorderController();
        recorderTreeView = new RecorderTreeView();
        recorderTreeView.setPrefWidth(300);
        camDashboard = new CamDashboard();
        getChildren().addAll(recorderTreeView, camDashboard);
        setAnchors();
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
        // Configurar o RecorderTreeView à esquerda
        AnchorPane.setTopAnchor(recorderTreeView, 0.0);
        AnchorPane.setBottomAnchor(recorderTreeView, 0.0);
        AnchorPane.setLeftAnchor(recorderTreeView, 0.0);

        // Configurar o CamDashboard para ocupar o resto da largura
        AnchorPane.setTopAnchor(camDashboard, 0.0);
        AnchorPane.setBottomAnchor(camDashboard, 0.0);
        AnchorPane.setLeftAnchor(camDashboard, 300.0);
        AnchorPane.setRightAnchor(camDashboard, 0.0);
    }


}
