	module com.javafx_voltech_cps.cameramonitoringapp {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires opencv;


    opens com.javafx_voltech_cps.cameramonitoringapp to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp;
    exports com.javafx_voltech_cps.cameramonitoringapp.controller;
    opens com.javafx_voltech_cps.cameramonitoringapp.controller to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp.model.entity;
    opens com.javafx_voltech_cps.cameramonitoringapp.model.entity to javafx.fxml;
}