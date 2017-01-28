package com.robbomb.gemsolver;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Board {

    private final int height;
    private final int width;
    private GameColor[][] board;
    private int startX;
    private int startY;
    private float deltaX;
    private float deltaY;

    public Board(int rows, int columns) {
        this.width = rows;
        this.height = columns;
        board = new GameColor[columns][rows];

        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                setGemAt(GameColor.UNKNOWN, x, y);
            }
        }
    }

    public GameColor getGemAt(int row, int column) {
        return board[column][row];
    }

    public void setGemAt(GameColor color, int row, int column) {
        board[column][row] = color;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartY() {
        return startY;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public float getDeltaY() {
        return deltaY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(board[y][x]).append('|');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
