package org.bsheehan.algorithms.backtracking;

import org.bsheehan.BaseTest;
import org.junit.Test;

public class NQueensTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        NQueens game = new NQueens();
        game.placeQueen(0);
        game.printBoard();
    }

}