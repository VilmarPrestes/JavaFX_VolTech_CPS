module com.javafx_voltech_cps.cameramonitoringapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafx_voltech_cps.cameramonitoringapp to javafx.fxml;
    exports com.javafx_voltech_cps.cameramonitoringapp;
}