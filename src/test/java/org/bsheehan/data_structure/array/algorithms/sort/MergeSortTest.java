package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 5);
        Array.print(a);
        MergeSort.sort(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }

    @Test
    public void bottumUpTest() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 15);
        Array.print(a);
        MergeSort.bottumUpSort(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }
}