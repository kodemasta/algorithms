package org.bsheehan.data_structure.hashmap.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.bsheehan.data_structure.linked_list.LinkedList;
import org.junit.Test;

public class MaxRangeSumTest extends BaseTest {

    @Test
    public void test() {
        super.test();

        int [] a = {1,2,3,4};
        Array.print(a);
        int targetSum = 7;
        System.out.println("target sum:" + targetSum);
        LinkedList<Integer, Integer> range = MaxRangeSum.find(a, targetSum);
        range.print(true);
        System.out.println();

        int [] a2 = {4,3,2,1};
        Array.print(a2);
        targetSum = 7;
        System.out.println("target sum:" + targetSum);
        range = MaxRangeSum.find(a2, targetSum);
        range.print(true);
        System.out.println();


        int [] a3 = {1,-1, 2, 1, 1, 1};
        Array.print(a3);
        targetSum = 4;
        System.out.println("target sum:" + targetSum);
        range = MaxRangeSum.find(a3, targetSum);
        range.print(true);
        System.out.println();

    }


}