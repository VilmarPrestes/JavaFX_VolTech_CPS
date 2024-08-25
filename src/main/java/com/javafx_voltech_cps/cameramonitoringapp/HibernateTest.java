package com.javafx_voltech_cps.cameramonitoringapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        // Configura o Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Abre uma sessão
        Session session = null;
        try {
            session = sessionFactory.openSession();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            sessionFactory.close();
        }
    }
}
