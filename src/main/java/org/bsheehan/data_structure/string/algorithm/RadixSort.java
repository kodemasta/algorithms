package org.bsheehan.data_structure.string.algorithm;

// for FIXED LENGTH string right to left sortBucket into buckets
// STABLE SORT (must be !)
// RADIX is ASCII 256
// CountingSort from right to left on characters !!
// Time COMPLEXITY - O(n) LINEAR worse case !!!
// Radix uses a bucket or counting sortBucket

// In mathematics, radix refers to the base, the number of unique digits, where decimal is known as base 10.
// The non-comparative alg sorts the list of numbers digit-by-digits from the least significant digit (LSD) to the most significant digit (MSD) or vice versa, this determines the relative order of the elements in the list.
// The runtime for radix sort is O(nk), where n is the length of the array, k is the maximum number of digits.
// Radix sort can use counting sort, insertion sort, bubble sort, or bucket sort as a subroutine to sort individual digits. As a result, may require additional memory space to sort digits, especially when using counting sort.
// "Radix sort only applies to integers, fixed size strings, floating points
// and to "less than", "greater than" or "lexicographic order" comparison predicates,
// whereas comparison sorts accommodates different orders."

import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {

    // radix sort using bucket sort as subroutine, BASE 256 ASCII chars
    public static void sortBucket(String arr[]) {

        Integer maxLength = Integer.MIN_VALUE;
        for (String s: arr)
            maxLength = (maxLength < s.length()) ? s.length():maxLength;

        // LSD for string means getting length and moving back to 0th position
        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from left to right by one
        for (int pos = maxLength-1; pos >= 0; pos--) {
            BucketSort.sortRadix(arr, pos);
        }
    }

    public static void sortCounting(String arr[]) {

        Integer maxLength = Integer.MIN_VALUE;
        for (String s: arr)
            maxLength = (maxLength < s.length()) ? s.length():maxLength;

        // LSD for string means getting length and moving back to 0th position
        // while there is a max element larger positional value, iterate another counting sorting pass
        // moving the position from left to right by one
        for (int pos = maxLength-1; pos >= 0; pos--) {
            CountingSort.sortRadix(arr, pos);
        }
    }

    // 1. bucket the strings by length (maxlen buckets)
    // 2. reset the input array to be sorted by length using the buckets
    // 3. left to right radix sortBucket into 256 (ASCII) alpha buckets.
    // 3a. start on left most position on longest strings,
    // 3b. then as the position is moved to the right include additional bucket of that smaller
    //     length.
    // 3c. Each pass reset input array to new order determined by alpha buckets
    // 3d. By the time you are down the last rightmost char input array will be reset to sorted ordered

    public static void sortBucketMsd(String []words, int maxLen ) {

        java.util.List<java.util.List<String>> lengthBuckets = new ArrayList<List<String>>();

        for (int i = 0; i < words.length; i++)
            lengthBuckets.add(new ArrayList<String>());


        // execute buckets for each length and sortBucket the strings by length into each bucket.
        for (String s : words)
            lengthBuckets.get(s.length()).add(s);

        //FILL LENGTH BUCKETS
        // reinit array so all strings are sorted by length, not alpha yet !
        int idx = 0;
        for (java.util.List<String> lengthBucket : lengthBuckets)
            for (String fixedLengthStr : lengthBucket)
                words[idx++] = fixedLengthStr;

        // now starting with longest strings, go bucket by bucket to shortest strings
        // subsequent passes as we move the position to the right will include the already
        // sorted longer strings
        int startingStrIndex = words.length;

        java.util.List<java.util.List<String>> alphaBuckets = new ArrayList<java.util.List<String>>();
        for (int i = 0; i < 256; i++)
            alphaBuckets.add(new ArrayList<String>());

        for (int charPos = maxLen - 1; charPos >= 0; charPos--) {
            // index into arr for strings of the same length
            startingStrIndex -= lengthBuckets.get(charPos + 1).size();

            // FILL ALPHA BUCKET
            // index into arr for strings of the same length
            // NOW WE ADD TO ALPHA BUCKET based on pos value from arr
            // Do this for each string of this length
            for (int i = startingStrIndex; i < words.length; i++) {
                alphaBuckets.get(words[i].charAt(charPos)).add(words[i]);
            }

            //EMPTY ALPHA BUCKET
            // NOW we iterate over ALPHA buckets one at a time and
            // enqueue in order to arr starting from startingIndex.
            // This sorts all the strings of that length
            idx = startingStrIndex;
            for (java.util.List<String> alphaBucket : alphaBuckets) {
                for (String s : alphaBucket) {
                    words[idx++] = s; // adds in sorted order !
                }

                alphaBucket.clear();
            }
        }
    }
}
