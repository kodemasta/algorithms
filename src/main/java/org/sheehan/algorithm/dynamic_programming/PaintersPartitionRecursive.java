package org.sheehan.algorithm.dynamic_programming;

// exponential (not using DP)
public class PaintersPartitionRecursive {

    public static void main(String args[]){

        int numPainters = 2;
        int numBoards = 4;
        int boards[] = {10, 20, 30, 90};

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
            int minTimeLeft = solve(boards, numBoardsLeft, numPainters - 1);

            // last partition
            int timeRight = sum(boards, numBoardsLeft, numBoards);
            // which of the (previous partition and new last partition value are biggest
            int maxCurrent = Math.max(minTimeLeft, timeRight);

            // of all partitions processed we update to select the minimum time found
            minPaintingTime = Math.min(minPaintingTime,maxCurrent);

        }

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
