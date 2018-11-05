package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.bsheehan.data_structure.array.algorithms.sort.SelectionSort;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShuffleTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.LINEAR_SORTED, 52, 52);
        Array.print(a);
        Shuffle.shuffle(a);
        Array.print(a);
        Assert.assertTrue("Not Sorted", !Array.isSorted(a, true));
    }
}