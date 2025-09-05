package com.example.chessfx;

import com.example.chessfx.pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {

    private static int width = 100;
    private boolean isDark;
    private Piece piece;
    private int row, col;

    /* functionality
     * get the color of the tile
     * get the row of the tile
     * get the column of the tile
     */

    public Tile(boolean isDark, int row, int col) {
        this.row = row;
        this.col = col;
        this.isDark = isDark;
    }

    public boolean isDark() {
        return isDark;
    }

    public void setPiece(Piece p) {
        piece = p;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static int getWidth() {
        return width;
    }
}
