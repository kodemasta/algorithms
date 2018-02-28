package org.sheehan.algorithm.hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestQuicksort2 {

    static int partition(int[] ar, int left, int right) {
        List<Integer> list1= new LinkedList<Integer>();
        List<Integer> list2= new LinkedList<Integer>();

        int pivotIndex = right;
        int pivot = ar[pivotIndex];

        swap(ar, pivotIndex, right); // Move pivot off to rightmost place for safe keeping

        int storeIndex = left;
        for( int i = left; i < right; i++){
            if (ar[i] < pivot) {
                swap(ar, i, storeIndex);
                storeIndex = storeIndex + 1;
            }
        }
        swap(ar, storeIndex, right);// Move pivot to its final place

        printArray(ar, 0, ar.length-1);
        return storeIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
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
        quickSort(ar, 0, ar.length-1);
    }
}