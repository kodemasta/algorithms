package org.sheehan.algorithm.hackerrank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestQuicksort3 {

    static int partition(int[] ar, int left, int right) {
        List<Integer> list1= new LinkedList<Integer>();
        List<Integer> list2= new LinkedList<Integer>();

        int pivotIndex = right;
        int pivot = ar[pivotIndex];

        //swap(ar, pivotIndex, right); // Move pivot off to rightmost place for safe keeping

        int storeIndex = left;
        for( int i = left; i < right; i++){
            if (ar[i] < pivot) {
                swap(ar, i, storeIndex);
                storeIndex = storeIndex + 1;
            }
        }
        swap(ar, storeIndex, right);// Move pivot to its final place

        //printArray(ar, 0, ar.length-1);
        return storeIndex;
    }

    public static void insertionSortPart2(int[] ar)
    {
        // Fill up the code for the required logic here
        // Manipulate the array as required
        // The code for Input/Output is already provided
        for (int i = 1; i < ar.length; ++i)
            insertIntoSorted(ar, i);
    }

    static int shifts = 0;
    static int swaps = 0;
    public static void insertIntoSorted(int[] ar, int k) {

        int v = ar[k];
        ar[k]= Integer.MAX_VALUE;

        int i=k;
        for (i=k; i > 0; --i){
            if (v < ar[i-1]) {
                ar[i] = ar[i - 1];
                shifts++;
            } else {
                ar[i] = v;
                break;
            }
        }

        if (i == 0){
            ar[i] = v;
        }
        //printArray(ar, 0, ar.length-1);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
        swaps++;
    }


    static void quickSort(int[] ar, int left, int right) {
        if (right <= left)
                return;
        int index = partition(ar, left, right);
        quickSort(ar, left, index-1);
        quickSort(ar, index+1, right) ;


    }

    static void printArray(int[] ar, int left, int right) {
        for(int i=left; i<=right; i++)
            System.out.print(ar[i]+" ");

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt();
        }

        int[] ar2 = Arrays.copyOf(ar, ar.length);
        quickSort(ar, 0, ar.length - 1);
        insertionSortPart2(ar2);
        //System.out.println(shifts);
        //System.out.println(swaps);
        System.out.println(shifts-swaps);
    }
}