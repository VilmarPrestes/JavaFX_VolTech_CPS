module com.javafx_voltech_cps.cameramonitoringapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafx_voltech_cps.cameramonitoringapp to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp;
    exports com.javafx_voltech_cps.cameramonitoringapp.controller;
    opens com.javafx_voltech_cps.cameramonitoringapp.controller to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp.model;
    opens com.javafx_voltech_cps.cameramonitoringapp.model to javafx.fxml;
}