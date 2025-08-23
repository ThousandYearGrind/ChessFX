package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Pawn extends Piece {
    public Pawn(Board board, Tile tile, Player color) {
        super(board, tile, "pawn", color);
    }
}
