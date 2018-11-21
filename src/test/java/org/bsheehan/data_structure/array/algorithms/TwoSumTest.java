package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

public class TwoSumTest extends BaseTest {

    @Test
    public void testSortedArray() {
        super.test();
        int arr[] = {1,3,7};
        Array.print(arr);
        int targetSum = 10;
        boolean sumFound = TwoSum.findSumSorted(arr, targetSum);
        System.out.println("sum found for:" + targetSum + " " + sumFound);
    }

    @Test
    public void testUnSortedArray() {
        super.test();
        int arr[] = {1,7,3};
        Array.print(arr);
        int targetSum = 5;
        boolean sumFound = TwoSum.findSumUnsorted(arr, targetSum);
        System.out.println("sum found for:" + targetSum + " " + sumFound);
    }

}