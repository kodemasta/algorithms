package org.sheehan.algorithm;

import junit.framework.TestCase;
import org.junit.Test;
import org.sheehan.algorithm.data_structures.stack.Stack;

import java.util.HashSet;
import java.util.Set;

public class RangeMergeTest extends TestCase {

    @Test
    public void testSort() throws Exception {
        Set<RangeMerge.Range> ranges = new HashSet<RangeMerge.Range>();
        ranges.add(new RangeMerge.Range(5, 15));
        ranges.add(new RangeMerge.Range(4, 10));
        ranges.add(new RangeMerge.Range(3, 6));
        ranges.add(new RangeMerge.Range(1, 2));
        ranges.add(new RangeMerge.Range(25, 35));
        Stack<RangeMerge.Range> sorted = new RangeMerge().sort(ranges.toArray(new RangeMerge.Range[0]));

        sorted.print();

        ranges = new HashSet<RangeMerge.Range>();
        ranges.add(new RangeMerge.Range(5, 15));
        ranges.add(new RangeMerge.Range(4, 10));
        ranges.add(new RangeMerge.Range(3, 6));
        ranges.add(new RangeMerge.Range(1, 2));
        ranges.add(new RangeMerge.Range(25, 35));
        java.util.List<RangeMerge.Range> sorted2 = new RangeMerge().sort2(ranges);
        for (RangeMerge.Range r:sorted2)
            System.out.println(r);

    }
}