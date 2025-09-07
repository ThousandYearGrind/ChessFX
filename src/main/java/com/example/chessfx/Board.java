package com.example.chessfx;

import com.example.chessfx.pieces.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Board {

    public final BorderPane pane;
    private final Draw d;
    private final Tile[][] board;
    private Tile enPassantTile;
    private Piece enPassantPiece;

    private Pane image;
    private double originX = 0;
    private double originY = 0;


    //TODO: implement check
    //TODO: implement checkmate

    /**
     * set the pane to display in
     * initialize 8x8 array of Tile objects <br>
     * create the Draw object based on the board to
     * @param pane - the pane the board will be displayed in
     */
    public Board(BorderPane pane) {
        this.pane = pane;
        board = new Tile[8][8];
        // Draw must be constructed after board is initialized (has a reference to board
        this.d = new Draw();
        initTiles();
        initPieces();
        image = new Pane();
        pane.setCenter(image);
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

    /**
     * > first clear the current GUI display <br>
     * > add each element in the board again to reflect any changes that were made <br>
     */
    public void draw() {
        ((Pane) pane.getCenter()).getChildren().clear();
        ((Pane) pane.getCenter()).getChildren().addAll(d.drawBoard());
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

    final MouseEvent[] event = new MouseEvent[1];
    double mouseX, mouseY;
    private class Draw {
        // make a pane with the board
        Board b = Board.this;
        Tile[][] board = b.getBoard();

        public List<Node> drawBoard() {
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

            return boardImage.getChildren();
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
         *
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

        private double dX;
        private double dY;
        private void makeDraggable(Node... s) {
            for (Node node : s) {
                node.setOnMousePressed(e -> {
                    node.toFront();

                    Bounds bounds = image.localToScene(image.getBoundsInParent());
                    originX = bounds.getMinX() / 2;
                    originY = bounds.getMinY() / 2;
                    System.out.println(originX);
                    System.out.println(originY);

                    guiCol = (int) (e.getSceneX() - originX) / Tile.getWidth();
                    guiRow = (int) (e.getSceneY() - originY) / Tile.getWidth();
                    currentPiece = board[guiRow][guiCol].getPiece();
                    endCol = guiCol;
                    endRow = guiRow;
                    System.out.println(currentPiece);
                    node.setTranslateX(e.getSceneX() - originX - Tile.getWidth()/2.0);
                    node.setTranslateY(e.getSceneY() - originY - Tile.getWidth()/2.0);
                    System.out.println(guiRow + ":" + guiCol);
                });
                node.setOnMouseDragged(e -> {
                    node.setTranslateX(e.getSceneX() - originX - Tile.getWidth()/2.0);
                    node.setTranslateY(e.getSceneY() - originY - Tile.getWidth()/2.0);
                    endRow = (int) (e.getSceneY() - originY) / Tile.getWidth();
                    endCol = (int) (e.getSceneX() - originX) / Tile.getWidth();
                    System.out.println(endRow + " : " + endCol);
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