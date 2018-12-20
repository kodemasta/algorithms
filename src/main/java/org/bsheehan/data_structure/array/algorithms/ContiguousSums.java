package org.bsheehan.data_structure.array.algorithms;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Set;
import java.util.TreeSet;

// See SubsetSum for DP version discontiguous sum of elements
public class ContiguousSums {

    public static void findSums_n3(int arr[]){
        Set<Integer> sums = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                sums.add(sum);
            }
        }
        System.out.println(sums.toString());
    }

    public static void findSums_n2(int arr[]){
        Set<Integer> sums = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                sums.add(sum);
            }
        }
        System.out.println(sums.toString());
    }

    public static void findSums_n2_2(int arr[]){
        Set<Integer> sums = new TreeSet<>();

        // create accumulated sums
        int accumulatedSum[] = new int[arr.length];
        accumulatedSum[0] = arr[0];
        sums.add(accumulatedSum[0]);
        for (int i = 1; i < arr.length; i++) {
            accumulatedSum[i] = accumulatedSum[i-1] + arr[i];
            sums.add(accumulatedSum[i]);
        }

        // just subtract pairs of accumulated sums to get each diff sum
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int sum = accumulatedSum[j]-accumulatedSum[i];
                sums.add(sum);
            }
        }
        System.out.println(sums.toString());
    }

    public static void findMaxSum_dp(int arr[]){
        int max = Integer.MIN_VALUE;
        int soln[] = new int[arr.length];
        soln[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(soln[i-1], soln[i-1] + arr[i]);
            soln[i] = soln[i-1] + arr[i];
        }
        System.out.println("max: " + max);
    }

    public static void findMaxSum_dp2(int arr[])
    {
        int max_so_far = arr[0];
        int curr_max = arr[0];

        for (int i = 1; i < arr.length; i++)
        {
            curr_max = Integer.max(arr[i], curr_max + arr[i]);
            max_so_far = Integer.max(max_so_far, curr_max);
        }
        System.out.println("max: " + max_so_far);
    }
}
