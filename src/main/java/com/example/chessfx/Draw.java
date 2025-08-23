package com.example.chessfx;

import com.example.chessfx.pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Draw {
    // make a pane with the board
    public Pane drawBoard(Tile[][] board/*, boolean flip*/) {
        Pane boardImage = new Pane();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile t = board[row][col];
                Rectangle tileRectangle = drawTile(t);
                tileRectangle.setTranslateX(Tile.getWidth() * col);
                tileRectangle.setTranslateY(Tile.getWidth() * row);
                boardImage.getChildren().add(tileRectangle);
                if (t.getPiece() != null) {
                    ImageView pcImg = drawPiece(t.getPiece());
                    pcImg.setTranslateX(Tile.getWidth() * col);
                    pcImg.setTranslateY(Tile.getWidth() * row);
                    boardImage.getChildren().add(pcImg);
                }
            }
        }
        return boardImage;
    }

    // make an ImageView of the Piece
    private ImageView drawPiece(Piece p) {
        String path = "/pieceImages/";
        if (p.getColor() == Player.WHITE)
            path += "w-";
        else
            path += "b-";
        path += p.getType().toString().toLowerCase();
        path += ".png";
        System.out.println(path);
        Draw d = new Draw();
        return new ImageView(String.valueOf(this.getClass().getResource(path)));
    }

    // make a rectangle of the Tile
    private Rectangle drawTile(Tile t) {
        Rectangle rect;
        if (t.isDark()) {
            rect = new Rectangle(Tile.getWidth(), Tile.getWidth());
            rect.setFill(Color.SIENNA);
            return rect;
        }
        rect = new Rectangle(Tile.getWidth(), Tile.getWidth());
        rect.setFill(Color.BEIGE);
        return rect;
    }

    private void makeDraggable(Node... s) {
        for (Node node : s) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                }
            });
        }
    }
}
