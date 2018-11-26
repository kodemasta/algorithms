package org.bsheehan.data_structure.array;

import org.bsheehan.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayTest extends BaseTest {

    @Test
    public void testShift(){
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        arr = Array.addFront(arr,99);
        Array.print(arr);
        arr = Array.removeFront(arr);
        Array.print(arr);
        arr = Array.insertAt(arr, 3, 99);
        Array.print(arr);
        arr = Array.removeAt(arr, 3);
        Array.print(arr);
    }


    @Test
    public void testRotate(){
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        Array.rotateRight(arr,4);
        Array.print(arr);
        Array.rotateLeft(arr,4);
        Array.print(arr);
        Array.rotateRight2(arr,1);
        Array.print(arr);
    }

    @Test
    public void testReverse(){
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        Array.reverse(arr, 0, 3);
        Array.print(arr);
        Array.reverse(arr, 4, arr.length-1);
        Array.print(arr);
    }

    @Test
    public void testPartition(){

        super.test();
        int pivotVal = 5;
        for (int i = 0; i < 100; i++) {

            int[] arr = Array.create(Array.ArrayType.RANDOM_UNSORTED, 11, 9);

            Array.print(arr);
            int partitionIndex = Array.partition(arr, pivotVal, 0, arr.length - 1);
            Array.print(arr);
            System.out.println("partition index:" + partitionIndex);
            System.out.println();
            Assert.assertTrue("partitioned", Array.isParitioned(arr, partitionIndex, pivotVal));
         }
    }

    @Test
    public void testPartition3Way() {
        super.test();
        int pivotVal = 1;
        for (int i = 0; i < 100; i++) {

            int[] arr = Array.create(Array.ArrayType.RANDOM_UNSORTED, 3, 15);

            Array.print(arr);
            int partitionIndex = Array.partition3way(arr, pivotVal, 0, arr.length - 1);
            Array.print(arr);
            Assert.assertTrue("paritioned", Array.isParitioned(arr, partitionIndex, pivotVal));

        }
    }
}