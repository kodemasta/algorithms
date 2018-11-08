package org.bsheehan.data_structure.array.algorithms;


public class BinarySearch {

    //iterative
    static int search(int arr[], int key) {

        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            int middle = start + (end-start)/2;
            if (arr[middle] > key) {
                end = middle-1;
            }  else if (arr[middle] < key) {
                start = middle+1;
            }  else
                return middle; //IMPORTANT
        }
        return -1;
    }

    //recursive
    static int search(int arr[], int key, int left, int right) {
        // IMPORTANT
        if (left > right)
            return -1; // not found

        int middle = left+(right-left)/2;

        // IMPORTANT
        if (arr[middle] == key)
            return middle;
        else if (key < arr[middle])
            return search(arr, key, left, middle-1);
        else
            return search(arr, key, middle+1, right);

    }

    static int rotatedSearch(int arr[], int key, int left, int right)
    {
        if (left > right)
            return -1;

        int middle = left+(right-left)/2;

        // ****IMPORTANT
        if (arr[middle] == key)
            return middle;

        // lower half is sorted
        if (arr[left] < arr[middle]){ // ****IMPORTANT
            if (key >= arr[left] && key < arr[middle]) {
                //key in lower sorted half - use normal BS
                return search(arr, key, left, middle - 1);
            }else {
                //System.out.println("key: "+key +" start: " + start + " middle: " + middle + " end: " + end+ " start: " +array[start] + " middle: " + array[middle] + " end: " + array[end]);
                //key in unsorted upper half
                return rotatedSearch(arr, key, middle + 1, right);
            }
        }
        // upper is sorted
        else {
            if (key > arr[middle] && key <= arr[right]) {
                //key in upper sorted half - use normal BS
                return search(arr, key, middle + 1, right);
            }else {
                //System.out.println("key: "+key +" start: " + start + " middle: " + middle + " end: " + end+ " start: " +array[start] + " middle: " + array[middle] + " end: " + array[end]);
                //key in unsorted lower half
                return rotatedSearch(arr, key, left, middle - 1);
            }
        }

    }
}