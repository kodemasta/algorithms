package org.bsheehan.data_structure.array;

import org.bsheehan.BaseTest;
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
        int arr[] = Array.create(Array.ArrayType.RANDOM_UNSORTED_UNIQUE, 10,10);
        Array.print(arr);
        System.out.println("pivot:" + arr[5]);
        Array.partition(arr, 5, 0, arr.length-1);
        Array.print(arr);
    }
}