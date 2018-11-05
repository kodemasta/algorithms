//package org.sheehan.algorithm;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.sheehan.algorithm.data_structures.array.Array;
//import org.sheehan.algorithm.sortBucket.SortArray;
//
//import java.util.Arrays;
//
//public class SortArrayTest {
//
//    @Test
//    public void testBubbleSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("bubble");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.bubbleSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//    }
//
//    @Test
//    public void testBubbleSortPolarity() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        SortArray.bubbleSortPolarity(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//        array = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        SortArray.bubbleSortPolarity(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//    }
//
//    @Test
//    public void testOddEvenSort() throws Exception {
//        Integer array[]={1,2,3,4,5};
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//        SortArray.oddEvenSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//        array = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//        SortArray.oddEvenSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//
//    }
//
//    @Test
//    public void testInsertionSortArray() throws Exception {
//        Integer array[] = {6,5,3,1,8,7};
//
//        System.out.println("insertion");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.insertionSort2(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//
//    }
//
//    @Test
//    public void testHeapSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("heap");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.heapSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//
//    @Test
//    public void testSelectionSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("selection");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.selectionSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//    @Test
//    public void testSelectionSortRecursive() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("selection recurse");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.selectionSortRecursive(array, 1);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//
//    @Test
//    public void testMergeSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        int array2[] = {8,2,3,4,5,6,7,1};
//
//        System.out.println("merge");
//        System.out.println(Arrays.toString(array2));
//
//        //System.out.println("inversions: " + SortArray.mergeSort(array2));
//        SortArray.mergeSort(array2);
//        System.out.println(Arrays.toString(array2));
//        System.out.println();
//    }
//
//    // simple merge of ints
//    @Test
//    public void testMergeSort2() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//        int array2[] = new int[array.length];
//        for (int i = 0; i < array.length; i++)
//            array2[i]=array[i];
//
//        System.out.println("merge");
//        System.out.println(Arrays.toString(array2));
//
//        SortArray.mergeSort2(array2);
//        System.out.println(Arrays.toString(array2));
//        System.out.println();
//    }
//
//    @Test
//    public void testQuickSortSelect() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("quick-select");
//
//        int index = SortArray.quickselectKthStatistic(array, 0, array.length - 1, 4);
//        System.out.println(Arrays.toString(array));
//        System.out.println(array[index]);
//        System.out.println();
//    }
//
//    @Test
//    public void testQuickSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("quick");
//        org.sheehan.algorithm.data_structures.array.Array.print(array);
//
//        SortArray.quicksort(array, 0, array.length - 1);
//        Assert.assertTrue(org.sheehan.algorithm.data_structures.array.Array.isSorted(array));
//        org.sheehan.algorithm.data_structures.array.Array.print(array);
//        System.out.println();
//
//    }
//
//    @Test
//    public void testCountingSort() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("counting");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.countingSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//    @Test
//    public void testCountingSort2() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
//
//        System.out.println("counting");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.countingSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//    @Test
//    public void testRadixSortLsd() throws Exception {
//        Integer array[] =Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 100);
//
//        System.out.println("radix lsd");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.radixSort(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//
//    @Test
//    public void testRadixSortBinaryLsd() throws Exception {
//        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 100);
//
//        System.out.println("radix lsd");
//        System.out.println(Arrays.toString(array));
//
//        SortArray.radixSortBinaryLsd(array);
//        System.out.println(Arrays.toString(array));
//        System.out.println();
//    }
//}