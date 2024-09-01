package com.javafx_voltech_cps.cameramonitoringapp.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
    private SessionFactory sessionFactory;
    private Session session;

    public void initialize() {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getConnection() {
        if (sessionFactory == null || session == null) {
            sessionFactory = null;
            session = null;
            initialize();
        }
        return session;
    }

    public void close() {
        if (sessionFactory != null || session != null) {
            assert sessionFactory != null;
            sessionFactory.close();
            session.close();
            sessionFactory = null;
            session = null;
        }
    }
}