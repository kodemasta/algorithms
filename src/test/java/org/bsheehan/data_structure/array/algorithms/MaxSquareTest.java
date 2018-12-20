package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxSquareTest extends BaseTest {
    int a[];
    @Before
    public void setup() {
        a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 5);
    }

    @Test
    public void test() {
        super.test();
        //int a2[] = { 7, 4, 7, 5, 6};
        Array.print(a);
        Array.draw(a);
        System.out.println("MAX RECT AREA:" + MaxSquare.brute(a));
    }

    @Test
    public void brute() {
    }

    @Test
    public void efficient() {
    }
}