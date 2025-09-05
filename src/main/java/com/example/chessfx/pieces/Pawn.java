package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.chessfx.pieces.PieceType.*;

public class Pawn extends Piece {

    private boolean firstMove;
    private boolean enPassant;
    private int direction;
    private final PieceType[] promoted = new PieceType[1];

    public Pawn(Board b, Tile t, Player color) {
        super(b, t, PAWN, color);
        direction = color == Player.WHITE ? 1 : -1;
        firstMove = true;
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        if (firstMove && row == tile.getRow() + 2 * direction && col == tile.getCol()) {
            enPassant = true;
            return !(isOccupied(row, col) || isOccupied(row - direction, col));
        } else if (row == tile.getRow() + direction && col == tile.getCol()) {
            return !isOccupied(row, col);
        } else if (row == tile.getRow() + direction && Math.abs(col - tile.getCol()) == 1) {
            return isOccupied(row, col) || b.isEnPassantTile(board[row][col]);
        }

        return false;
    }

    @Override
    public void move(int row, int col) {
        super.move(row, col);

        if (enPassant) {
            b.setEnPassant(this, board[row - direction][col]);
            enPassant = false;
        }

        if (color == Player.WHITE && tile.getRow() == 7 || color == Player.BLACK && tile.getRow() == 0) {
            promotions(color);
        }
    }

    public void setMoved() {
        firstMove = false;
    }
    public void promotions(Player color) {
        VBox promotionPane = new VBox();
        String c = color.toString().toLowerCase().substring(0,1);
        ImageView K = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-knight.png")));
        K.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tile.setPiece(new Knight(b, tile, color));
                promotionPane.getChildren().clear();
                b.draw();
            }
        });
        ImageView B = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-bishop.png")));
        B.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tile.setPiece(new Bishop(b, tile, color));
                promotionPane.getChildren().clear();
                b.draw();
            }
        });
        ImageView R = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-rook.png")));
        R.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tile.setPiece(new Rook(b, tile, color));
                promotionPane.getChildren().clear();
                b.draw();
            }
        });
        ImageView Q = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-queen.png")));
        Q.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tile.setPiece(new Queen(b, tile, color));
                promotionPane.getChildren().clear();
                b.draw();
            }
        });
        promotionPane.getChildren().addAll(K, B, R, Q);
        promotionPane.setBackground(Background.fill(Color.BEIGE));
        b.pane.setRight(promotionPane);
        b.pane.getRight().autosize();
    }
//
//    public PieceType promote(Player color) {
//        final PieceType[] type = new PieceType[1];
//        return type[0];
//    }

    @Override
    public String toString() {
        return "P";
    }
}
