package org.bsheehan.data_structure.string.algorithm;

import org.bsheehan.data_structure.array.algorithms.sort.InsertionSort;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Bucket is mainly useful when input is uniformly distributed over a range R.
public class BucketSort {


    public static void sortRadix(String arr[], int pos) {

        final int NUM_BUCKETS = 256; //ascii;


        List<List<String>> buckets = new ArrayList<List<String>>(NUM_BUCKETS);
        for (int i = 0; i < NUM_BUCKETS; ++i)
            buckets.add(new LinkedList<>());

        // FILL empty BUCKETS
        // each pass checks a rt to left position and buckets based on that digit
        for (String value : arr){
            char c = value.charAt(pos);
            buckets.get(Character.getNumericValue(c)).add(value);
        }

        // EMPTY full BUCKETS into original array !
        // reset array to new order after sorting this pass
        // the new order is obtained by removing elements from the bucket queues in FIFO order
        // starting from least valued bucket
        for (int bucketIndex = 0, i = 0; bucketIndex < NUM_BUCKETS; ++bucketIndex){
            List<String>   bucket = buckets.get(bucketIndex);
            String value;
            Iterator<String> iterator = bucket.iterator();
            while (iterator.hasNext()){
                arr[i++] = iterator.next();
            }
        }
    }
}
