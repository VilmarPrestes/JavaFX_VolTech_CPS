package com.javafx_voltech_cps.cameramonitoringapp.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.javafx_voltech_cps.cameramonitoringapp.model.Database;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;

import java.util.List;

public class CameraDAO {
    private Dao<Camera, Integer> cameraDao;

    public CameraDAO() throws Exception {
        ConnectionSource connectionSource = Database.getConnection();
        TableUtils.createTableIfNotExists(connectionSource, Camera.class);
        cameraDao = DaoManager.createDao(connectionSource, Camera.class);
    }

    public void create(Camera camera) throws Exception {
        cameraDao.create(camera);
    }

    public List<Camera> getAll() throws Exception {
        return cameraDao.queryForAll();
    }

    public void update(Camera camera) throws Exception {
        cameraDao.update(camera);
    }

    public void delete(int id) throws Exception {
        cameraDao.deleteById(id);
    }
}
