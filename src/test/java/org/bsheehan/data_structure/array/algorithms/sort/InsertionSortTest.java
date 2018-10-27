package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest  extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 5);
        Array.print(a);
        InsertionSort.sort(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }
}