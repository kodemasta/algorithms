package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Before;
import org.junit.Test;

public class ContiguousSumsTest extends BaseTest {
    int a[];

    @Before
    public void setup() {
        a = Array.create(Array.ArrayType.RANDOM_UNSORTED, 5, 5);
    }

    @Test
    public void test() {
        super.test();
        Array.print(a);
        ContiguousSums.findSums_n3(a);
        ContiguousSums.findSums_n2(a);
        ContiguousSums.findSums_n2_2(a);
        ContiguousSums.findMaxSum_dp(a);
        ContiguousSums.findMaxSum_dp2(a);

    }

}