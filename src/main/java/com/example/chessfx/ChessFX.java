package com.example.chessfx;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessFX extends Application {

    public void start(Stage stage) {
        stage.setScene(new Scene(getContent()));
        stage.setResizable(false);
        stage.show();
    }

    private Parent getContent() {
        Pane root = new Pane();
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
