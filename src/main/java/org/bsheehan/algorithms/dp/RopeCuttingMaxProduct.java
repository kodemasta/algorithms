package org.bsheehan.algorithms.dp;

/**
 * Created by bob on 9/10/14.
 *
 * Get biggest product for M cuts
 */
public class RopeCuttingMaxProduct {

    public static int maxProductRecursive(int totalLength){

        if (totalLength == 0)
            return 0;

        int maxPoduct = 0;

        for (int currLength = 1; currLength < totalLength; ++currLength){
            int remainingLength = totalLength-currLength;
            int currProduct = currLength*(totalLength-currLength);
            int currMaxProduct = Math.max(currProduct, currLength * maxProductRecursive(remainingLength));
            maxPoduct = Math.max(maxPoduct, currMaxProduct);
        }

        return maxPoduct;
    }

    // O(n^2)
    public static int maxProductDynamic(int totalLength) {
        int solns[] = new int[totalLength+1];
        solns[0] = 0;
        solns[1] = 0;

        // build up sub problems starting with length 1 and incrementing
        for (int currLength = 1; currLength <= totalLength; ++currLength){
            int maxVal = 0;

            // find best val for currLength
            // 1. new cut product
            // 2. soln from previous cut * new cut
            for (int j = 1; j <= currLength/2; ++j) {
               int value = Math.max(j * (currLength-j), j*solns[currLength-j]);
               maxVal = Math.max(maxVal, value);
            }
            solns[currLength] = maxVal;
        }

        return solns[totalLength];
    }

}
