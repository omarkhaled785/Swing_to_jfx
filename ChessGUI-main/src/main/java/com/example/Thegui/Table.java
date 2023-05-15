package com.example.Thegui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.chessgui.board.Board;
import com.example.chessgui.board.BoardStructure;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Table {

    private final Stage stage;
    private final Board chessBoard;
    private String pieceIconPath;
    private Color lightTileColor = Color.BEIGE;
    private Color darkTileColor = Color.BLACK;

    public Table() {
        this.stage = new Stage();
        Boardp boardPane = new Boardp();
        this.pieceIconPath = "art/simple/";
        this.chessBoard = Board.createStandardBoard();
        Scene scene = new Scene(boardPane, 600, 600);
        this.stage.setTitle("Chess Game");
        this.stage.setScene(scene);
        this.stage.show();
    }

    private class Boardp extends Pane {
        final ArrayList<Tilep> BoardTiles;

        static GridPane pane = new GridPane();

        public Boardp() {
            super(pane);
            this.BoardTiles = new ArrayList<>();
            for (int i = 0; i < BoardStructure.NUM_OF_TILES; i++) {
                final Tilep tilep = new Tilep(this, i);
                this.BoardTiles.add(tilep);
                pane.getChildren().add(tilep);
            }
            setWidth(400);
            setHeight(350);
        }
    }

    private class Tilep extends Pane {
        private final int tileId;
        static GridPane pane = new GridPane();

        public Tilep(final Boardp boardPane, final int tileId) {
            super(pane);
            this.tileId = tileId;
            setWidth(10);
            setHeight(10);
            assignTileColor();
            // assignTilePieceIcon(chessBoard);
        }

        /*
         * private void assignTilePieceIcon(Board board) {
         * this.getChildren().clear(); // Use 'getChildren' instead of 'removeAll'
         * 
         * if (board.getTile(this.tileId) != null) {
         * try {
         * String imagePath = pieceIconPath +
         * board.getTile(this.tileId).getPiece().toString().substring(0, 1) + "" +
         * board.getTile(this.tileId).toString() +
         * ".gif";
         * Image image = new Image(new FileInputStream(imagePath)); // Use 'Image'
         * instead of 'BufferedImage'
         * ImageView imageView = new ImageView(image);
         * this.getChildren().add(imageView); // Use 'getChildren' and 'add' instead of
         * 'add(new Label(new
         * // ImageIcon(image)))'
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * }
         * }
         */

        private void assignTileColor() {
            if (BoardStructure.FIRST_ROW[this.tileId] ||
                    BoardStructure.THIRD_ROW[this.tileId] ||
                    BoardStructure.FIFTH_ROW[this.tileId] ||
                    BoardStructure.SEVENTH_ROW[this.tileId]) {
                int x;
                x = (this.tileId % 2 == 0 ? 1 : 2);
                if (x == 1) {
                    // this.setBackground(new Background(new BackgroundFill(lightTileColor, null,
                    // null)));
                    System.out.println("H");

                } else {
                    // this.setBackground(new Background(new BackgroundFill(darkTileColor, null,
                    // null)));
                    System.out.println("N");
                }

            } else if (BoardStructure.SECOND_ROW[this.tileId] ||
                    BoardStructure.FOURTH_ROW[this.tileId] ||
                    BoardStructure.SIXTH_ROW[this.tileId] ||
                    BoardStructure.EIGHTH_ROW[this.tileId]) {
                int x;
                x = (this.tileId % 2 == 0 ? 1 : 2);
                if (x == 2) {
                    this.setBackground(new Background(new BackgroundFill(lightTileColor, null, null)));

                } else {
                    this.setBackground(new Background(new BackgroundFill(darkTileColor, null, null)));

                }
            }
        }
    }
}
