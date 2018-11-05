package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class RadixSortTest extends BaseTest {

    @Test
    public void testBucket() {
        super.test();

        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 0, 29, 15);
        Array.print(a);
        RadixSort.sortBucket(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }

    @Test
    public void testCounting() {
        super.test();

        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 0, 29, 15);
        Array.print(a);
        RadixSort.sortCounting(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }
}