package com.example.chessfx.pieces;

import com.example.chessfx.Board;
import com.example.chessfx.Player;
import com.example.chessfx.Tile;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Piece {

    /*
     * functionalities:
     * get the tile that the piece is on
     * get the color of the piece
     * get the type of the piece
     * TODO: abstract method to check if a tile is a valid move
     * TODO: move the piece (capture if there is an enemy piece is there)
     */

    private Board board;
    private Tile tile;
    private Player color;
    private final PieceType type;

    public Piece(Board board, Tile tile, PieceType type, Player color) {
        this.board = board;
        this.tile = tile;
        this.color = color;
        this.type = type;
    }

    public abstract void canMove();

    // TODO: implement move
    public void move(int row, int col) {

    }

    // TODO: implement capture
    public void capture(int row, int col) {

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
}
