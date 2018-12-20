package org.bsheehan.algorithms.dp;

public class LongestIncreasingSubsequence {


    // O(n2)
    static int dp(int arr[])
    {
        int n = arr.length;

        int solns[] = new int[n];
        int lisItems[] = new int[n];
        int max = 0;

        /* Initialize LIS values for all indexes */
        for ( int i = 0; i < n; i++ )
            solns[i] = 1;

        for ( int i = 0; i < n; i++ )
            lisItems[i] = -1;

        /* Compute optimized LIS values in left to right manner */
        // For each position i, scan from 1 to i-1 and cnt smaller items
        // For each item found smaller, update lis as lis[j]+1

        for ( int i = 1; i < n; i++ ) {
            for (int j = 0; j < i; j++) {

                // if increasing AND DP check soln is bigger than last soln
                if (arr[i] > arr[j] && solns[i] < solns[j] + 1) {
                    solns[i] = solns[j] + 1;
                    System.out.println(arr[i] + " > " + arr[j] + " length:" +  solns[i]);
                }
            }
        }

        /* Pick maximum of all LIS values */
        for ( int i = 0; i < n; i++ ) {
            if (max < solns[i])
                max = solns[i];
        }

        return max;
    }
}
