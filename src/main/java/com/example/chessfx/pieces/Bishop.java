package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Bishop extends Piece {
    public Bishop(Board b, Tile t, Player color) {
        super(b, t, PieceType.BISHOP, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        return canMoveOrdinally(row, col);
    }

    @Override
    public String toString() {
        return "B";
    }
}
