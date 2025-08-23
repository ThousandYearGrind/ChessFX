package com.example.chessfx;

import com.example.chessfx.pieces.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Board {

    private final Tile[][] board;

    /* functionality:
     * contain a 2D board of tiles with rows and columns
     * when a board is constructed, init a board of tiles and default pieces for both colors on the appropriate tiles
     */
    public Board() {
        board = new Tile[8][8];
        // TODO: init tiles
        // TODO: init pieces
        // TODO: draw board flipped
    }


    private void initTiles() {
        boolean isDark = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Tile(isDark, row, col);
            }
            isDark = ! isDark;
        }
        isDark = ! isDark;
    }

}