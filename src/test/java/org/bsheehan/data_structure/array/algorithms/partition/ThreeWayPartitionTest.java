package org.bsheehan.data_structure.array.algorithms.partition;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;


import static org.junit.Assert.*;

public class ThreeWayPartitionTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] arr = Array.create(Array.ArrayType.RANDOM_UNSORTED, 3, 10);

        Array.print(arr);
        ThreeWayPartition.partition(arr, 0, 1);
        Array.print(arr);
    }
}