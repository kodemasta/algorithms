package org.bsheehan.algorithms.dp;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 *
 * 2^n possible subsequences (each char is either in or out (2 states), n elements
 *
 *
 *
 *
 * LOOK AT LAST RIGHTMOST ELEMENTS FOR OPTIMAL SUB STRUCTURE
 CASE 1: if last characters of both sequences match (or X[m-1] == Y[n-1]) then
    L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])

    (i.e 1 + LIS(of shorter strings)

 CASE 2: else if last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
    L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2]) )

    (i.e. max of LIS(one string shorter OR LIST (other string shorter) ) try to get CASE 1

 */
public class LongestCommonSubsequence {

    public static int dp(int arr1[], int arr2[]){

        // array of LIS for subsequence of lengths nxm
        int soln[][] = new int[arr1.length + 1][arr2.length + 1];


        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (i==0 )
                if (arr1[i] == arr2[j])
                    soln[i][j] = 1 + soln[i-1][j-1];
                else
                    soln[i][j] = Math.max(soln[i][j-1], soln[i-1][j]);
            }
        }

    }
}
