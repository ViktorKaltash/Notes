package com.notes;

import com.notes.entity.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {
    public static String PATH_AUTH = (new File("").getAbsolutePath()) + "\\src\\main\\resources\\com\\notes\\Auth.fxml";
    public static User user;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 260, 360);
        stage.setTitle("Notes!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}