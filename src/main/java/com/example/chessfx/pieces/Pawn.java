package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.chessfx.pieces.PieceType.*;

public class Pawn extends Piece {

    private boolean firstMove;
    private boolean enPassant;
    private final int direction;
    private final PieceType[] promoted = new PieceType[1];

    public Pawn(Board b, Tile t, Player color) {
        super(b, t, PAWN, color);
        direction = color == Player.WHITE ? 1 : -1;
        firstMove = true;
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        if (b.isPromoting()) return false;
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
        if (!canMove(row, col)) {
            b.draw();
            return;
        }
        Tile target = board[row][col];

        if (type == PieceType.PAWN) {
            ((Pawn) this).setMoved();
            b.resetEnPassant(target);
        } else {
            b.resetEnPassant(null);
        }

        target.setPiece(this);
        tile.setPiece(null);
        tile = target;

        if (enPassant) {
            b.setEnPassant(this, board[row - direction][col]);
            enPassant = false;
        }

        if (color == Player.WHITE && tile.getRow() == 7 || color == Player.BLACK && tile.getRow() == 0) {
            b.startPromoting();
            promotion(color);
        } else {
            b.nextToMove();
        }

        b.draw();
    }

    public void setMoved() {
        firstMove = false;
    }

    private void promote(Piece p, Pane promotionPane) {
        System.out.println("PROMOTE");
        p.getTile().setPiece(p);
        promotionPane.getChildren().clear();
        b.nextToMove();
        b.promoted();
        b.draw();
    }

    public void promotion(Player color) {
        VBox promotionPane = new VBox();
        String c = color.toString().toLowerCase().substring(0,1);
        ImageView N = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-knight.png")));
        N.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                promote(new Knight(b, tile, color), promotionPane);
            }
        });
        ImageView B = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-bishop.png")));
        B.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                promote(new Bishop(b, tile, color), promotionPane);
            }
        });
        ImageView R = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-rook.png")));
        R.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                promote(new Rook(b, tile, color), promotionPane);
            }
        });
        ImageView Q = new ImageView(String.valueOf(this.getClass().getResource("/pieceImages/" +
                c + "-queen.png")));
        Q.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                promote(new Queen(b, tile, color), promotionPane);
            }
        });
        promotionPane.getChildren().addAll(N, B, R, Q);
        promotionPane.setBackground(Background.fill(Color.BEIGE));
        b.pane.setRight(promotionPane);
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
