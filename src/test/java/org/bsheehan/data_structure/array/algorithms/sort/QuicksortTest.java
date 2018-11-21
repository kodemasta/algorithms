package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuicksortTest extends BaseTest{
    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 5);
        Array.print(a);
        Quicksort.sort(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }

    @Test
    public void testSelect() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED_UNIQUE, 100, 10);
        int[] aCopy = Arrays.copyOf(a, a.length);
        Quicksort.sort(aCopy);
        Array.print(aCopy);

        int k = 5;
        int kVal = Quicksort.select(a,k);
        System.out.println("K Select Value:" + kVal);
        Assert.assertEquals("Kth Matches", aCopy[k], kVal);
    }

    @Test
    public void test3Way() {
        super.test();
        int[] a = {0,1,2,0,1,2,0,1,2,0,1,2};
        Array.print(a);
        Quicksort.sort3way(a);
        Array.print(a);
        Assert.assertTrue("Sorted", Array.isSorted(a, true));
    }
}