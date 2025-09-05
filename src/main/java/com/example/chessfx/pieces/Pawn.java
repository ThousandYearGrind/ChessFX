package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;

public class Pawn extends Piece {

    private boolean firstMove;
    private boolean enPassant;
    private int direction;

    public Pawn(Board b, Tile t, Player color) {
        super(b, t, PieceType.PAWN, color);
        direction = color == Player.WHITE ? 1 : -1;
        firstMove = true;
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!super.canMove(row, col)) return false;
        if (firstMove && row == tile.getRow() + 2 * direction && col == tile.getCol()) {
            enPassant = true;
            return !(isOccupied(row, col) || isOccupied(row - direction, col));
        } else if (row == tile.getRow() + direction && col == tile.getCol()) {
            return !isOccupied(row, col);
        } else if (row == tile.getRow() + direction && Math.abs(col - tile.getCol()) == 1) {
            return isOccupied(row, col) || b.isEnPassantTile(board[row][col]);
        }

        return false;
    }

    @Override
    public void move(int row, int col) {
        super.move(row, col);

        if (enPassant) {
            b.setEnPassant(this, board[row - direction][col]);
            enPassant = false;
        }

        if (color == Player.WHITE && tile.getRow() == 7 || color == Player.BLACK && tile.getRow() == 0) {
            promote();
        }
    }

    public void setMoved() {
        firstMove = false;
    }

    // TODO: Fully implement promote
    private void promote() {
        Tile cur = board[tile.getRow()][tile.getCol()];
        cur.setPiece(new Queen(b, tile, color));
    }

    @Override
    public String toString() {
        return "P";
    }
}
