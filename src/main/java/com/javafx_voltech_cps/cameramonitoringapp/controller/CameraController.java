package com.javafx_voltech_cps.cameramonitoringapp.controller;

import com.javafx_voltech_cps.cameramonitoringapp.model.dao.CameraDAO;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;

import java.util.List;

public class CameraController {
    private CameraDAO cameraDAO;

    public CameraController() throws Exception {
        this.cameraDAO = new CameraDAO();
    }

    public void addCamera(Camera camera) throws Exception {
        cameraDAO.create(camera);
    }

    public List<Camera> getAllCameras() throws Exception {
        return cameraDAO.getAll();
    }

    public void updateCamera(Camera camera) throws Exception {
        cameraDAO.update(camera);
    }

    public void deleteCamera(int id) throws Exception {
        cameraDAO.delete(id);
    }
}
