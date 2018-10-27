package org.bsheehan.data_structure.array.algorithms.sort;

// stable - if same set sorted on different keys, previous sorting order not disrupted when new keys are same.
// Merge Sort is useful for sorting linked lists in O(nLogn) time.
// In case of linked lists the case is different mainly due to difference in memory allocation of arrays and linked lists.
// External sorting is a class of sorting algorithms that can handle massive amounts of data.
// External sorting is required when the data being sorted do not fit into the main memory of a computing device (usually RAM) and instead they must reside in the slower external memory, usually a hard disk drive.
// Step 1. divide total data into K chunks that fit into main memory and sort with quicksort into K sorted files on disk
// step 2. divide main memory into K input buffers and 1 output buffer
// step 3. perform K-Way merge into output buffer

public class MergeSort {

    public static void sort(int arr[])
    {
        // create one top level aux array to use for merge step. A one tim space cost of N
        int[] tmp = new int[arr.length];

        int inversions = mergeSort(arr, tmp, 0, arr.length - 1);

        System.out.println("total inversions: " + inversions);
    }

    // recursive
    private static int mergeSort(int arr[], int tmp[], int lo, int hi) {
        int inversions = 0;
        if (arr.length <= 1)
            return 0;

        if (lo >= hi)
            return 0;

        int mid = lo + (hi - lo) / 2;

        inversions += mergeSort(arr, tmp, lo, mid);
        inversions += mergeSort(arr, tmp, mid + 1, hi);

        inversions += merge(arr, tmp, lo, mid, hi);

        return inversions;
    }

    // assert both ranges (lo,mid) and (mid+1, hi) are sorted preconditions
    private static int merge(int a[], int tmp[], int lo, int mid, int hi) {
        int inversions = 0;
        // COPY INTO TEMP AUX ARRAY THEN MERGE BACK INTO SOURCE ARRAY !!
        for (int i = lo; i <= hi; i++)
            tmp[i] = a[i];

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) a[k] = tmp[j++]; // lower array exhausted
            else if (j > hi) a[k] = tmp[i++]; // upper array exhausted
            else if (tmp[i] <= tmp[j]) {
                a[k] = tmp[i++];
            }
            else {
                // the remainder of i to mid-1 are all larger than tmp[j] element so are each an inversion
                inversions += mid-i + 1;
                a[k] = tmp[j++];
            }
        }
        return inversions;
    }

    // optional non-recursive version
    public static void bottumUpSort(int arr[])
    {
        // create one top level aux array to use for merge step. A one tim space cost of N
        int[] tmp = new int[arr.length];
        int inversions = bottumUpMergeSort(arr, tmp);
        System.out.println("total inversions: " + inversions);

    }

    // iterative
    private static int bottumUpMergeSort(int arr[], int tmp[]) {
        int inversions = 0;
        for (int midStep = 1; midStep < arr.length; midStep = (2*midStep)) {
            for (int lo = 0; lo < arr.length-midStep; lo += (2*midStep) ){
                inversions += merge(arr, tmp, lo, lo+midStep-1, Math.min(lo+2*midStep-1, arr.length-1));
            }
        }

        return inversions;
    }
}
