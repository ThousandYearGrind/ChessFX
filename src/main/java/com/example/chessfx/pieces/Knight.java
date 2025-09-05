package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Knight extends Piece {

    public Knight(Tile[][] board, Tile t, Player color) {
        super(board, t, PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;

        int R = tile.getRow();
        int C = tile.getCol();

        if (row == R - 1 && col == C + 2) {
            return true;
        } else if (row == R - 1 && col == C - 2) {
            return true;
        } else if (row == R + 1 && col == C + 2) {
            return true;
        } else if (row == R + 1 && col == C - 2) {
            return true;
        } else if (row == R - 2 && col == C + 1) {
            return true;
        } else if (row == R - 2 && col == C - 1) {
            return true;
        } else if (row == R + 2 && col == C + 1) {
            return true;
        } else if (row == R + 2 && col == C - 1) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "N";
    }
}
