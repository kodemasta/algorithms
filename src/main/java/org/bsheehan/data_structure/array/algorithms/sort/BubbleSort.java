package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Array;

public class BubbleSort {

    public static void sort(int arr[]) {
        for (int i = 0; i < arr.length; ++i) {
            boolean swapped = false;
            for (int j = 0; j < arr.length-1; ++j ){
                if (arr[j] > arr[j+1]) {
                    // adjacent compare and swap
                    Array.swap(arr, j, j+1);
                    swapped = true;
                }
            }

            if (!swapped)
                return;
        }
    }
}
