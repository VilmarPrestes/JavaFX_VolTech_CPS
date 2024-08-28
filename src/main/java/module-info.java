	module com.javafx_voltech_cps.cameramonitoringapp {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
    requires java.naming;
	requires opencv;
        requires java.sql;
        requires org.hibernate.orm.core;
        requires jakarta.persistence;


        opens com.javafx_voltech_cps.cameramonitoringapp to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp;
    exports com.javafx_voltech_cps.cameramonitoringapp.model.entity;
    exports com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;
    opens com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements to javafx.fxml;
}