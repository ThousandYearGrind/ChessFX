package com.example.chessfx;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessFX extends Application {

    public void start(Stage stage) {
        stage.setScene(new Scene(getContent(), 800, 800));
        stage.setResizable(false);
        stage.show();
    }

    private Parent getContent() {
        Pane root = new Pane();
        Board b = new Board();
        Draw d = new Draw();
        root = d.drawBoard(b.getBoard());
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
