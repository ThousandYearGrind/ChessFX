package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Rook extends Piece {
    public Rook(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.ROOK, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;

        return false;
    }

    @Override
    public String toString() {
        return "R";
    }
}
