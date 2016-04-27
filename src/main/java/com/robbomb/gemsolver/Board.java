package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Board {

    private final int height;
    private final int width;
    private Gem[][] board;

    public Board(int rows, int columns) {
        this.width = rows;
        this.height = columns;
        board = new Gem[columns][rows];

        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                setGemAt(new Gem(), x, y);
            }
        }
    }

    public void setGemColorAt(int row, int column, Color c) {
        board[column][row].setBackground(c);
    }

    public Gem getGemAt(int row, int column) {
        return board[column][row];
    }

    public void setGemAt(Gem gem, int row, int column) {
        board[column][row] = gem;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
