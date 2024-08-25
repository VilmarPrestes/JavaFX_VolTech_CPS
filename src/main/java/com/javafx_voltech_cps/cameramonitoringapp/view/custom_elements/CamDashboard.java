package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

public class CamDashboard extends AnchorPane {
    private List<Recorder> recorderList;
    private HBox hbox;

    public CamDashboard() {
        recorderList = new ArrayList<>();
        hbox = new HBox();
        hbox.setSpacing(10);
        getChildren().add(hbox);
        AnchorPane.setTopAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 0.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);

        // Ajusta o tamanho dos CamMonitoring quando o tamanho da janela mudar
        widthProperty().addListener((obs, oldWidth, newWidth) -> adjustCamMonitoringSize());
    }

    public void addRecorder(Recorder recorder) throws Exception {
        if (recorder != null && !recorderList.contains(recorder)) {
            recorderList.add(recorder);
            CamMonitoring camMonitoring = new CamMonitoring();
            camMonitoring.setRecorder(recorder); // Associa o Recorder ao CamMonitoring
            camMonitoring.start();
            hbox.getChildren().add(camMonitoring);
            adjustCamMonitoringSize(); // Ajusta o tamanho após adicionar
        }
    }

    public void removeRecorder(Recorder recorder) {
        if (recorder != null && recorderList.contains(recorder)) {
            recorderList.remove(recorder);
            CamMonitoring camMonitoringToRemove = null;
            for (var child : hbox.getChildren()) {
                if (child instanceof CamMonitoring) {
                    CamMonitoring camMonitoring = (CamMonitoring) child;
                    if (camMonitoring.getRecorder().equals(recorder)) {
                        camMonitoringToRemove = camMonitoring;
                        break;
                    }
                }
            }
            if (camMonitoringToRemove != null) {
                hbox.getChildren().remove(camMonitoringToRemove);
            }
            adjustCamMonitoringSize(); // Ajusta o tamanho após remover
        }
    }

    // Ajusta o tamanho dos CamMonitoring com base no tamanho total disponível
    private void adjustCamMonitoringSize() {
        double totalWidth = getWidth() - (hbox.getSpacing() * (hbox.getChildren().size() - 1)); // Subtrai o espaço entre os elementos
        int count = hbox.getChildren().size();
        double widthPerItem = (count > 0) ? totalWidth / count : 0;

        for (var child : hbox.getChildren()) {
            if (child instanceof CamMonitoring) {
                ((CamMonitoring) child).setPrefWidth(widthPerItem);
            }
        }
    }
}
