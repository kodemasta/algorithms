package org.bsheehan.data_structure.string.algorithm;

// Counting sortBucket is a sorting technique based on keys between a specific range.
// It works by counting the number of objects having distinct key values (kind of hashing).

// sortBucket an array of keys which may be used to index nodes in an array.
// 1. calculate the range of the input
// 1. create histogram of elements, size of range
// 2. turn histogram into accumlation of elements

// COMPLEXITY
// SPACE - N+R (size of array + radix or numeric range. (e.g. ascii is 256)
// STABLE
// Counts array is like a bucket for each key value - fine grained buckets. Each bucket is a counter.
// You can think of counting sortBucket as an upper bound bucket sortBucket
// Need to know the range before hand (min and max) to get number of counter buckets !!
// Can't work with floating point numbers
// Counting sortBucket is a sorting technique based on keys between a specific range.

// It works by counting the number of objects having distinct key values (kind of hashing).

public class CountingSort {

    // sort based on digit position relative to from right most LSD (position 0)
    // used by RadixSort LSD
    // exp is the value required to select a specific digit position out of the integer
    public static void sortRadix(String arr[], final int pos) {

        final int BASE = 256;
        if (arr.length == 0)
            return;

        int counts[] = new int[BASE]; // for int digit base 10
        String temp[] = new String[arr.length];


        // HISTOGRAM of keys (values of array are keys in this case)
        // how many of each element is there ?
        // counts array will fall in range event hough we iterate over source array length
        for (int i = 0; i < arr.length; ++i) {
            counts[Character.getNumericValue(arr[i].charAt(pos))] += 1;
        }

        // ACCUMULATE keys
        // the position in counts references the value (sorting key) in input array (
        // the value at the position in counts references the sorted position in output array
        for (int i = 1; i < BASE; ++i) {
            counts[i] += counts[i-1];
        }

        // SORT
        // jump around to fill array based on accumulated counts !
        // get funky if you iterate forward
        for (int i = arr.length-1 ; i >=0 ; i--) {
            int keyIndex = Character.getNumericValue(arr[i].charAt(pos));

            // decrement so same value key in input will be inserted to left of just inserted key of same value
            counts[keyIndex] -= 1;

            temp[counts[keyIndex]] = arr[i];
        }

        // COPY output back to array
        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }
}
