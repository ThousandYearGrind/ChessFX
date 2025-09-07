package com.example.chessfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

public class ChessFX extends Application {

    private Board x;

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setBackground(Background.fill(Color.BLACK));
        Scene scene = new Scene(pane, 1000, 900);
        Board b = new Board(pane);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("ChessFX");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
