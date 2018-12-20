package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

public class SubsetsTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int[] a = Array.create(Array.ArrayType.RANDOM_SORTED_UNIQUE, 15, 10);
        Array.print(a);
        Subsets.find(a);
    }
}