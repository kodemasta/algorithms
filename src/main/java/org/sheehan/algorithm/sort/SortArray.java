package org.sheehan.algorithm.sort;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.array.Array;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;
import org.sheehan.algorithm.data_structures.tree.BinaryHeap;


import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 5/20/14
 * Time: 7:43 AM
 * To change this template use File | Settings | File Templates.
 */

interface SwapCallback {
    <T extends Comparable<T>> boolean swap(T[] array, int i, int j);
}

class SwapCallbackValue implements SwapCallback{
    public <T extends Comparable<T>> boolean swap(T[] array, int i, int j) {
        if (array[i].compareTo(array[j]) > 0) {
            T tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            return true;
        }
        return false;
    }
}

public class SortArray {

    /////////////////////////////////////////////////////////////////////////////////
    // BUBBLE SORT
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(n2)
    // avg O(n2)
    // best O(n)
    public static void bubbleSort(Integer []array) {

        int n = array.length - 1;

        SwapCallbackValue swabCallback = new SwapCallbackValue();

        boolean swapped = true;
        // repeat until no more swaps
        while (swapped) {
            swapped = false;

            for (int i = 0; i < n; ++i) {
                swapped |= swabCallback.swap(array, i, i+1);

            }
            n = n - 1; //optimization
        }
    }


