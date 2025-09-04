package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Pawn extends Piece {
    public Pawn(Tile[][] board, Tile tile, Player color) {
        super(board, tile, PieceType.PAWN, color);
    }

    // TODO: implement canMove() in Pawn
    @Override
    public boolean canMove(int row, int col) {
        return false;
    }

    @Override
    public String toString() {
        return "P";
    }
}
