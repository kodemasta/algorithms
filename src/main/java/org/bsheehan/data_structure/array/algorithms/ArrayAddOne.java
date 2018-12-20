package org.bsheehan.data_structure.array.algorithms;

import java.util.Arrays;

public class ArrayAddOne {
    public static int[] addOne(int[] arr){
        int carry = 0;
        arr[arr.length-1] += 1; // add one to lsd
        for (int i = 0; i < arr.length; i++) {
            int pos = arr.length-1-i;
            int term = arr[pos]+carry;
            if (term == 10) {
                term = 0;
                carry = 1;
            } else {
                carry = 0;
            }
            arr[pos] = term;
        }

        // if we get to end and have carry !!!
        if (carry == 1) {
            arr = new int[arr.length + 1];
            Arrays.fill(arr, 0);
            arr[0] = 1;
        }

        return arr;
    }
}
