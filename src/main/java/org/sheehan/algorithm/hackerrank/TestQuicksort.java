package org.sheehan.algorithm.hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestQuicksort {

    static int partition(int[] ar, int left, int right) {
        List<Integer> list1= new LinkedList<Integer>();
        List<Integer> list2= new LinkedList<Integer>();

        int pivot = ar[left];

        for(int i=left; i<=right; i++)
        {
            if (ar[i]<pivot) {
                list1.add(ar[i]);
            } else if (ar[i]>pivot) {
                list2.add(ar[i]);
            }
        }
        list1.add(pivot);
         List<Integer> list3 = new LinkedList<Integer>();
        list3.addAll(list1);
        list3.addAll(list2);
        for(int i=left; i<=right; i++)
            ar[i] = list3.get(i-left);

        return left + list1.size() -1;
    }

    static void quickSort(int[] ar, int left, int right) {
        if (right <= left)
                return;
        int index = partition(ar, left, right);
        quickSort(ar, left, index-1);
        quickSort(ar, index+1, right) ;

        printArray(ar, left, right);
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