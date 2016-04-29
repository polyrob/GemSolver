package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Solver {

    public static Move solve(Board board) {
        //loop through each gem and find best move
        Move bestMove = null;    // dummy, this has to get overridden unless there is a problem solving

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Point p1 = new Point(x, y);

                // swap and check horizontal movements
                if (x < board.getWidth() - 1) {
                    Point p2 = new Point(x + 1, y);
                    Move move = swapAndCheck(board, p1, p2);
                    if (move != null) {
                        if (bestMove == null || move.getRuns() > bestMove.getRuns()) bestMove = move;
                    }
                }

                // swap and check vertical movements
                if (y < board.getHeight() - 1) {
                    Point p2 = new Point(x, y + 1);
                    Move move = swapAndCheck(board, p1, p2);
                    if (move != null) {
                        if (bestMove == null || move.getRuns() > bestMove.getRuns()) bestMove = move;
                    }
                }
            }
        }

        return bestMove;
    }

    protected static Move swapAndCheck(Board board, Point p1, Point p2) {
        Gem gem1 = board.getGemAt(p1.x, p1.y);
        Gem gem2 = board.getGemAt(p2.x, p2.y);

        // If we don't know one, we can't solve
        if (gem1.getGameColor() == GameColor.UNKNOWN || gem2.getGameColor() == GameColor.UNKNOWN) return null;

        // Swap
        GameColor temp = board.getGemAt(p1.x, p1.y).getGameColor();
        gem1.setGameColor(board.getGemAt(p2.x, p2.y).getGameColor());
        gem2.setGameColor(temp);

        Move m1 = checkMovesForGem(board, board.getGemAt(p1.x, p1.y).getGameColor(), p1, p2);
        Move m2 = checkMovesForGem(board, board.getGemAt(p2.x, p2.y).getGameColor(), p1, p2);

        // Revert
        gem2.setGameColor(gem1.getGameColor());
        gem1.setGameColor(temp);

        // best move?
        if (m1 == null) return m2;
        if (m2 == null) return m1;
        if (m1.getRuns() > m2.getRuns()) {
            return m1;
        } else {
            return m2;
        }
    }


    protected static Move checkMovesForGem(Board board, GameColor current, Point p1, Point p2) {
        int run = 1;
        Move bestMove = null;
        // check left
        while (p1.x - run >= 0 && current == board.getGemAt(p1.x - run, p1.y).getGameColor()) {
            run++;
        }
        if (run > 2) {
            printSolution(p1, p2, run);
            bestMove = new Move(p1, p2, run);
        }

        // check right
        run = 1;
        while (p1.x + run < board.getHeight() && current == board.getGemAt(p1.x + run, p1.y).getGameColor()) {
            run++;
        }
        if (run > 2) {
            printSolution(p1, p2, run);
            if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(p1, p2, run);
        }

        // check up
        run = 1;
        while (p1.y - run >= 0 && current == board.getGemAt(p1.x, p1.y - run).getGameColor()) {
            run++;
        }
        if (run > 2) {
            printSolution(p1, p2, run);
            if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(p1, p2, run);
        }

        //check down
        run = 1;
        while (p1.y + run < board.getHeight() && current == board.getGemAt(p1.x, p1.y + run).getGameColor()) {
            run++;
        }
        if (run > 2) {
            printSolution(p1, p2, run);
            if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(p1, p2, run);
        }
        return bestMove;
    }


    protected static void printSolution(Point p1, Point p2, int value) {
//        System.out.println("Found a run of " + value + ". Point (" + p1.x + ", " + p1.y + ") with (" + p2.x + ", " + p2.y + ")");
    }
}
