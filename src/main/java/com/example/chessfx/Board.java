package com.example.chessfx;

import com.example.chessfx.pieces.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private final Pane pane;
    private final Draw d;
    private final Tile[][] board;
    private Tile enPassantTile;
    private Piece enPassantPiece;

    /**
     * set the pane to display in
     * initialize 8x8 array of Tile objects <br>
     * create the Draw object based on the board to
     * @param pane - the pane the board will be displayed in
     */
    public Board(Pane pane) {
        this.pane = pane;
        board = new Tile[8][8];
        // Draw must be constructed after board is initialized (has a reference to board
        this.d = new Draw();
        initTiles();
        initPieces();
        draw();
    }

    /**
     * > populate the 2D array of Tile objects with tiles of the appropriate color and size <br>
     * > (x,y) within the array represents rank x + 1, file y + 1 <br>
     */
    private void initTiles() {
        boolean isDark = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Tile(isDark, row, col);
                isDark = ! isDark;
            }
            isDark = ! isDark;
        }
    }

    /**
     * > populate the 2D array of Tile objects with the proper initial Piece objects <br>
     * > rank 1 will have the white back rank <br>
     * > rank 2 has the white pawns <br>
     * > rank 7 has the black pawns <br>
     * > rank 8 has the black back rank <br>
     * > pointers to the pieces will be stored within the Tile objects <br>
     */
    private void initPieces() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile t = board[row][col];
                if (row == 0) {
                    // white back rank
                    if (col == 0 || col == 7)
                        t.setPiece(new Rook(this, t, Player.WHITE));
                    else if (col == 1 || col == 6)
                        t.setPiece(new Knight(this, t, Player.WHITE));
                    else if (col == 2 || col == 5)
                        t.setPiece(new Bishop(this, t, Player.WHITE));
                    else if (col == 3)
                        t.setPiece(new King(this, t, Player.WHITE));
                    else
                        t.setPiece(new Queen(this, t, Player.WHITE));
                } else if (row == 1) {
                    // white pawns
                    t.setPiece(new Pawn(this, t, Player.WHITE));
                } else if (row == 6) {
                    // black pawns
                    t.setPiece(new Pawn(this, t, Player.BLACK));
                } else if (row == 7) {
                    // black back rank
                    if (col == 0 || col == 7)
                        t.setPiece(new Rook(this, t, Player.BLACK));
                    else if (col == 1 || col == 6)
                        t.setPiece(new Knight(this, t, Player.BLACK));
                    else if (col == 2 || col == 5)
                        t.setPiece(new Bishop(this, t, Player.BLACK));
                    else if (col == 3)
                        t.setPiece(new King(this, t, Player.BLACK));
                    else
                        t.setPiece(new Queen(this, t, Player.BLACK));
                }
            }
        }
    }

//    public void printBoard() {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(board[i][j].getPiece() + " ");
//            }
//            System.out.println();
//        }
//    }

    /**
     * > first clear the current GUI display <br>
     * > add each element in the board again to reflect any changes that were made <br>
     */
    private void draw() {
       pane.getChildren().clear();
       pane.getChildren().add(d.drawBoard());
    }

    /**
     * @return the 2D array of Tile objects representing the board
     */
    public Tile[][] getBoard() {
        return board;
    }

    public void setEnPassant(Pawn p, Tile t) {
        enPassantPiece = p;
        enPassantTile = t;
    }

    public void resetEnPassant(Tile t) {
        if (enPassantTile == null) return;
        if (t == enPassantTile) {
            enPassantPiece.getTile().setPiece(null);
            enPassantTile = null;
        } else {
            enPassantPiece = null;
            enPassantTile = null;
        }
    }

    public boolean isEnPassantTile(Tile t) {
        return t == enPassantTile;
    }

    private class Draw {
        // make a pane with the board
        Board b = Board.this;
        Tile[][] board = b.getBoard();

        public Pane drawBoard() {
            Pane boardImage = new Pane();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Tile t = board[row][col];
                    Rectangle tileRectangle = drawTile(t);
                    tileRectangle.setTranslateX(Tile.getWidth() * col);
                    tileRectangle.setTranslateY(Tile.getWidth() * row);
                    boardImage.getChildren().add(tileRectangle);
                }
            }

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Tile t = board[row][col];
                    if (t.getPiece() != null) {
                        ImageView pcImg = drawPiece(t.getPiece());
                        pcImg.setTranslateX(Tile.getWidth() * col);
                        pcImg.setTranslateY(Tile.getWidth() * row);
                        boardImage.getChildren().add(pcImg);
                    }
                }
            }
            return boardImage;
        }

        private ImageView drawPiece(Piece p) {
            String path = "/pieceImages/";
            if (p.getColor() == Player.WHITE)
                path += "w-";
            else
                path += "b-";
            path += p.getType().toString().toLowerCase();
            path += ".png";
            ImageView pcImg = new ImageView(String.valueOf(this.getClass().getResource(path)));
            pcImg.setPickOnBounds(true);
            makeDraggable(pcImg);
            return pcImg;
        }

        /**
         * draws a tile with the right color and position in the parent
         * @param t
         * @return
         */
        private Rectangle drawTile(Tile t) {
            Rectangle rect;
            if (t.isDark()) {
                rect = new Rectangle(Tile.getWidth(), Tile.getWidth());
                rect.setFill(Color.SIENNA);
                return rect;
            }
            rect = new Rectangle(Tile.getWidth(), Tile.getWidth());
            rect.setFill(Color.BEIGE);
            return rect;
        }

        private int guiRow, guiCol, endRow, endCol;
        private Piece currentPiece;
        private void makeDraggable(Node... s) {
            for (Node node : s) {
                node.setOnMousePressed(e -> {
                    node.toFront();
                    guiCol = (int) e.getSceneX() / 100;
                    guiRow = (int) e.getSceneY() / 100;
                    currentPiece = board[guiRow][guiCol].getPiece();
                    endCol = guiCol;
                    endRow = guiRow;
                    node.setTranslateX(e.getSceneX() - Tile.getWidth() / 2.0);
                    node.setTranslateY(e.getSceneY() - Tile.getWidth() / 2.0);
                    System.out.println(guiRow + ":" + guiCol);
                });
                node.setOnMouseDragged(e -> {
                    node.setTranslateX(e.getSceneX() - Tile.getWidth() / 2.0);
                    node.setTranslateY(e.getSceneY() - Tile.getWidth() / 2.0);
                    endRow = (int) e.getSceneY() / 100;
                    endCol = (int) e.getSceneX() / 100;
                });
                node.setOnMouseReleased(mouseEvent -> {
                    node.setTranslateX(endCol * Tile.getWidth());
                    node.setTranslateY(endRow * Tile.getWidth());
                    currentPiece.move(endRow, endCol);
                    b.draw();
                    System.out.println(endRow + ":" + endCol);
                });
            }
        }
    }
}