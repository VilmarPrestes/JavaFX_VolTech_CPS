package com.javafx_voltech_cps.cameramonitoringapp.controller;

import com.javafx_voltech_cps.cameramonitoringapp.model.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.Recorder;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static MainController instance;
    private List<Camera> cameras;
    private List<Recorder> recorders;

    private MainController() {
        cameras = new ArrayList<>();
        recorders = new ArrayList<>();
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
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
