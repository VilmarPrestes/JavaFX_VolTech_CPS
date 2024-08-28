package com.javafx_voltech_cps.cameramonitoringapp.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
    private static SessionFactory sessionFactory;

    public static void initialize() {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            initialize();
        }
        return sessionFactory.openSession();
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}