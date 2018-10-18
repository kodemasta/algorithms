package org.sheehan.algorithm.data_structures.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayTest {

    @Test
    public void testCreate() {
        Integer array[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 10);
        Array.print(array);
        array = Array.create(Array.ArrayType.RANDOM_UNSORTED, 10, 10);
        Array.print(array);
        array = Array.create(Array.ArrayType.LINEAR_SORTED, 10, 10);
        Array.print(array);
        array = Array.create(Array.ArrayType.LINEAR_UNSORTED, 10, 10);
        Array.print(array);
        array = Array.create(Array.ArrayType.RANDOM_SORTED_UNIQUE, 20, 10);
        Array.print(array);
        array = Array.create(Array.ArrayType.RANDOM_UNSORTED_UNIQUE, 20, 10);
        Array.print(array);
    }

    @Test
    public void testPartition() {
        int cnt = 100;
        while(cnt > 0) {
            System.out.println("------------------------------------------");
            Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED_UNIQUE, 20, 10);
            Integer copy[] = new Integer[10];
            System.arraycopy(array, 0, copy, 0, array.length);
            int pivotIndex = new Random().nextInt(array.length);
            int pivot = array[pivotIndex];
            System.out.println("pivot at " + pivotIndex + ": " + pivot);
            System.out.println();
            Array.print(array);
            int partition = Array.partition(array, pivotIndex, 0, array.length - 1);
            System.out.println("partitioned at: " + partition);
            Array.print(array);
            for (int i = 0; i < partition; ++i) {
                assertTrue(array[i] <= pivot);
            }
            for (int i = partition + 1; i < array.length; ++i) {
                assertTrue(array[i] > pivot);
            }
            Integer dest1[] = new Integer[partition + 1];
            System.arraycopy(array, 0, dest1, 0, partition + 1);
            Arrays.sort(dest1);
            Array.print(dest1);


            System.out.println();
            Array.print(copy);
            partition = Array.partition2(copy, pivotIndex, 0, copy.length - 1);
            System.out.println("partitioned at: " + partition);
            Array.print(copy);
            for (int i = 0; i <= partition; ++i) {
                assertTrue(copy[i] <= pivot);
            }
            for (int i = partition + 1; i < copy.length; ++i) {
                assertTrue(copy[i] > pivot);
            }
            Integer dest2[] = new Integer[partition + 1];
            System.arraycopy(copy, 0, dest2, 0, partition + 1);
            Arrays.sort(dest2);
            Array.print(dest2);
            assertEquals(dest1.length, dest2.length);
            cnt--;
        }
    }

    @Test
    public void testOddEvenPartition() {
        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED_UNIQUE, 20, 10);
        Array.print(array);
        int partition = Array.partitionOddEven(array, 0, array.length-1);
        Array.print(array);
        for (int i=0; i<partition; ++i){
            assertTrue("partition:" + partition + " element should be even:" + array[i], array[i]%2==0);
        }
        for (int i=partition+1; i<array.length; ++i){
            assertTrue("partition:" + partition + " element should be odd:" + array[i], array[i]%2==1);
        }
        Integer dest[] = new Integer[partition+1];
        System.arraycopy(array, 0, dest, 0,partition+1);
        Arrays.sort(dest);
        Array.print(dest);
    }

    @Test
    public void testLongestRun() {
        Integer array[] = Array.create(Array.ArrayType.RANDOM_UNSORTED, 3, 20);
        Array.print(array);
        int run[] = Array.longestRun(array);
        System.out.println("longest run is: " + run[0]+":"+run[1]);
    }

    @Test
    public void testMergedArrays() {
        Integer array1[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 20);
        Array.print(array1);
        Integer array2[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 20);
        Array.print(array2);
        Integer merged[] = Array.merge(array1, array2);
        Array.print(merged);

    }
}