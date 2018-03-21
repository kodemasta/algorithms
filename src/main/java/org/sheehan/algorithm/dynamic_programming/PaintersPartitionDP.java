package org.sheehan.algorithm.dynamic_programming;

// exponential (not using DP)
public class PaintersPartitionDP {

    static int M[][];

    public static void main(String args[]){

        int numPainters = 2;
        int numBoards = 4;
        int boards[] = {10, 20, 30, 90};
        M = new int[numBoards+1][numPainters+1];
        for (int i=0; i <= numBoards; ++i){
            for (int j=0; j <= numPainters; ++j){
                M[i][j]= 0;
            }
        }
        int cum[] = new int[numBoards+1];
        for (int j=0; j < numBoards+1; ++j){
            cum[j]= 0;
        }

        //DP  accumulate so we know total sum at each index
        for (int i = 1; i <= numBoards; i++)
            cum[i] = cum[i-1] + boards[i-1];
        for (int i = 1; i <= numBoards; i++)
            M[i][1] = cum[i];
        //DP  for one board init
        for (int i = 1; i <= numPainters; i++)
            M[1][i] = boards[0];
        System.out.println("Minimal time by " + numPainters + " painters  to paint " + numBoards + " boards is " + solve(boards, numBoards, numPainters));

    }

    private static int solve(int[] boards, int numBoards, int numPainters) {


        //base cases
        if (numBoards == 1)
            return boards[0];
        else if (numPainters == 1)
            return sum(boards, 0, numBoards);

        //boards are partitioned by painters. so every partition takes one painter
        int minPaintingTime = Integer.MAX_VALUE;

        // move the last partition for right from left to right
        for (int numBoardsLeft = 1; numBoardsLeft <= numBoards; numBoardsLeft++){

            // recurse on all partitions minus one needed for right sum
            // each iteration of loop moves this last partition to right
            // Best partition to left
            int minTimeLeft = M[numBoardsLeft][numPainters - 1];

            // last partition
            int timeRight = sum(boards, numBoardsLeft, numBoards);
            // which of the (previous partition and new last partition value are biggest
            int maxCurrent = Math.max(minTimeLeft, timeRight);

            // of all partitions processed we update to select the minimum time found
            minPaintingTime = Math.min(minPaintingTime,maxCurrent);

        }
        M[numBoards][numPainters]=minPaintingTime;
        return minPaintingTime;
    }

    private static int sum(int[] boards, int from, int to) {
        int total = 0;
        for (int i = from; i < to; ++i){
            total += boards[i];
        }
        return total;
    }
}
