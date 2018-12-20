package org.bsheehan.algorithms.dp;

public class SubsetSum {

    public static int recursive(int arr[], int total) {
        return recursive_step(arr, total, arr.length-1);
    }

    // return number of subset that add up to target from 0 to ith index
    public static int recursive_step(int arr[], int total, int i) {
        if (total == 0)
            return 1; //empty set - indicates that sum set does equal total recursion including i below
        else if (total < 0)
            return 0;
        if (i < 0)
            return 0;

        if (total < arr[i]) // cannot use rightmost ith element, go left my son (do not include i)
            return recursive_step(arr, total, i-1);
        else
            // exclude ith, and also add and look for remainder
            return recursive_step(arr, total, i-1) + recursive_step(arr, total-arr[i], i-1);
    }

    //  TODO recurive_dp https://www.youtube.com/watch?v=nqlNzOcnCfs

    // Returns true if there is a subset of set[] with sun equal to given sum
    public static boolean dp(int arr[], int targetSum) {
        int n = arr.length;

        // The value of subset[i][j] will be true if there is a
        // subset of set[0..j-1] with sum equal to i
        boolean soln[][] = new boolean[n + 1][targetSum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
            soln[i][0] = true;

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= targetSum; i++)
            soln[0][i] = false;

        // Fill the subset table in left to right (bottom up) manner
        for (int i = 1; i <= n; i++) {
            for (int sum = 1; sum <= targetSum; sum++) {
                // if the candidate element is larger than current sum use previous soln
                if (sum < arr[i - 1])
                    soln[i][sum] = soln[i - 1][sum];
                    // if the candidate element is <= than current sum
                    // 1. if previous value solved sum then new candidate is not necessary or,
                    // 2. or use current element and make sure difference from sum exists in previous soln
                else if (sum >= arr[i - 1])
                    soln[i][sum] = soln[i - 1][sum] ||
                            soln[i - 1][sum - arr[i - 1]];
            }
        }

        // uncomment this code to print table
         for (int i = 0; i <= n; i++)
         {
            for (int j = 0; j <= targetSum; j++)
                System.out.print(soln[i][j] + " ");
            System.out.println();
         }

        return soln[n][targetSum];
    }
}
