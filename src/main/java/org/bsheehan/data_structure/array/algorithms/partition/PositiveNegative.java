package org.bsheehan.data_structure.array.algorithms.partition;

import org.bsheehan.data_structure.array.Array;

public class PositiveNegative {

    public static void partition(int arr[]){
        int left = 0;
        int right = arr.length-1;

        // partition <0 to left
        int partitionIndex = Array.partition(arr, -1, left, right);
        Array.print(arr);
        System.out.println("partition index:" + partitionIndex);

        // can only interleave if we have enough
        int lim = Math.min(partitionIndex, arr.length-partitionIndex);

        // interleave at partition boundary
        for (int i = 0; i < lim; ++i){
            if (i%2 == 1) {
                Array.swap(arr, i,  partitionIndex++);
            }
        }
    }
}
