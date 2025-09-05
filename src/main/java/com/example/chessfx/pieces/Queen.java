package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Queen extends Piece {
    public Queen(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        Piece target = piece(row, col);
        if (sameColor(target)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
