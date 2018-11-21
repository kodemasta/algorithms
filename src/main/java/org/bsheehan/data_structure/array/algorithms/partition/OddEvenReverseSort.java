package org.bsheehan.data_structure.array.algorithms.partition;

import java.util.Arrays;

// Even indexes decreasing a[i] <= a[j] for j < i ( all even elements are <= to left )
// Odd indexes increasing a[i] >= a[j] for j < i ( all odd elements are >= to left )
//
// Because we want evens to decrease and be smaller we need to use smaller half of sorted array
// Because we want odds to increase and be larger we need to use larger half of sorted array

public class OddEvenReverseSort {
    // function to rearrange the array
    public static void rearrange(int arr[])
    {
        int n = arr.length;

        // find split point of sorted array to iterate from (mid to left for even decreasing and mid to right for odd increasing)
        int sortedSplitIndex = n -  n/2; // n/2 even positions

        // sort the auxiliary array
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);

        int j = sortedSplitIndex - 1;

        // update arr with even indexes decreasing with lower half of sorted array
        // update left right evens
        for (int i = 0; i < n; i += 2) {
            arr[i] = sortedArr[j];
            j--;
        }

        j = sortedSplitIndex;

        // update arr with odd indexes increasing with upper half of sorted array
        // update form left to right odds
        for (int i = 1; i < n; i += 2) {
            arr[i] = sortedArr[j];
            j++;
        }
    }
}
