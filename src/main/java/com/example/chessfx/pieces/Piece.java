package com.example.chessfx.pieces;

import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public abstract class Piece {

    /*
     * functionalities:
     * get the tile that the piece is on
     * get the color of the piece
     * get the type of the piece
     * abstract method to check if a tile is a valid move
     * move the piece (capture if there is an enemy piece is there)
     */

    protected final Tile[][] board;
    protected Tile tile;
    protected final Player color;
    protected final PieceType type;

    /**
     * @param board the 2D Tile array that contains the Tile that the Piece is in
     * @param tile the Tile that the piece is in
     * @param type the type of piece (e.g. Rook, Queen, Pawn...) that the piece is
     * @param color the player color of the piece (black/white)
     */
    public Piece(Tile[][] board, Tile tile, PieceType type, Player color) {
        this.board = board;
        this.tile = tile;
        this.color = color;
        this.type = type;
    }

    protected boolean canMove(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return false;
        Piece target = piece(row, col);
        if (sameColor(target)) return false;
        return true;
    }

    protected Piece piece(int row, int col) {
        return board[row][col].getPiece();
    }

    protected boolean sameColor(Piece other) {
        return other != null && other.color == this.color;
    }

    public void move(int row, int col) {
        if (!canMove(row, col)) return;
        Tile target = board[row][col];
        target.setPiece(this);
        tile.setPiece(null);
        tile = target;
    }

    protected boolean isOccupied(int row, int col) {
        return piece(row, col) != null;
    }

    public Player getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
/*
maybe use later
    public Tile getTile() {
        return tile;
    }
 */
    @Override
    public String toString() {
        return "X";
    }
}
