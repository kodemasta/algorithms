package org.bsheehan.data_structure.array.algorithms.sort;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;

public class RadixSort {

    // LSD with bucket sort per radix
    public static void sortBucket(int arr[]) {
        final int BASE = 10;

        Integer maxVal = Integer.MIN_VALUE;
        for (Integer value: arr)
            maxVal = Math.max(value,maxVal);

        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from right to left by one
        for (int exp=1; maxVal >= exp; exp *= BASE) {
            BucketSort.sortRadix(arr,exp);
        }
    }

    // LSD with counting sort per radix
    public static void sortCounting(int arr[]) {
        final int BASE = 10;

        Integer maxVal = Integer.MIN_VALUE;
        for (Integer value: arr)
            maxVal = Math.max(value,maxVal);

        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from right to left by one
        for (int exp=1; maxVal >= exp; exp *= BASE) {
            CountingSort.sortRadix(arr,exp);
        }
    }
}
