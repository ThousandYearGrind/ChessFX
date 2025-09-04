package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Knight extends Piece {

    public Knight(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        return "N";
    }
}
