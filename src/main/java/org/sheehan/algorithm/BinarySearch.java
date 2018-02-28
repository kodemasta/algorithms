package org.sheehan.algorithm;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 5/19/14
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearch {

    //iterative
    static int binarySearch(Integer array[], int key) {

        int start = 0;
        int end = array.length-1;

        while (start <= end) {
            int middle = start + (end-start)/2;
            if (array[middle] > key) {
                end = middle-1;
            }  else if (array[middle] < key) {
                start = middle+1;
            }  else
                return middle; //IMPORTANT
        }
        return -1;
    }

    //recursive
    static int binarySearch(Integer array[], int key, int start, int end) {
        // IMPORTANT
        if (start > end)
            return -1; // not found

        int middle = start+(end-start)/2;

        // IMPORTANT
        if (array[middle] == key)
            return middle;
        else if (key < array[middle])
            return binarySearch(array, key, start, middle-1);
        else
            return binarySearch(array, key, middle+1, end);

    }

    static int rotatedBinarySearch(Integer array[], int key, int start, int end)
    {
        if (start > end)
            return -1;

        int middle = start+(end-start)/2;

        // ****IMPORTANT
        if (array[middle] == key)
            return middle;

        // lower half is sorted
        if (array[start] < array[middle]){ // ****IMPORTANT
            if (key >= array[start] && key < array[middle]) {
                //key in lower sorted half - use normal BS
                return binarySearch(array, key, start, middle - 1);
            }else {
                //System.out.println("key: "+key +" start: " + start + " middle: " + middle + " end: " + end+ " start: " +array[start] + " middle: " + array[middle] + " end: " + array[end]);
                //key in unsorted upper half
                return rotatedBinarySearch(array, key, middle + 1, end);
            }
        }
        // upper is sorted
        else {
            if (key > array[middle] && key <= array[end]) {
                //key in upper sorted half - use normal BS
                return binarySearch(array, key, middle + 1, end);
            }else {
                //System.out.println("key: "+key +" start: " + start + " middle: " + middle + " end: " + end+ " start: " +array[start] + " middle: " + array[middle] + " end: " + array[end]);
                //key in unsorted lower half
                return rotatedBinarySearch(array, key, start, middle - 1);
            }
        }

    }
}