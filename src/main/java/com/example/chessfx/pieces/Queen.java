package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Queen extends Piece {
    public Queen(Board b, Tile t, Player color) {
        super(b, t, PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        return canMoveCardinally(row, col) || canMoveOrdinally(row, col);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
