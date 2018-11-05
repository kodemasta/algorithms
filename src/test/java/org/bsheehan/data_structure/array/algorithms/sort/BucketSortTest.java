package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BucketSortTest extends BaseTest {

    @Test
    public void test() {
        super.test();

        final int RANGE = 100;
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, RANGE, 15);
        Array.print(a);
        BucketSort.sort(a, RANGE);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }

    @Test
    public void testRadix() {
        super.test();

        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 0, 29, 15);
        Array.print(a);
        BucketSort.sortRadix(a, 1);
        Array.print(a);
        BucketSort.sortRadix(a, 10);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }
}