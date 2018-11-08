package org.bsheehan.algorithms.backtracking;

import org.bsheehan.BaseTest;
import org.junit.Test;

public class EightQueensTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        EightQueens game = new EightQueens();
        game.placeQueen(0);
        game.printBoard();
    }

}