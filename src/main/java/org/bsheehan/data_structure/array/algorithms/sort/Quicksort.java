package org.bsheehan.data_structure.array.algorithms.sort;



// It can be about two or three times faster than its main competitors, mergesort and heapsort.
//
// Quicksort is a comparison sort, meaning that it can sort items of any type for which a "less-than" relation (formally, a total order) is defined.
// In efficient implementations it is not a stable sort, meaning that the relative order of equal sort items is not preserved.
// Quicksort can operate in-place on an array, requiring small additional amounts of memory to perform the sorting.
//
// Mathematical analysis of quicksort shows that, on average, the algorithm takes O(n log n) comparisons to sort n items.
// In the worst case, it makes O(n2) comparisons, though this behavior is rare.
//
// Quicksort is a divide and conquer algorithm. Quicksort first divides a large array into two smaller sub-arrays: the low elements and the high elements. Quicksort can then recursively sort the sub-arrays.

// The steps are:
//      1 Pick an element, called a pivot, from the array.
//      2 Reorder the array so that all elements with values less than the pivot come before the pivot,
//          while all elements with values greater than the pivot come after it (equal values can go either way).
//      3 After this partitioning, the pivot is in its final position. This is called the partition operation.
//
// Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.

import org.bsheehan.data_structure.array.Array;

import java.util.Random;

public class Quicksort {

    public static void sort(int arr[])
    {
        quicksort(arr,0,arr.length - 1);
    }

    private static void quicksort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = lo + new Random().nextInt(hi - lo);
            int partitionIndex = partition(arr, pivotIndex, lo, hi);
            quicksort(arr, lo, partitionIndex - 1);
            quicksort(arr, partitionIndex + 1, hi);
        }
    }

    // median of medians pivot selection guarantees O(n) instead of just average (which has worst O(n2)
    public static int partition(int arr[], int pivotIndex, int firstIndex, int lastIndex){
        int pivot = arr[pivotIndex];
        Array.swap(arr, pivotIndex, lastIndex); // save pivot

        // only swap to left if smaller than pivot
        int storeIndex = firstIndex;
        for (int i = firstIndex; i < lastIndex; i++){
            if (arr[i] <= pivot){
                Array.swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        Array.swap(arr, storeIndex, lastIndex); // put back in place pivot

        return storeIndex;
    }
}
