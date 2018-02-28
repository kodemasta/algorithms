package org.sheehan.algorithm.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;

        int _arr_size = Integer.parseInt(in.nextLine());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine());
            _arr[_arr_i] = _arr_item;
        }

        res = bitFlip(_arr);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }

    private static int bitFlip(int[] arr) {
        //save a copy of array for reset after bit flip
        final int[] master  = Arrays.copyOf(arr, arr.length);
        int maxOnes = Integer.MIN_VALUE;
        for (int left = 0; left < arr.length; left++) {
            for (int right = left + 1; right < arr.length; right++) {
                flip(arr, left, right);
                int numOnes = count(arr);
                if (numOnes > maxOnes)
                    maxOnes = numOnes;
                //reset
                arr  = Arrays.copyOf(master, master.length);

            }
        }

        return maxOnes;
    }

    /**
     * return array with flipped bits between left and right indices
     * @param arr
     * @param left
     * @param right
     */
    private static void flip(int[] arr, int left, int right) {
         for (int i = left; i <= right; ++i) {
            if (arr[i] == 1)
                arr[i] = 0;
            else if (arr[i] == 0) {
                arr[i] = 1;
            }
        }
    }

    /**
     * count the number of ones in the array
     * @param arr
     * @return
     */
    private static int count(int[] arr) {
        int numOnes = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1)
                numOnes++;
        }
        return numOnes;
    }
}