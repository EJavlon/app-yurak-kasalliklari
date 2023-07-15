module com.company.appyurakkasalliklari {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.company.appyurakkasalliklari to javafx.fxml;
    exports com.company.appyurakkasalliklari;
    exports com.company.appyurakkasalliklari.controller;
    opens com.company.appyurakkasalliklari.controller to javafx.fxml;
    opens com.company.appyurakkasalliklari.model to com.google.gson;

}