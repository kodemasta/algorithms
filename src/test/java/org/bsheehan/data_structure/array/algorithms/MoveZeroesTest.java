package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class MoveZeroesTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 5, 20);
        Array.print(a);
        MoveZeroes.moveZeroes(a);
        Array.print(a);
    }
}