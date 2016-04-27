package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Solver {

    public static void solve(Board board) {
        //loop through each gem and find best move

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Gem gem = board.getGemAt(x, y);
                Point p1 = new Point(x, y);

                // check left
                if (x > 0) {

                }

                // check right
                if (x < board.getWidth() - 1) {
                    Point p2 = new Point(x + 1, y);
                    swapAndCheck(board, p1, p2);
                }
            }
        }


    }

    protected static void swapAndCheck(Board board, Point p1, Point p2) {

        // Swap
        Gem gem1 = board.getGemAt(p1.x, p1.y);
        Gem gem2 = board.getGemAt(p2.x, p2.y);
        GameColor temp = board.getGemAt(p1.x, p1.y).getGameColor();
        gem1.setGameColor(board.getGemAt(p2.x, p2.y).getGameColor());
        gem2.setGameColor(temp);

        // Check horizontals  - check 0,1,2 - then 1,2,3 ... 5,6,7
        int run = 1;
        GameColor current;
//        for (int y = 0; y < board.getHeight(); y++) {
//            for (int x = 0; x < board.getWidth(); x++) {

        current = board.getGemAt(p1.x, p1.y).getGameColor();

        // check left from first
        run = 1;
        while (p1.x - run >= 0 && current == board.getGemAt(p1.x - run, p1.y).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);

        // check right from second
        run = 1;
        while (p2.x + run < board.getHeight() && current == board.getGemAt(p2.x + run, p2.y).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);

        // check up from first
        run = 1;
        while (p1.y - run >= 0 && current == board.getGemAt(p1.x, p1.y - run).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);

        //check down from first
        run = 1;
        while (p1.y + run < board.getHeight() && current == board.getGemAt(p1.x, p1.y + run).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);


        // check up from second
        run = 1;
        while (p2.y - run >= 0 && current == board.getGemAt(p2.x, p2.y - run).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);

        //check down from second
        run = 1;
        while (p2.y + run < board.getHeight() && current == board.getGemAt(p2.x, p2.y + run).getGameColor()) {
            run++;
        }
        if (run > 2) printSolution(p1, p2, run);

//            }
//        }

        // Revert
        gem2.setGameColor(gem1.getGameColor());
        gem1.setGameColor(temp);
    }

    protected static void printSolution(Point p1, Point p2, int value) {
        System.out.println("Found a run of " + value + ". Point (" + p1.x + ", " + p1.y + ") with (" + p2.x + ", " + p2.y + ")");
    }
}
