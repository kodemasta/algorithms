package org.bsheehan.algorithms.dp;

public class EggDrop {

    /* Function to get minimum number of trials needed in worst case with n eggs and k floors */
    public static int recursive(int numEggs, int numFloors) {

        return recursive_step(numEggs, numFloors);
    }

    private static int recursive_step(int numEggs, int numFloors) {
        if (numFloors == 0) {
            return 0;
        } else if (numFloors == 1){
            return 1;
        }

        if (numEggs == 1)
            return numFloors;

        int minWorstCase = Integer.MAX_VALUE;

        // Consider all droppings from 1st floor to kth floor and
        // return the minimum of these values plus 1.
        for (int floor = 1; floor <= numFloors; floor++)
        {
            int eggBroken = recursive_step(numEggs-1, floor-1);
            int eggNotBroken = recursive_step(numEggs, numFloors-floor);
            int worstCase = Math.max(eggBroken, eggNotBroken);
            if (worstCase < minWorstCase)
                minWorstCase = worstCase;
        }

        return minWorstCase + 1;
    }

    /* Function to get minimum number of trials needed in worst
        case with n eggs and k floors */
    static int dp(int numEggs, int numFloors)
    {
       /* A 2D table where entry eggFloor[i][j] will represent minimum
       number of trials needed for i eggs and j floors. */
        int soln[][] = new int[numEggs+1][numFloors+1];
        int res;
        int x;

        // We need one trial for one floor and0 trials for 0 floors
        for (int i = 1; i <= numEggs; i++)
        {
            soln[i][1] = 1;
            soln[i][0] = 0;
        }

        // We always need j trials for one egg and j floors.
        for (int j = 1; j <= numFloors; j++)
            soln[1][j] = j;

        // Fill rest of the entries in table using optimal substructure
        // property
        for (int i = 2; i <= numEggs; i++)
        {
            for (int j = 2; j <= numFloors; j++)
            {
                soln[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++)
                {
                    res = 1 + Integer.max(soln[i-1][x-1], soln[i][j-x]);
                    if (res < soln[i][j])
                        soln[i][j] = res;
                }
            }
        }

        // eggFloor[n][k] holds the result
        return soln[numEggs][numFloors];

    }
}
