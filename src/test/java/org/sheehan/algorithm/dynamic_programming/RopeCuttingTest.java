package org.sheehan.algorithm.dynamic_programming;

import org.junit.Test;

public class RopeCuttingTest {

    @Test
    public void testMaxProductRecursive() throws Exception {

        System.out.println(RopeCuttingMaxProduct.recursive(8));
        System.out.println(RopeCuttingMaxProduct.recursive(10));

    }

    @Test
    public void testMaxProductDynamic() throws Exception {

        System.out.println(RopeCuttingMaxProduct.dp(8));
        System.out.println(RopeCuttingMaxProduct.dp(10));

    }
}