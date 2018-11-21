package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.LINEAR_SORTED, 52, 52);
        Array.print(a);
        int result = BinarySearch.search(a, 20);
        System.out.println(result);
    }
}