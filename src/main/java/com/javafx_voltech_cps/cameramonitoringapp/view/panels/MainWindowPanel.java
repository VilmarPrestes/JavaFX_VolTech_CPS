package com.javafx_voltech_cps.cameramonitoringapp.view.panels;

import java.util.ArrayList;
import java.util.List;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;

public class MainWindowPanel {

    private static MainWindowPanel instance;
    private List<Camera> cameras;
    private List<Recorder> recorders;

    private MainWindowPanel() {
        cameras = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    public static MainWindowPanel getInstance() {
        if (instance == null) {
            instance = new MainWindowPanel();
        }
        return instance;
    }

    // Métodos para adicionar, editar, excluir e atualizar câmeras e gravadores
    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    public void addRecorder(Recorder recorder) {
        recorders.add(recorder);
    }

    // Outros métodos conforme necessário
}