    public static void oddEvenSort(Integer []array) {

        int i=0;
        int j=array.length-1;
        while(i<j){

            // find next odd on left
            while(array[i]%2==0)
                ++i;
            //find next even on right
            while(array[j]%2==1)
                --j;

            // if found swappable elements then swap
            if (i<j && array[i]%2!=0 && array[j]%2!=1) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }

            // whether we swap or not need to increment and continue scan from left and right
            i++;
            j--;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // BUBBLE SORT Even and Odd
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(n2)
    // avg O(n2)
    // best O(n)
    public static void bubbleSortPolarity(Integer []array) {

        int n = array.length - 1;
        SwapCallbackValue swabCallback = new SwapCallbackValue();

        boolean swapped = true;
        // repeat until no more swaps
        while (swapped) {
            swapped = false;

            for (int i = 0; i < n; ++i) {
                //swapped |= swabCallback.swap(array, i, i+1);

                if ((array[i]&1)==1 && (array[i+1]&1)==0) {
                    Integer tmp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = tmp;
                    swapped = true;

                }
            }
            n = n - 1; //optimization
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // INSERTION SORT
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(n2)
    // avg O(n2)
    // best O(n) - if already sorted !
    // compares each new element against already sorted elements
    public static <T extends Comparable<T>> void insertionSort(T array[]){
        SwapCallbackValue swabCallback = new SwapCallbackValue();
        int n = array.length;

        // starting index to start from right and move left from
        for (int i = 0; i < n; ++i){
            // move left from i swapping as you go
            for (int j = i; j > 0; j--){
                if (array[j-1].compareTo(array[j]) > 0) {
                    T tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    // this variation gets the insertBefore position then moves then sorted array down by one from the insertpoint
    public static <T extends Comparable<T>> void insertionSort2(T array[]){
         int n = array.length;

        // starting index to start from right and move left from
        for (int i = 0; i < n; ++i){
            // move from right and find insert point
            int insertAt = i;
            for (int j = i-1; j >= 0; j--){
                if (array[j].compareTo(array[i]) > 0) {
                    insertAt = j;
                }
            }

            // save as it will be written over
            T val = array[i];

            // slide em over to the right
            for (int j = i; j > insertAt; j--){
                array[j] = array[j-1];
            }

            //now set it where it belongs !
            array[insertAt] = val;

        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // INSERTION SORT
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(n2)
    // avg O(n2)
    // best O(n) - if already sorted !
    // compares each new element against already sorted elements
    public static <T extends Comparable<T>> void insertionSortParity(T array[]){
        SwapCallbackValue swabCallback = new SwapCallbackValue();
        int n = array.length;

        // starting index to start from right and move left from
        for (int i = 1; i < n; ++i){
            // move left from i swapping as you go
            for (int j = i; j > 0; j--){
                if (array[j].compareTo(array[j - 1]) < 0)
                    swabCallback.swap(array, j, j - 1);
            }
        }
    }


    /////////////////////////////////////////////////////////////////////////////////
    // HEAP SORT
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(nlogn)
    // avg O(nlogn)
    // best O(nlogn)
    public static void heapSort(Integer []array) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(array.length, BinaryHeap.HeapType.MIN_HEAP);
        heap.heapify(array);
        Integer sortedArray[] = new Integer[array.length];
        Integer value;
        int cnt = 0;
        while ((value=heap.pop()) != null) {
            sortedArray[cnt++] = value;
        }
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }

    /////////////////////////////////////////////////////////////////////////////////
    // SELECTION SORT - recursive
    // worst 0(n^2)
    // avg O(n^2)
    // best O(n^2)
    // in-place
    // if compares are cheaper than swaps may be better !
    // not stable
    ///////////////////////////////////////////////////////////////////////////////
    // loop finding minimum element and move to next position at front
    public static void selectionSort(Integer array[]) {
        //compare ith element to the min element in remainder and swap if necessary
        for (int i = 1; i < array.length; ++i) {
            int iMin = i;
            for (int j = i+1; j < array.length; ++j) {
                if (array[j] < array[iMin])
                    iMin = j;

            }

            if (i != iMin)
                swap(array, i, iMin);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // SELECTION SORT - recursive
    // worst 0(n^2)
    // avg O(n^2)
    // best O(n^2)
    // in-place
    // if compares are cheaper than swaps may be better !
    // not stable
    ///////////////////////////////////////////////////////////////////////////////
    // loop finding minimum element and move to next position at front
    public static void selectionSortRecursive(Integer array[], int start) {
        if (start < array.length){
            int iMin = start;
            // (n-1) + (n-2) + (n-3) + .. +1 = n(n-1)/2 --> O(n^2)
            for (int j = start+1; j < array.length; ++j) {
                if (array[j] < array[iMin])
                    iMin = j;

            }

            if (start != iMin)
                swap(array, start, iMin);

            selectionSortRecursive(array, start+1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // MERGE SORT
    /////////////////////////////////////////////////////////////////////////////////
    public static void mergeSort(int array[]) {
        int []tempMergArr = new int[array.length];
        mergeSort(array, tempMergArr, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int mergeSort(int array[], int tmpArray[], int lo, int hi)
    {
        int inversions = 0;
        if (array.length <= 1)
            return 0;

        if (lo >= hi)
            return 0;

        int m = lo + (hi-lo)/2;
        //int size2 = array.length - size1;
      //  T array1[] = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, size1);
      //  T array2[] = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, size2);

      //  System.arraycopy(array, tmpArray0, array1, 0, size1);
      //  System.arraycopy(array, tmpArray,size1, array2, 0, size2);

        inversions += mergeSort(array, tmpArray, lo, m);
        inversions += mergeSort(array, tmpArray, m+1, hi);

        //overwrite array with merge
        //todo inversions
        mergeParts(array, tmpArray, lo, m, hi);
        System.out.println("inversions: " + inversions);

        return inversions;
    }

    private static void mergeParts(int array[], int tmpArray[], int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {//***** important
            tmpArray[i] = array[i];
        }

        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tmpArray[i] <= tmpArray[j]) {
                array[k] = tmpArray[i];
                i++;
            } else {
                array[k] = tmpArray[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tmpArray[i];
            k++;
            i++;
        }

    }


    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }


    // simplified for ints
    public static int[] mergeSort2(int []array) {
        if (array.length <= 1) {
            return array;
        }

        // Split the array in half
        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];

        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        // SortArray each half
        mergeSort2(first);
        mergeSort2(second);

        // Merge the halves together, overwriting the original array
        merge2(first, second, array);
        return array;
    }

    private static void merge2(int[] array1, int[]array2, int[]array){

        int i, index1, index2;
        i = 0;
        index1 = 0;
        index2 = 0;

        // merge COMMON LENGTH of 2 arrays
        // this merge actually SORTS the arrays !
        // this will exhaust one of the two arrays
        while(index1 < array1.length && index2 < array2.length){
            if (array1[index1] < array2[index2]) {
                array[i++] = array1[index1++];
            } else {
                array[i++] = array2[index2++];
            }
        }

        // one will have left overs...

        // merge the LEFT OVER array portion
        while(index1 < array1.length){
            array[i++] = array1[index1++];
        }
        // if remaining array2 not merged
        while(index2 < array2.length){
            array[i++] = array2[index2++];
        }
    }




    /////////////////////////////////////////////////////////////////////////////////
    // QUICKSORT
    //
    // It can be about two or three times faster than its main competitors, merge sort and heapsort.
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
    /////////////////////////////////////////////////////////////////////////////////
    public static <T extends Comparable<T>> void quicksort(Integer[] array, int left, int right){
        if (left < right) {
            int partitionIndex = Array.partition(array, left, right);
            quicksort(array, left, partitionIndex - 1);
            quicksort(array, partitionIndex + 1, right);
        }
    }


    public static <T extends Comparable<T>> int quickselectKthStatistic(Integer[] array, int left, int right, int k){
        if (left <= right) {
            int partitionIndex = Array.partition(array, left, right);
            if (partitionIndex == k)
                return partitionIndex;
            else if (k < partitionIndex)
                return quickselectKthStatistic(array, left, partitionIndex - 1, k);
            else
                return quickselectKthStatistic(array, partitionIndex + 1, right, k);
        }

        return -1;
    }

    /////////////////////////////////////////////////////////////////////////////////
    // COUNTING SORT
    /////////////////////////////////////////////////////////////////////////////////
    private static final int MAX_RANGE = 1000000;
    public static void countingSort(Integer array[]) {

        if (array.length == 0)
            return;

        /** find max and min values **/
        int max = array[0], min = array[0];

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] > max)
                max = array[i];

            if (array[i] < min)
                min = array[i];
        }

        final int range = max - min + 1;

        /** check if range is small enough for count array **/
        /** else it might give out of memory exception while allocating memory for array **/
        if (range > MAX_RANGE)
        {
            System.out.println("\nError : Range too large for sort");
            return;

        }
        Integer counts[] = new Integer[range];
        Integer output[] = new Integer[array.length];

        Arrays.fill(counts, 0);

        // histogram
        // how many of each element is there ?
        for (int i = 0; i < array.length; ++i) {
            counts[array[i]-min] += 1;
        }

        // accumulate - "prefix sum" counts[i] contains # of values <=
        // counts[i]
        // it instead stores the number of items with key less than i,
        // which is the same as the first index at which an item with key i should be stored in the output array.
        for (int i = 1; i < range; ++i) {
            counts[i] += counts[i - 1];
        }

        // execute sorted output
        for (int i = 0 ; i < array.length; i++) {
            counts[array[i] - min] -= 1; // decrement count
            //System.out.println("# items <= " + array[i] + " is " + counts[array[i] - min]);
            //System.out.println("setting output index " + counts[array[i] - min] + " to " + array[i]);
            output[counts[array[i] - min]] = array[i];

        }

        // copy output back to array
        System.arraycopy( output, 0, array, 0, array.length );
    }

    /////////////////////////////////////////////////////////////////////////////////
    // RADIX SORT
    // LSD on integer keys
    // BASE = 10
    /////////////////////////////////////////////////////////////////////////////////
    public static void radixSort(Integer array[]) {
        final int BASE = 10;
        int numBuckets = BASE;

        List<QueueInterface<Integer>> buckets = new ListImpl<QueueInterface<Integer>>();
        for (int i = 0; i < numBuckets; i++){
            buckets.appendBack(new QueueArrayImpl<Integer>(array.length));
        }

        Integer max = Integer.MIN_VALUE;
        for (Integer value: array)
            max = Math.max(value,max);


        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from right to left by one
        for (int positionMultiplier=1; max >= positionMultiplier; positionMultiplier *= BASE) {
            // each pass checks a rt to left position and buckets based on that digit
            for (Integer value : array){
                int valueDiv = value/positionMultiplier;
                int valueMod = valueDiv%BASE;
                buckets.get(valueMod).enqueue(value);
            }

            // reset array to new order after sorting this pass
            // the new order is obtained by removing elements from the bucket queues in FIFO order
            // starting from least valued bucket

            for (int bucketIndex = 0, i = 0; bucketIndex < numBuckets; ++bucketIndex){
                QueueInterface<Integer> bucket = buckets.get(bucketIndex);
                Integer value;
                while ((value = bucket.dequeue()) != null){
                    array[i++] = value;
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // RADIX SORT
    // LSD on integer keys (left to right)
    // BASE = 2
    /////////////////////////////////////////////////////////////////////////////////
    public static void radixSortBinaryLsd(Integer array[]) {
        final int BASE = 2;
        final int numBuckets = 2;

        List<QueueInterface<Integer>> buckets = new ListImpl<QueueInterface<Integer>>();
        for (int i = 0; i < numBuckets; i++){
            buckets.appendBack(new QueueArrayImpl<Integer>(array.length));
        }

        int MASK = 0x00000001;

        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from right to left by one
        for (int position=0; position < Integer.SIZE; position++) {
            // each pass checks a rt to left position and buckets based on that digit
            for (Integer value : array){
                int bitValue = value & MASK;
                bitValue >>>= position;
                buckets.get(bitValue).enqueue(value);
            }
            MASK <<= 1;


            // reset array to new order after sorting this pass
            // the new order is obtained by removing elements from the bucket queues in FIFO order
            // starting from least valued bucket
            int i = 0;
            for (int bucketIndex = 0; bucketIndex < numBuckets; ++bucketIndex){
                QueueInterface<Integer> bucket = buckets.get(bucketIndex);
                Integer value;
                while ((value = bucket.dequeue()) != null){
                    array[i++] = value;
                }
            }
        }
    }


}
