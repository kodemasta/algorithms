package org.sheehan.algorithm;


import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;

import java.util.*;


/**
 * Created by bsheehan on 9/10/2014.
 */
public class RangeMerge {

    static public class Range implements Comparable<RangeMerge.Range> {

        private int start;
        private int end;

        public Range(int start, int end){
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
            if (range.end > end) {
                end = range.end;

            }
        }

        @Override
        public String toString(){
           return "range: " + start + "-" + end;
        }
    }

//    with queue
//    1. SortArray the intervals based on increasing order of starting time.
//    2. Push the first interval on to a queue.
//    3. For each interval do the following
//      a. If the current interval does not overlap with the queue top, push it.
//      b. If the current interval overlaps with queue top and ending
//          time of current interval is more than that of queue top,
//          update queue top with the ending  time of current interval.
//    4. At the end queue contains the merged intervals.
    public Stack<Range> sort(Range ranges[]){

        Arrays.sort(ranges);

        Stack<Range> stack = new StackArrayImpl<>(ranges.length);
        for (Range range : ranges){
            if (stack.peek() == null)
                stack.push(range); //initial range
            else {
                Range top = stack.peek(); // look at most recent queue range
                if (top.overlaps(range)){ //compare to iterated range
                    top.merge(range); //update top range
                }else {
                    stack.push(range); //enqueue iterated range
                }
            }
        }
        return stack;
    }

    // without queue
    public List<Range> sort2(Set<Range> ranges){

        //convert to sorted List, Set cannot be sorted.
        List<Range> listRanges = new ArrayList<>();
        listRanges.addAll(ranges);
        Collections.sort(listRanges);

        // first sortBucket ranges by starting point
        Iterator<Range> iterator = listRanges.iterator();
        Range curr = iterator.next();
        while(iterator.hasNext()){
          Range next = iterator.next();
          if (next.start <= curr.end) {
              curr.merge(next);
              iterator.remove();
          } else {
              curr = next;
          }
        }
        return listRanges;
    }


}
