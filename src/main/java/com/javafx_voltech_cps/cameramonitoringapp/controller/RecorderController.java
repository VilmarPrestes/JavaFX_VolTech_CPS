package com.javafx_voltech_cps.cameramonitoringapp.controller;

import com.javafx_voltech_cps.cameramonitoringapp.model.Database;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RecorderController {
    private Session session;
    private List<Recorder> recorderList;

    public RecorderController(Database database) {
        this.session = database.getConnection();
        recorderList = new ArrayList<>();
    }

    public void load() throws Exception {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Recorder";
            Query<Recorder> query = session.createQuery(hql, Recorder.class);
            recorderList = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
                throw new Exception("Error loading records", e);
        }
    }

    public List<Recorder> getAll() {
        return recorderList;
    }

    public void create(Recorder recorder) throws Exception {
        for (Recorder existing : recorderList) {
            if (existing.getName().equals(recorder.getName())) {
                throw new Exception("There is already a recorder registered with the same name");
            }
            else if( existing.getHost().equals(recorder.getHost())){
                throw new Exception("There is already a recorder registered with the same IP address");
            }
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(recorder);
            transaction.commit();
            recorderList.add(recorder);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception("Error creating recorder",e); // Re-throw the exception after rollback
        }
    }
}

