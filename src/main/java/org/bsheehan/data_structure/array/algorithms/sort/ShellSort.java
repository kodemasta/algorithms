package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Array;

public class ShellSort {
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
