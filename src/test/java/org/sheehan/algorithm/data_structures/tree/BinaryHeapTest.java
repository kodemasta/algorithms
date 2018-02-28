package org.sheehan.algorithm.data_structures.tree;

import org.junit.Test;

import java.util.Random;

public class BinaryHeapTest {

    @Test
    public void testMaxHeap() throws Exception {
        BinaryHeap<Integer> tree = new BinaryHeap<>(10, BinaryHeap.HeapType.MAX_HEAP);

        for (int i = 0; i < 6; ++i)
            tree.add(i);

        tree.print(0);

        System.out.println();

        tree.add(50);
        tree.add(-50);


        Integer value;
        while ((value = tree.pop()) != null) {
            System.out.println("top priority: " + value);
        }

    }

    @Test
    public void testMinHeap() throws Exception {
        BinaryHeap<Integer> tree = new BinaryHeap<>(10, BinaryHeap.HeapType.MIN_HEAP);

        for (int i = 0; i < 6; ++i)
            tree.add(i);

        tree.print(0);

        System.out.println();

        tree.add(50);
        tree.add(-50);


        Integer value;
        while ((value = tree.pop()) != null) {
            System.out.println("top priority: " + value);
        }

    }

    @Test
    public void testBuildHeap() {

        BinaryHeap<Integer> tree = new BinaryHeap<>(10, BinaryHeap.HeapType.MIN_HEAP);
        Integer array[] = {4,6,2,8,-5,-6,-66, 55};
        tree.heapify(array);
        Integer value;
        while ((value = tree.pop()) != null) {
            System.out.println("top priority: " + value);
        }

    }

    @Test
    public void testRunningMedian() {
        BinaryHeap<Integer> minHeap = new BinaryHeap<>(100, BinaryHeap.HeapType.MIN_HEAP);
        BinaryHeap<Integer> maxHeap = new BinaryHeap<>(100, BinaryHeap.HeapType.MAX_HEAP);

        Random r = new Random();


        for (int i = 0; i < 20; ++i) {
            int val = r.nextInt(100);
            System.out.println("adding " + val);

            Integer median = maxHeap.peek();
            if (median == null)
                maxHeap.add(val);
            else if (val <= median)
                maxHeap.add(val);
            else {
                minHeap.add(val);
            }

            if (maxHeap.size() > minHeap.size() +1){
                minHeap.add(maxHeap.pop());
            } else if (maxHeap.size() < minHeap.size() ){
                maxHeap.add(minHeap.pop());
            }
            System.out.println("median " + maxHeap.peek());
            System.out.println("median " + maxHeap.peek());

        }

        minHeap.print(0);
        maxHeap.print(0);



    }

    }