package org.bsheehan.data_structure.array;

import org.bsheehan.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapTest  extends BaseTest {

    @Test
    public void testMaxHeap() {
        super.test();
        int a[] = Array.create(Array.ArrayType.LINEAR_UNSORTED, 10, 10);
        Array.print(a);
        Heap<Integer> heap = Heap.createHeap(Arrays.stream(a).boxed().toArray(Integer[]::new), Heap.HeapType.MAX_HEAP);

        heap.print();

        Assert.assertTrue("Is Heap", heap.isHeap());
    }

    @Test
    public void testMinHeap() {
        super.test();
        int a[] = Array.create(Array.ArrayType.LINEAR_UNSORTED, 10, 10);
        Array.print(a);
        Heap<Integer> heap = Heap.createHeap(Arrays.stream(a).boxed().toArray(Integer[]::new), Heap.HeapType.MIN_HEAP);

        heap.print();

        Assert.assertTrue("Is Heap", heap.isHeap());
    }
    @Test
    public void testRemove() {
        super.test();
        int a[] = Array.create(Array.ArrayType.LINEAR_UNSORTED, 10, 10);
        Array.print(a);
        Heap<Integer> heap = Heap.createHeap(Arrays.stream(a).boxed().toArray(Integer[]::new), Heap.HeapType.MAX_HEAP);
        Assert.assertTrue("Is Heap", heap.isHeap());
        heap.print();

        for (int i =0; i < 10; ++i) {

            System.out.println("removed:" + heap.remove());
            heap.print();
            Assert.assertTrue("Is Heap", heap.isHeap());
        }
    }
}