package org.bsheehan.data_structure.array.algorithms.partition;

import org.bsheehan.data_structure.array.Array;

public class PositiveNegative {

    public static void partition(int arr[]){
        int left = 0;
        int right = arr.length-1;

        Array.partition(arr, -1, left, right);




//        // group < 0 to left
//        for (int i = 0; i < arr.length; ++i){
//            if ( left < right) {
//                while (arr[left] < 0) {
//                    ++left; // pass by all neg
//                }
//                while (arr[right] > 0) {
//                    --right; // pass by all pos
//                }
//
//                if (left < right) {
//                    Array.swap(arr, left, right);
//                }
//            }
//        }

        // interleave
//        for (int i = 0; i < arr.length; ++i){
//            if (i%2 == 1) {
//                Array.swap(arr, i,  ++left);
//            }
//        }
    }
}
