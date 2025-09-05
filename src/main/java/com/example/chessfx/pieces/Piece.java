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

    private final Tile[][] board;
    private Tile tile;
    private final Player color;
    private final PieceType type;

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

    protected abstract boolean canMove(int row, int col);

    protected Piece piece(int row, int col) {
        return board[row][col].getPiece();
    }

    protected boolean sameColor(Piece other) {
        return other.color == this.color;
    }

    public void move(int row, int col) {
        if (!canMove(row, col)) return;
        Tile target = board[row][col];
        target.setPiece(this);
        tile.setPiece(null);
        tile = target;
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
