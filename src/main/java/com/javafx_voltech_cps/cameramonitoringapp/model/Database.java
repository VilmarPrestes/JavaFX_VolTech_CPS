package com.javafx_voltech_cps.cameramonitoringapp.model;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Database {
    private static final String DATABASE_URL = "jdbc:sqlite:your-database.db";
    private static ConnectionSource connectionSource;

    public static ConnectionSource getConnection() throws Exception {
        if (connectionSource == null) {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
        }
        return connectionSource;
    }

    public static void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
            connectionSource = null;
        }
    }
}
