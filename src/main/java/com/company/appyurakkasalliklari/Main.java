package com.company.appyurakkasalliklari;

import com.company.appyurakkasalliklari.constants.ProjectConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("window1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);
        stage.setResizable(false);
        stage.setTitle(ProjectConstants.PROJECT_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}