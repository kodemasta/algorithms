package org.sheehan.algorithm.dynamic_programming;

/**
 * Created by bob on 3/3/16.
 */
public class MinChange {

    // solution matrix is (# of coin types, number of cents) e.g. 4 X 100 for 4 US coins and 100cents
    static public int minChange(int[] denom, int targetAmount) {
        int actualAmount;
        int m = denom.length + 1;
        int n = targetAmount + 1;
        int inf = Integer.MAX_VALUE - 1;

        int[][] table = new int[m][n];
        for(int i = 0; i< m; ++i) {
            for (int j = 1; j < n; j++) {
                table[i][j] = inf;
            }
        }

        // build up solutions
        for (int denomPosition = 1; denomPosition < m; denomPosition++) {
            for (int currentAmount = 1; currentAmount < n; currentAmount++) {
                if (denom[denomPosition-1] <= currentAmount) {
                    // take
                    actualAmount = table[denomPosition][currentAmount - denom[denomPosition-1]];
                }
                else {
                    actualAmount = inf;
                }                                              // do not take
                table[denomPosition][currentAmount] = Math.min(table[denomPosition-1][currentAmount], 1 + actualAmount);
            }
        }

        // take the final computed solution = minimum number of coins required
        return table[m-1][n-1];
    }

    static public void main(String args[]) {
        int denom[] = {1,5,10,25};
        System.out.println(minChange(denom,99));
    }
}


