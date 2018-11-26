package org.bsheehan.data_structure.array.algorithms.partition;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

public class PositiveNegativeTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] arr = Array.create(Array.ArrayType.RANDOM_UNSORTED, -10, 10, 10);

        Array.print(arr);
        PositiveNegative.partition(arr);
        Array.print(arr);
    }
}