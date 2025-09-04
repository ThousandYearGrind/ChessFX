package com.example.chessfx;

import com.example.chessfx.pieces.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Board {

    private final Pane pane;
    private Draw d;
    private final Tile[][] board;

    /* functionality:
     * contain a 2D board of tiles with rows and columns
     * when a board is constructed, init a board of tiles and default pieces for both colors on the appropriate tiles
     */
    public Board(Pane pane) {
        this.pane = pane;
        this.d = new Draw();
        board = new Tile[8][8];
        initTiles();
        initPieces();
    }

    private void initTiles() {
        boolean isDark = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Tile(isDark, row, col);
                isDark = ! isDark;
            }
            isDark = ! isDark;
        }
    }

    private void initPieces() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile t = board[row][col];
                if (row == 0) {
                    // white back rank
                    if (col == 0 || col == 7)
                        t.setPiece(new Rook(board, t, Player.WHITE));
                    else if (col == 1 || col == 6)
                        t.setPiece(new Knight(board, t, Player.WHITE));
                    else if (col == 2 || col == 5)
                        t.setPiece(new Bishop(board, t, Player.WHITE));
                    else if (col == 3)
                        t.setPiece(new King(board, t, Player.WHITE));
                    else
                        t.setPiece(new Queen(board, t, Player.WHITE));
                } else if (row == 1) {
                    // white pawns
                    t.setPiece(new Pawn(board, t, Player.WHITE));
                } else if (row == 6) {
                    // black pawns
                    t.setPiece(new Pawn(board, t, Player.BLACK));
                } else if (row == 7) {
                    // black back rank
                    if (col == 0 || col == 7)
                        t.setPiece(new Rook(board, t, Player.BLACK));
                    else if (col == 1 || col == 6)
                        t.setPiece(new Knight(board, t, Player.BLACK));
                    else if (col == 2 || col == 5)
                        t.setPiece(new Bishop(board, t, Player.BLACK));
                    else if (col == 3)
                        t.setPiece(new King(board, t, Player.BLACK));
                    else
                        t.setPiece(new Queen(board, t, Player.BLACK));
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getPiece() + " ");
            }
            System.out.println();
        }
    }

   public Pane getGUIBoard() {
       pane.getChildren().clear();
       pane.getChildren().add(d.drawBoard(this));
       return pane;
   }

    public Tile[][] getBoard() {
        return board;
    }
}