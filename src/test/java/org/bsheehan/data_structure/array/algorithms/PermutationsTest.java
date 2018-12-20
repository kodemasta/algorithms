package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

import java.util.List;

public class PermutationsTest extends BaseTest {

        @Test
        public void test() {
            super.test();
            int[] a = Array.create(Array.ArrayType.LINEAR_SORTED, 5, 4);
            List<List<Integer>> lists = Permutations.find(a);
            for (List<Integer> l: lists)
                System.out.println(l.toString());
            System.out.println("permutations:" + lists.size());
        }
}