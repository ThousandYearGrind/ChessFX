package com.example.chessfx.pieces;

import com.example.chessfx.Board;
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

    protected final Board b;
    protected final Tile[][] board;
    protected Tile tile;
    protected final Player color;
    protected final PieceType type;

    /**
     * @param b     the 2D Tile array that contains the Tile that the Piece is in
     * @param tile  the Tile that the piece is in
     * @param type  the type of piece (e.g. Rook, Queen, Pawn...) that the piece is
     * @param color the player color of the piece (black/white)
     */
    public Piece(Board b, Tile tile, PieceType type, Player color) {
        this.b = b;
        board = b.getBoard();
        this.tile = tile;
        this.color = color;
        this.type = type;
    }

    public boolean canMove(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return false;
        if (! b.hasMove(this.color)) return false;
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
        if (!canMove(row, col)) {
            b.draw();
            return;
        }
        Tile target = board[row][col];

        if (type == PieceType.PAWN) {
            ((Pawn) this).setMoved();
            b.resetEnPassant(target);
        } else {
            b.resetEnPassant(null);
        }

        target.setPiece(this);
        tile.setPiece(null);
        tile = target;
        b.nextToMove();
        b.draw();
    }

    protected boolean isOccupied(int row, int col) {
        return piece(row, col) != null;
    }

    protected boolean canMoveCardinally(int row, int col) {
        int R = tile.getRow();
        int C = tile.getCol();
        int deltaR = row - R;
        int deltaC = col - C;
        int dirR = deltaR == 0 ? 0 : deltaR / Math.abs(deltaR);
        int dirC = deltaC == 0 ? 0 : deltaC / Math.abs(deltaC);

        if (dirR * dirC == 0) {
            for (int i = 1; i < Math.max(deltaR * dirR, deltaC * dirC); i++) {
                if (isOccupied(R + dirR * i, C + dirC * i)) return false;
            }
            return true;
        }
        return false;
    }

    protected boolean canMoveOrdinally(int row, int col) {
        int R = tile.getRow();
        int C = tile.getCol();
        int deltaR = (row - R);
        int deltaC = (col - C);
        int dirR = deltaR == 0 ? 0 : deltaR / Math.abs(deltaR);
        int dirC = deltaC == 0 ? 0 : deltaC / Math.abs(deltaC);

        if (deltaR * dirR == deltaC * dirC) {
            for (int i = 1; i < deltaR * dirR; i++) {
                if (isOccupied(R + i * dirR, C + i * dirC)) return false;
            }
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
