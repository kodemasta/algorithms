package org.bsheehan.data_structure.stack.algorithm;

import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;

import java.util.Arrays;

public class RangeMerge {

    ///////////////////////////////////////////////////////////////
    public class Range implements Comparable<Range> {

        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range range) {
            return start - range.start;
        }

        public boolean overlaps(Range range) {
            if (range.start >= start && range.start <= end)
                return true;
            if (range.end <= end && range.end >= start)
                return true;
            return false;
        }

        public void merge(Range range) {
            if (range.start < start)
                start = range.start;
            if (range.end > end)
                end = range.end;
        }
    }


    /////////////////////////////////////////////////
    // 1. sort ranges on starting time
    // 2. check stack
    // 2a. if empty add
    // 2b. if overlap merge.. don't add (modified).. otherwise add in no overlap
    // 3. end up with a stack with all ranges .. NO OVERLAPS in order LIFO
    public static Stack<Range> sort(Range ranges[]){
        Arrays.sort(ranges);

        Stack<Range> stack = new StackArrayImpl<>(ranges.length);
        for (Range range : ranges){
            if (stack.peek() == null)
                stack.push(range); //initial range
            else {
                Range top = stack.peek(); // look at most recent queue range
                if (top.overlaps(range)){ //compare to iterated range
                    top.merge(range); //update top range
                } else {
                    stack.push(range); //enqueue iterated range
                }
            }
        }
        return stack;
    }
}
