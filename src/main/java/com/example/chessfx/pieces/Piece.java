package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public abstract class Piece {

    /*
     * functionalities:
     * get the tile that the piece is on
     * get the color of the piece
     * get the type of the piece
     * TODO: abstract method to check if a tile is a valid move
     * TODO: move the piece (capture if there is an enemy piece is there)
     */

    private final Tile[][] board;
    private Tile tile;
    private final Player color;
    private final PieceType type;

    public Piece(Tile[][] board, Tile tile, PieceType type, Player color) {
        this.board = board;
        this.tile = tile;
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(int row, int col);

    public boolean move(int row, int col) {
        if (canMove(row, col)) {
            Tile target = board[row][col];
            target.setPiece(this);
            tile.setPiece(null);
            tile = target;
            return true;
        }
        return false;
    }

    public Player getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return "X";
    }
}
