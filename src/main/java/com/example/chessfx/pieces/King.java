package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class King extends Piece {
    public King(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.KING, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        Piece target = piece(row, col);
        if (sameColor(target)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "K";
    }
}
