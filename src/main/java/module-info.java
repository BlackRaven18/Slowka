
module com.arek{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires FXTrayIcon;
    requires java.sql;


    opens com.arek to javafx.fxml;
    exports com.arek;

}