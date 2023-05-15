package com.example.chessgui.board;

public class BoardStructure {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] THIRD_COLUMN = initColumn(2);
    public static final boolean[] FOURTH_COLUMN = initColumn(3);
    public static final boolean[] FIFTH_COLUMN = initColumn(4);
    public static final boolean[] SIXTH_COLUMN = initColumn(5);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    public static final boolean[] FIRST_ROW = initRow(0);
    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] THIRD_ROW = initRow(16);
    public static final boolean[] FOURTH_ROW = initRow(24);
    public static final boolean[] FIFTH_ROW = initRow(32);
    public static final boolean[] SIXTH_ROW = initRow(40);
    public static final boolean[] SEVENTH_ROW = initRow(48);
    public static final boolean[] EIGHTH_ROW = initRow(56);
    public static final int NUM_OF_TILES = 64;
    public static final int TILES_PER_ROW = 8;

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] columns = new boolean[NUM_OF_TILES];
        do {
            columns[columnNumber] = true;
            columnNumber += TILES_PER_ROW;
        } while (columnNumber < NUM_OF_TILES);
        return columns;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NUM_OF_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while (rowNumber < NUM_OF_TILES);
        return row;
    }

    public static boolean isValidCoordinate(int tileCoordinate) {
        return tileCoordinate >= 0 && tileCoordinate < NUM_OF_TILES;
    }
}
