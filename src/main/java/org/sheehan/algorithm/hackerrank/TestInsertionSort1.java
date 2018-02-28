package org.sheehan.algorithm.hackerrank;

import java.util.Scanner;

public class TestInsertionSort1 {



    public static void insertIntoSorted(int[] array) {

        int curr = array[array.length-1];
        array[array.length-1]= Integer.MAX_VALUE;

        int i=array.length-1;
        for (i=array.length-1; i > 0; --i){
            if (curr < array[i-1]) {
                array[i] = array[i - 1];
                printArray(array);
            } else {
                array[i] = curr;
                printArray(array);
                break;
            }
        }

        if (i == 0){
            array[i] = curr;
            printArray(array);
        }
    }


    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        insertIntoSorted(ar);
    }


    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

}