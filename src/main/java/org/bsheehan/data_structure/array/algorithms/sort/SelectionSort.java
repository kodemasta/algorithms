package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Array;

// we keep swapping first position with min

public class SelectionSort {
    public static void sort(int arr[]) {

        for (int i = 0; i < arr.length; ++i) {
            int min = i;
            for (int j = i; j < arr.length; ++j) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            Array.swap(arr, i, min);
        }
    }
}
