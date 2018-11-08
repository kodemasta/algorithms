package org.bsheehan.data_structure.stack.algorithm;

import org.bsheehan.BaseTest;
import org.junit.Test;
import org.sheehan.algorithm.RangeMerge;
import org.sheehan.algorithm.data_structures.stack.Stack;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.bsheehan.data_structure.stack.algorithm.RangeMerge.Range;

public class RangeMergeTest extends BaseTest {
    @Test
    public void testSort() throws Exception {
        super.test();
        Set<RangeMerge.Range> ranges = new HashSet<RangeMerge.Range>();
        ranges.add(new RangeMerge.Range(5, 15));
        ranges.add(new RangeMerge.Range(4, 10));
        ranges.add(new RangeMerge.Range(3, 6));
        ranges.add(new RangeMerge.Range(1, 2));
        ranges.add(new RangeMerge.Range(25, 35));
        Stack<RangeMerge.Range> sorted = new RangeMerge().sort(ranges.toArray(new RangeMerge.Range[0]));

        sorted.print();
    }

}