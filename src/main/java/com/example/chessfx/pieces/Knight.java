package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Knight extends Piece {

    public Knight(Board b, Tile t, Player color) {
        super(b, t, PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;

        int R = tile.getRow();
        int C = tile.getCol();
        int deltaR = row - R;
        int deltaC = col - C;

        if (Math.abs(deltaR) == 1 && Math.abs(deltaC) == 2) return true;
        if (Math.abs(deltaR) == 2 && Math.abs(deltaC) == 1) return true;

        return false;
    }

    @Override
    public String toString() {
        return "N";
    }
}
