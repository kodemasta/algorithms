package org.bsheehan.algorithms.dp;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

public class SubsetSumTest  extends BaseTest {

    @Test
    public void test() {
        super.test();
        int [] arr = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 5);
        //int arr[] = { 2, 3, 3, 9, 5, 8, 9, 2, 6, 4 };
        Array.print(arr);
        System.out.println("15 sum found:" + SubsetSum.dp(arr, 15));
        System.out.println("15 sum number of sets:" + SubsetSum.recursive(arr, 15));
    }
}