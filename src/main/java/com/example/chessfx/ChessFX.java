package com.example.chessfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChessFX extends Application {

    private Board b;

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        VBox L, R;
        L = new VBox(new Label("LEFT"));
        L.setBackground(Background.fill(Color.RED));
        R = new VBox(new Label("RIGHT"));
        R.setBackground(Background.fill(Color.GREEN));
        HBox T, B;
        T = new HBox(new Label("TOP"));
        T.setBackground(Background.fill(Color.BLUE));
        B = new HBox(new Label("BOTTOM"));
        B.setBackground(Background.fill(Color.ORANGE));
        pane.setBackground(Background.fill(Color.BEIGE));
        pane.setLeft(L);
        pane.setTop(T);
        pane.setBottom(B);
        pane.setRight(R);
        Scene scene = new Scene(pane, 1200, 1200);
        b = new Board(pane);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
