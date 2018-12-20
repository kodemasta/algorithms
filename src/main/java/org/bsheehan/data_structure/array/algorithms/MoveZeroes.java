package org.bsheehan.data_structure.array.algorithms;

public class MoveZeroes {
    public static void moveZeroes(int arr[]) {

        // shift non-zero to left
        int nonZeroCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroCnt++] = arr[i];
            } else
                System.out.println("skipped a zero");
        }

        //  fill in left over
        for (int i = nonZeroCnt; i < arr.length; i++) {
            arr[i] = 0;
        }
    }
}
