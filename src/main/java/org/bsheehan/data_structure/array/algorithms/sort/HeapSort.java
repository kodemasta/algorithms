package org.bsheehan.data_structure.array.algorithms.sort;

import org.bsheehan.data_structure.array.Heap;

import java.util.Arrays;

// worst 0(nlogn)
// avg O(nlogn)
// best O(nlogn)

public class HeapSort {
    public static void sort(int arr[]) {
        Integer[] arr2 = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
        Heap<Integer> heap = Heap.createHeap(arr2, Heap.HeapType.MAX_HEAP);

        for (int i = 0; i < arr.length; i++) {
            Integer remove = heap.remove();
            arr[arr.length-1-i]= remove;
        }
        arr = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();
    }
}
