package org.bsheehan.data_structure.array.algorithms;

import java.util.Set;
import java.util.TreeSet;

public class MaxSquare {


    // here we are just scanning backward and storing largest rect
    // then take max of those as we move to right
    public static int brute(int arr[]){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, getMaxArea(arr, i));
        }
        return max;

    }

    private static int getMaxArea(int[] arr, int i) {
        int max = Integer.MIN_VALUE;
        // increment or grow offset towards left
        for (int offset = 0; offset <= i; offset++) {
            int leftArea = getMaxLeftArea(arr, i, offset);
            max = Math.max(max, leftArea);
        }

        return max;
    }

    // scan up to a given offset to get largest in that range
    private static int getMaxLeftArea(int[] arr, int i, int offset) {
        int minHeight = arr[i];
        for (int j = 0; j <= offset; j++){
            if (i-j >= 0)
                minHeight = Math.min(minHeight, arr[i-j]);
            else
                break; // too far left <0
        }

        int width = i > offset ? (offset+1):i+1;
        return minHeight * width;

    }

    // TODO use stacks
    public static void efficient(int arr[]){

    }
}
