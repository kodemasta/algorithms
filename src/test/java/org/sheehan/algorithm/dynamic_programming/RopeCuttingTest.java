package org.sheehan.algorithm.dynamic_programming;

import org.junit.Test;

public class RopeCuttingTest {

    @Test
    public void testMaxProductRecursive() throws Exception {

        System.out.println(RopeCutting.maxProductRecursive(8));
        System.out.println(RopeCutting.maxProductRecursive(10));

    }

    @Test
    public void testMaxProductDynamic() throws Exception {

        System.out.println(RopeCutting.maxProductDynamic(8));
        System.out.println(RopeCutting.maxProductDynamic(10));

    }
}