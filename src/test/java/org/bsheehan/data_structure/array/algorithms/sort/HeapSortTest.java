package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 15, 15);
        Array.print(a);
        HeapSort.sort(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));

    }

}