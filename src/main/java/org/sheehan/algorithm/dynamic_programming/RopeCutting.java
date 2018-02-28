package org.sheehan.algorithm.dynamic_programming;

/**
 * Created by bob on 9/10/14.
 *
 * Get biggest product for M cuts
 */
public class RopeCutting {

    public static int maxProductRecursive(int length){

        if (length == 0)
            return 0;

        int maxVal = 0;

        for (int i = 1; i < length; ++i){
            int value = Math.max(i*(length-i), i*maxProductRecursive(length-i));
            maxVal = Math.max(maxVal, value);
        }

        return maxVal;
    }

    // O(n^2)
    public static int maxProductDynamic(int length) {
        int solutions[] = new int[length+1];
        solutions[0] = 0;
        solutions[1] = 0;

        // build up sub problems starting with length 1 and incrementing
        for (int i = 1; i <=length; ++i){
            int maxVal = 0;
            // cut the remainder incrementally by 1 up to length of subproblem (i)
            // AND use previous shorter subproblems  to check against
            // j < i but j< i/2 optimization
            // persist best solution as optimal for i
            for (int j = 1; j <= i/2; ++j) {
               int value = Math.max(j * (i-j), j*solutions[i-j]);
               maxVal = Math.max(maxVal, value);
            }
            solutions[i] = maxVal;
        }

       // for (int i = 1; i < length; ++i)
        //    System.out.println(solutions[i]);

        return solutions[length];
    }

}
