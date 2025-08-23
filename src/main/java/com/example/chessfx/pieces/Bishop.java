package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Bishop extends Piece {
    public Bishop(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.BISHOP, color);
    }

    @Override
    public boolean canMove(int row, int col) {

    }

    @Override
    public String toString() {
        return "B";
    }
}
