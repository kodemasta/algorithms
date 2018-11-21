package org.bsheehan.data_structure.hashmap.algorithms;

import org.bsheehan.data_structure.linked_list.LinkedList;

import java.util.HashMap;

public class MaxRangeSum {

    // find largest range (index start to index finish) that has SUM
    // Not just any range. Largest range.
    // Not sorted
    // Can have negatives.
    // signed int range -2^31 and a maximum value of 2^31-1
    // unsigned int range 0 to 2^32
    // could be a single value

    public static LinkedList<Integer,Integer> find(int[] arr, int targetSum) {
        int sum = 0, maxWidth = 0; // max will stay at zero if targetSum not found
        HashMap<Integer, Integer> sum2indexMap = new HashMap<>();

        LinkedList<Integer,Integer> range = new LinkedList<>();


        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];

            // Handles simple case of sum from index 0 adding to necessary value.
            // simple case if sum is found from beginning
            if (sum == targetSum) {
                maxWidth = i + 1;
                range.clear();
                for (int j = 0; j < maxWidth; ++j ){
                    range.addBack(j, arr[j]);
                }
            }


            // store sums in a hashmap
            if (!sum2indexMap.containsKey(sum))
                sum2indexMap.put(sum, i);

            // Find a new range that sums to target.
            // We have been accumulating so if a new range has the sum we have to look for start of new range.
            // We do this by taking current sum and subtracting target and getting previous index. This will be the start of the
            // new range !

            if (sum2indexMap.containsKey(sum - targetSum)){
                int s = sum2indexMap.get(sum - targetSum);
                int width = i - s;
                if (width > maxWidth){
                    range.clear();
                    for (int j = s+1; j <= i; ++j ){
                        range.addBack(j, arr[j]);
                    }
                }
                //maxWidth = Math.max(maxWidth, width);
            }
        }

        return range;
    }
}
