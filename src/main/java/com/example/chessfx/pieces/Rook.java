package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Rook extends Piece {
    public Rook(Board b, Tile t, Player color) {
        super(b, t, PieceType.ROOK, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        return canMoveCardinally(row, col);
    }

    @Override
    public String toString() {
        return "R";
    }
}
