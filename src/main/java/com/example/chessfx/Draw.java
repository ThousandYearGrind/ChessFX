package com.example.chessfx;

import com.example.chessfx.pieces.Piece;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Draw {
    // make a pane with the board
    Board b;
    Tile[][] board;
    public Pane drawBoard(Board b /*, boolean flip*/) {
        this.b = b;
        board = b.getBoard();
        Pane boardImage = new Pane();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile t = board[row][col];
                Rectangle tileRectangle = drawTile(t);
                tileRectangle.setTranslateX(Tile.getWidth() * col);
                tileRectangle.setTranslateY(Tile.getWidth() * row);
                boardImage.getChildren().add(tileRectangle);
            }
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile t = board[row][col];
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
        ImageView pcImg = new ImageView(String.valueOf(this.getClass().getResource(path)));
        pcImg.setPickOnBounds(true);
        makeDraggable(pcImg);
        return pcImg;
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

    private int guiRow, guiCol, endRow, endCol;
    private Piece currentPiece;
    private void makeDraggable(Node... s) {
        for (Node node : s) {
            node.setOnMousePressed(e -> {
                node.toFront();
                guiCol = (int) e.getSceneX() / 100;
                guiRow = (int) e.getSceneY() / 100;
                currentPiece = board[guiRow][guiCol].getPiece();
                endCol = guiCol;
                endRow = guiRow;
                node.setTranslateX(e.getSceneX() - Tile.getWidth() / 2.0);
                node.setTranslateY(e.getSceneY() - Tile.getWidth() / 2.0);
                System.out.println(guiRow + ":" + guiCol);
            });
            node.setOnMouseDragged(e -> {
                node.setTranslateX(e.getSceneX() - Tile.getWidth() / 2.0);
                node.setTranslateY(e.getSceneY() - Tile.getWidth() / 2.0);
                endRow = (int) e.getSceneY() / 100;
                endCol = (int) e.getSceneX() / 100;
            });
            node.setOnMouseReleased(mouseEvent -> {
                node.setTranslateX(endCol * Tile.getWidth());
                node.setTranslateY(endRow * Tile.getWidth());
                currentPiece.move(endRow, endCol);
                b.getGUIBoard();
                System.out.println(endRow + ":" + endCol);
            });
        }
    }

    Pane p;
    private Parent getContent() {
        p = new Pane();
        p = b.getGUIBoard();
        return p;
    }
}
