package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Array;

// Invariant is to keep left side sorted as we move to the right
// bubble new right candidate to the left and swap
// Time: worst 0(n2)
// Time: avg O(n2)
// Time: best O(n) - if already sorted !

public class InsertionSort {

    public static void sort(int arr[]){
        for (int i = 0; i < arr.length; ++i) {
            // right to left from i
            for (int j = i; j > 0; j--) {
                // adjacent compare and swap
                if (arr[j] < arr[j-1])
                    Array.swap(arr,j,j-1);
            }
        }
    }
}
