package com.javafx_voltech_cps.cameramonitoringapp.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.javafx_voltech_cps.cameramonitoringapp.model.Database;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;

import java.util.List;

public class RecorderDAO {
    private Dao<Recorder, Integer> recorderDao;

    public RecorderDAO() throws Exception {
        ConnectionSource connectionSource = Database.getConnection();
        TableUtils.createTableIfNotExists(connectionSource, Recorder.class);
        recorderDao = DaoManager.createDao(connectionSource, Recorder.class);
    }

    public void create(Recorder recorder) throws Exception {
        recorderDao.create(recorder);
    }

    public List<Recorder> getAll() throws Exception {
        return recorderDao.queryForAll();
    }

    public void update(Recorder recorder) throws Exception {
        recorderDao.update(recorder);
    }

    public void delete(int id) throws Exception {
        recorderDao.deleteById(id);
    }
}
