package com.example.chessfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessFX extends Application {

    private Board b;

    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 800);
        b = new Board(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
