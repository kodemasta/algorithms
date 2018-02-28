package org.sheehan.algorithm.hackerrank;

import java.util.Scanner;

//    6
//    1
//    1
//    6
//    -1 -2 -3 -4 -5 -6
//    2
//    1 -2
//    3
//    1 2 3
//    1
//    -10
//    6
//    1 -1 -1 -1 -1 5
//


//    1 1
//    -1 -1
//    1 1
//    6 6
//    -10 -10
//    5 6
public class TestMaxSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int n;
        for (int i = 0; i < t; i++){
            n = in.nextInt();
            int array[] = new int[n];
            for (int j = 0; j < n; ++j){
                array[j] = in.nextInt();
            }
            findMaxSubArray(array);

        }
    }

    public static void findMaxSubArray(int[] inputArray) {

        int maxStartIndex = 0;
        int maxEndIndex = 0;
        int maxSum = Integer.MIN_VALUE;

        int cumulativeSum = 0;
        int maxStartIndexUntilNow = 0;

        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
            int eachArrayItem = inputArray[currentIndex];

            cumulativeSum += eachArrayItem;

            if (cumulativeSum > maxSum) {
                maxSum = cumulativeSum;
                maxStartIndex = maxStartIndexUntilNow;
                maxEndIndex = currentIndex;
            } else if (cumulativeSum < 0) {
                maxStartIndexUntilNow = currentIndex + 1;
                cumulativeSum = 0;
            }
        }

        //System.out.println("Max sum         : " + maxSum);
//        System.out.println("Max start index : " + maxStartIndex);
//        System.out.println("Max end index   : " + maxEndIndex);

        int maxSum2 = 0;
        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
            if (inputArray[currentIndex] > 0)
                maxSum2 += inputArray[currentIndex];
        }
        int max = Integer.MIN_VALUE;
        if (maxSum2 == 0) {
            for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
                if (inputArray[currentIndex] > max)
                    max = inputArray[currentIndex];
            }
            maxSum2 = max;
        }


        //System.out.println("Max sum non contiguous   : " + maxSum2);
        System.out.println(maxSum + " " + maxSum2);
    }
}