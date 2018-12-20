package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.data_structure.array.Array;

import java.util.*;

/**
 * Permutations: How many orderings are there of an array:
 * (n)*(n-1)*...(1) = n!
 *
 * Permutations of groupings of k - how many ways to choose or group k items from n (order maters)
 * n!/(n-k)!
 *
 * Combinations: there are less because order does NOT matter.
 * n!/k!*(n-k)!
 */
public class Permutations {

    public static List<List<Integer>> find(int arr[]){
       List<List<Integer>> permutations = new ArrayList<List<Integer>>();

       getPermutations(arr, permutations, 0);

       return permutations;

    }

    private static void getPermutations(int[] arr, List<List<Integer>> permutations, int k) {

        if (k == arr.length-1) {
            Integer[] perm = Array.toIntegerArray(arr);
            List<Integer> integers = Arrays.asList(perm);
            List<Integer> list = new ArrayList<Integer>();
            list.addAll(integers);
            permutations.add(list);
            return;
        }
        for (int i = k; i < arr.length; i++) {
            Array.swap(arr, k, i);
            getPermutations(arr, permutations, k+1);
            Array.swap(arr, k, i);
        }
    }


}
