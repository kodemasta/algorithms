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
        for (int i = 0; i < 100; i++) {
            int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
            Array.print(a);
            Quicksort.sort(a);
            Array.print(a);
            Assert.assertTrue("Sorted", Array.isSorted(a, true));
            System.out.println();
        }
    }

    @Test
    public void testSelect() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 100, 10);
        Array.print(a);
        int[] aCopy = Arrays.copyOf(a, a.length);
        Quicksort.sort(aCopy);
        Array.print(aCopy);


        int k = 5;
        int kVal = Quicksort.select(a,k);
        Array.print(a);
        System.out.println("K Select:" + k + " Value:" + kVal);
        Assert.assertEquals("Kth Matches", aCopy[k], kVal);
    }

    @Test
    public void test3Way() {
        super.test();

        for (int i = 0; i < 100; i++) {
            int[] a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 5, 10);
            Array.print(a);
            Quicksort.sort3way(a);
            Array.print(a);
            Assert.assertTrue("Sorted", Array.isSorted(a, true));
            System.out.println();
        }
    }
}