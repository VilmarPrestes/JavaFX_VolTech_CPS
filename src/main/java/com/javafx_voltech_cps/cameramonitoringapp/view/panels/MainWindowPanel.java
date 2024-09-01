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
    private RecorderController recorderController;

    public MainWindowPanel() throws Exception {
        Recorder recorderAmericano = new Recorder();
        recorderAmericano.setName("Americano");
        Camera cameraAmericano1 = new Camera();
        cameraAmericano1.setPrincipal(true);
        cameraAmericano1.setId(1);
        cameraAmericano1.setModel("");
        cameraAmericano1.setLogin("vorbe");
        cameraAmericano1.setHost("192.168.7.240");
        cameraAmericano1.setChannel(2);
        cameraAmericano1.setPassword("vorbe123");
        cameraAmericano1.setPort(554);
        cameraAmericano1.setStreaming(0);
        cameraAmericano1.setProtocol("rtsp");
        cameraAmericano1.setName("CAM 02");
        recorderAmericano.getCameras().add(cameraAmericano1);


        Camera cameraAmericano2 = new Camera();
        cameraAmericano2.setPrincipal(false);
        cameraAmericano2.setId(2);
        cameraAmericano2.setModel("");
        cameraAmericano2.setLogin("vorbe");
        cameraAmericano2.setHost("192.168.7.240");
        cameraAmericano2.setPassword("vorbe123");
        cameraAmericano2.setChannel(3);
        cameraAmericano2.setPort(554);
        cameraAmericano2.setStreaming(1);
        cameraAmericano2.setProtocol("rtsp");
        cameraAmericano2.setName("CAM 03'");
        recorderAmericano.getCameras().add(cameraAmericano2);


        Camera cameraAmericano3 = new Camera();
        cameraAmericano3.setPrincipal(false);
        cameraAmericano3.setId(2);
        cameraAmericano3.setModel("");
        cameraAmericano3.setLogin("vorbe");
        cameraAmericano3.setHost("192.168.7.240");
        cameraAmericano3.setChannel(4);
        cameraAmericano3.setPort(554);
        cameraAmericano3.setPassword("vorbe123");
        cameraAmericano3.setStreaming(1);
        cameraAmericano3.setProtocol("rtsp");
        cameraAmericano3.setName("CAM 04'");
        recorderAmericano.getCameras().add(cameraAmericano3);


        Camera cameraAmericano4 = new Camera();
        cameraAmericano4.setPrincipal(false);
        cameraAmericano4.setId(2);
        cameraAmericano4.setModel("");
        cameraAmericano4.setLogin("vorbe");
        cameraAmericano4.setHost("192.168.7.240");
        cameraAmericano4.setPassword("vorbe123");
        cameraAmericano4.setChannel(5);
        cameraAmericano4.setPort(554);
        cameraAmericano4.setStreaming(1);
        cameraAmericano4.setProtocol("rtsp");
        cameraAmericano4.setName("CAM 05'");
        recorderAmericano.getCameras().add(cameraAmericano4);


        Recorder recorderMossamedes = new Recorder();
        recorderMossamedes.setName("Mossamedes");
        Camera cameraMossamedes1 = new Camera();
        cameraMossamedes1.setPrincipal(false);
        cameraMossamedes1.setId(1);
        cameraMossamedes1.setModel("");
        cameraMossamedes1.setLogin("vorbe");
        cameraMossamedes1.setHost("192.168.4.240");
        cameraMossamedes1.setChannel(1);
        cameraMossamedes1.setPassword("vorbe123");
        cameraMossamedes1.setPort(554);
        cameraMossamedes1.setStreaming(0);
        cameraMossamedes1.setProtocol("rtsp");
        cameraMossamedes1.setName("CAM 02");
        recorderMossamedes.getCameras().add(cameraMossamedes1);


        Camera cameraMossamedes2 = new Camera();
        cameraMossamedes2.setPrincipal(false);
        cameraMossamedes2.setId(2);
        cameraMossamedes2.setModel("");
        cameraMossamedes2.setLogin("vorbe");
        cameraMossamedes2.setHost("192.168.4.240");
        cameraMossamedes2.setPassword("vorbe123");
        cameraMossamedes2.setChannel(2);
        cameraMossamedes2.setPort(554);
        cameraMossamedes2.setStreaming(1);
        cameraMossamedes2.setProtocol("rtsp");
        cameraMossamedes2.setName("CAM 03'");
        recorderMossamedes.getCameras().add(cameraMossamedes2);


        Camera cameraMossamedes3 = new Camera();
        cameraMossamedes3.setPrincipal(false);
        cameraMossamedes3.setId(2);
        cameraMossamedes3.setModel("");
        cameraMossamedes3.setLogin("vorbe");
        cameraMossamedes3.setHost("192.168.4.240");
        cameraMossamedes3.setChannel(3);
        cameraMossamedes3.setPort(554);
        cameraMossamedes3.setPassword("vorbe123");
        cameraMossamedes3.setStreaming(1);
        cameraMossamedes3.setProtocol("rtsp");
        cameraMossamedes3.setName("CAM 04'");
        recorderMossamedes.getCameras().add(cameraMossamedes3);


        Camera cameraMossamedes4 = new Camera();
        cameraMossamedes4.setPrincipal(false);
        cameraMossamedes4.setId(2);
        cameraMossamedes4.setModel("");
        cameraMossamedes4.setLogin("vorbe");
        cameraMossamedes4.setHost("192.168.4.240");
        cameraMossamedes4.setPassword("vorbe123");
        cameraMossamedes4.setChannel(4);
        cameraMossamedes4.setPort(554);
        cameraMossamedes4.setStreaming(1);
        cameraMossamedes4.setProtocol("rtsp");
        cameraMossamedes4.setName("CAM 05'");
        recorderMossamedes.getCameras().add(cameraMossamedes4);


        Camera cameraMossamedes5 = new Camera();
        cameraMossamedes5.setPrincipal(false);
        cameraMossamedes5.setId(2);
        cameraMossamedes5.setModel("");
        cameraMossamedes5.setLogin("vorbe");
        cameraMossamedes5.setHost("192.168.4.240");
        cameraMossamedes5.setPassword("vorbe123");
        cameraMossamedes5.setChannel(5);
        cameraMossamedes5.setPort(554);
        cameraMossamedes5.setStreaming(1);
        cameraMossamedes5.setProtocol("rtsp");
        cameraMossamedes5.setName("CAM 05'");
        recorderMossamedes.getCameras().add(cameraMossamedes5);


        Camera cameraMossamedes6 = new Camera();
        cameraMossamedes6.setPrincipal(true);
        cameraMossamedes6.setId(2);
        cameraMossamedes6.setModel("");
        cameraMossamedes6.setLogin("vorbe");
        cameraMossamedes6.setHost("192.168.4.240");
        cameraMossamedes6.setPassword("vorbe123");
        cameraMossamedes6.setChannel(6);
        cameraMossamedes6.setPort(554);
        cameraMossamedes6.setStreaming(1);
        cameraMossamedes6.setProtocol("rtsp");
        cameraMossamedes6.setName("CAM 05'");
        recorderMossamedes.getCameras().add(cameraMossamedes6);


        Camera cameraMossamedes7 = new Camera();
        cameraMossamedes7.setPrincipal(false);
        cameraMossamedes7.setId(2);
        cameraMossamedes7.setModel("");
        cameraMossamedes7.setLogin("vorbe");
        cameraMossamedes7.setHost("192.168.4.240");
        cameraMossamedes7.setPassword("vorbe123");
        cameraMossamedes7.setChannel(7);
        cameraMossamedes7.setPort(554);
        cameraMossamedes7.setStreaming(1);
        cameraMossamedes7.setProtocol("rtsp");
        cameraMossamedes7.setName("CAM 05'");
        recorderMossamedes.getCameras().add(cameraMossamedes7);


        Camera cameraMossamedes8 = new Camera();
        cameraMossamedes8.setPrincipal(false);
        cameraMossamedes8.setId(2);
        cameraMossamedes8.setModel("");
        cameraMossamedes8.setLogin("vorbe");
        cameraMossamedes8.setHost("192.168.4.240");
        cameraMossamedes8.setPassword("vorbe123");
        cameraMossamedes8.setChannel(8);
        cameraMossamedes8.setPort(554);
        cameraMossamedes8.setStreaming(1);
        cameraMossamedes8.setProtocol("rtsp");
        cameraMossamedes8.setName("CAM 05'");
        recorderMossamedes.getCameras().add(cameraMossamedes8);

        recorderTreeView = new RecorderTreeView();
        recorderTreeView.addRecorder(recorderAmericano);
        recorderTreeView.addRecorder(recorderMossamedes);
        recorderTreeView.setPrefWidth(300);
        camDashboard = new CamDashboard();
        camDashboard.addRecorder(recorderAmericano);
        camDashboard.addRecorder(recorderMossamedes);
        getChildren().addAll(recorderTreeView, camDashboard);
        setAnchors();
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
