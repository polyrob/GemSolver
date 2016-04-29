package com.robbomb.gemsolver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by x789997 on 4/29/2016.
 */
public class SolverTest {

    private Board board;

    @Test
    public void solveBoardTest() {
//0        XXXXXXXX
//1        XXXXXXXX
//2        XXXXXXXX
//3        XXXXXXXX
//4        XXXXXXXX
//5        XRXRRXXX
//6        XXXXXXXX
//7        XXXXXXXX

        Move move = Solver.solve(board);
        org.junit.Assert.assertNotNull(move);
        org.junit.Assert.assertNotNull(move.getFrom());
        org.junit.Assert.assertNotNull(move.getTo());
        assertTrue(move.getFrom().getX() == 2 || move.getTo().getX() == 2);
        assertTrue(move.getFrom().getY() == 5 || move.getTo().getY() == 5);
    }


    @Before
    public void init() {
        board = new Board(8,8);
        board.getGemAt(1,5).setGameColor(GameColor.COLOR_RED);
        board.getGemAt(2,5).setGameColor(GameColor.COLOR_BLUE);
        board.getGemAt(3,5).setGameColor(GameColor.COLOR_RED);
        board.getGemAt(4,5).setGameColor(GameColor.COLOR_RED);

    }
}
