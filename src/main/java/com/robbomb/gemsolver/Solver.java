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
                        // use >= so lower moves get better priority (these should score better)
                        if (bestMove == null || move.getRuns() >= bestMove.getRuns()) bestMove = move;
                    }
                }
            }
        }

        if (bestMove != null) {
            System.out.println("Best move: " + bestMove);
            System.out.println("Colors: " + board.getGemAt(bestMove.getFrom().x, bestMove.getFrom().y) + ", " +
                    board.getGemAt(bestMove.getTo().x, bestMove.getTo().y));
        } else {
            System.out.println("Could not determine best move.");
            System.out.println(board.toString());
        }
        return bestMove;
    }

    protected static Move swapAndCheck(Board board, Point p1, Point p2) {
        GameColor gem1 = board.getGemAt(p1.x, p1.y);
        GameColor gem2 = board.getGemAt(p2.x, p2.y);

        // If we don't know one, we can't solve
        if (gem1 == GameColor.UNKNOWN || gem2 == GameColor.UNKNOWN) return null;

        // Swap
        GameColor temp = gem2;

        board.setGemAt(gem1, p2.x, p2.y);
        board.setGemAt(gem2, p1.x, p1.y);


        Move m1 = checkMovesForGem(board, board.getGemAt(p1.x, p1.y), p1);
        Move m2 = checkMovesForGem(board, board.getGemAt(p2.x, p2.y), p2);

        // Revert
        board.setGemAt(gem2, p2.x, p2.y);
        board.setGemAt(gem1, p1.x, p1.y);

        // set Move pieces
        if (m1 != null) {
            m1.setFrom(p1);
            m1.setTo(p2);
        }

        if (m2 != null) {
            m2.setFrom(p1);
            m2.setTo(p2);
        }

        // best move?
        if (m1 == null) return m2;
        if (m2 == null) return m1;
        if (m1.getRuns() > m2.getRuns()) {
            return m1;
        } else {
            return m2;
        }
    }


    protected static Move checkMovesForGem(Board board, GameColor current, Point p) {
        int run = 1;
        Move bestMove = null;
        // check left
        while (p.x - run >= 0 && current == board.getGemAt(p.x - run, p.y)) {
            run++;
        }
        if (run > 2) bestMove = new Move(run);

        // check right
        run = 1;
        while (p.x + run < board.getHeight() && current == board.getGemAt(p.x + run, p.y)) {
            run++;
        }
        if (run > 2) if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(run);

        // check up
        run = 1;
        while (p.y - run >= 0 && current == board.getGemAt(p.x, p.y - run)) {
            run++;
        }
        if (run > 2) if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(run);

        //check down
        run = 1;
        while (p.y + run < board.getHeight() && current == board.getGemAt(p.x, p.y + run)) {
            run++;
        }
        if (run > 2) if (bestMove == null || run > bestMove.getRuns()) bestMove = new Move(run);

        return bestMove;
    }


}
