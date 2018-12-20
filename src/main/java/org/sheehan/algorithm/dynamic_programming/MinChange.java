package org.sheehan.algorithm.dynamic_programming;

/**
 * Created by bob on 3/3/16.
 *
 * Find mun number of coins to get target amount  from infinite supply of denominations
 *
 */
public class MinChange {

    // solution matrix is (# of coin types, number of cents) e.g. 4 X 100 for 4 US coins and 100 cents
    static public int minChange(int[] denominations, int targetAmount) {

        int numDenoms = denominations.length + 1;
        int numTarget = targetAmount + 1;
        int inf = Integer.MAX_VALUE - 1;

        int[][] soln = new int[numDenoms][numTarget];
        for(int i = 0; i < numDenoms; ++i) {
            for (int j = 1; j < numTarget; j++) {
                soln[i][j] = inf;
            }
        }

        // build up solutions
        int candidateNumberOfDenoms;
        for (int currentDenom = 1; currentDenom < numDenoms; currentDenom++) {
            for (int currentTarget = 1; currentTarget < numTarget; currentTarget++) {
                // if this coin is smaller than current amount then we can use
                if (denominations[currentDenom-1] <= currentTarget) {
                    // valid candidate number of coins is previous soln with room to spare for new coint + 1
                    candidateNumberOfDenoms = soln[currentDenom][currentTarget - denominations[currentDenom-1]] + 1;
                }
                else {
                    candidateNumberOfDenoms = inf;
                }                                              // do not take
                soln[currentDenom][currentTarget] = Math.min(soln[currentDenom-1][currentTarget],  candidateNumberOfDenoms);
            }
        }

        // take the final computed solution = minimum number of coins required
        return soln[numDenoms-1][numTarget-1];
    }

    static public void main(String args[]) {
        int denom[] = {1,5,10,25};
        System.out.println(minChange(denom,99));
    }
}


