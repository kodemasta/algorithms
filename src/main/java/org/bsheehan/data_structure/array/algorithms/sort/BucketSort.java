package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Bucket is mainly useful when input is uniformly distributed over a range R.
public class BucketSort {

    public static void sort(int arr[], final int range) {

        final int NUM_BUCKETS = range;

        // create 10 buckets
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(NUM_BUCKETS);
        for (int i = 0; i < NUM_BUCKETS; ++i)
            buckets.add(new LinkedList<>());

        // hash into buckets based on known range
        for (int i= 0;i < arr.length; ++i){
            buckets.get(arr[i]/NUM_BUCKETS).add(arr[i]);
        }

        // sort each bucket, assign back to source array
        int k = 0;
        for (int i = 0; i < NUM_BUCKETS; ++i){
            int[] bucket = buckets.get(i).stream().mapToInt(j->j).toArray();

            // select a stable sort for subroutine on each bucket
            InsertionSort.sort(bucket);
            for (int j = 0; j < bucket.length; j++)
                arr[k++] = bucket[j];
        }
    }

    public static void sortRadix(int arr[], final int exp) {

        final int NUM_BUCKETS = 10;

        // create 10 buckets
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(NUM_BUCKETS);
        for (int i = 0; i < NUM_BUCKETS; ++i)
            buckets.add(new LinkedList<>());

        // hash into buckets based on known range
        for (int i= 0;i < arr.length; ++i){
            buckets.get((arr[i]/exp)%10).add(arr[i]);
        }

        // sort each bucket, assign back to source array
        int k = 0;
        for (int i = 0; i < NUM_BUCKETS; ++i){
            int[] bucket = buckets.get(i).stream().mapToInt(j->j).toArray();

            // select a stable sort for subroutine on each bucket
            InsertionSort.sort(bucket);
            for (int j = 0; j < bucket.length; j++)
                arr[k++] = bucket[j];
        }
    }
}
