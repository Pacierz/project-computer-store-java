module com.app.computerstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires org.slf4j;

    exports com.app.computerstore.controllers;
    opens com.app.computerstore.controllers to javafx.fxml;
    exports com.app.computerstore.database;
    opens com.app.computerstore.database to javafx.fxml;
    exports com.app.computerstore.models;
    opens com.app.computerstore.models to javafx.fxml;
    exports com.app.computerstore.exceptions;
    opens com.app.computerstore.exceptions to javafx.fxml;
    exports com.app.computerstore;
    opens com.app.computerstore to javafx.fxml;
}