package org.sheehan.algorithm.backtracking;

public class NQueensProblem {
    private int n;
    private int board[][];

    public static void main(String args[]){
        for (int numQueens = 0; numQueens < 20; ++numQueens) {
            NQueensProblem problem = new NQueensProblem(numQueens);
            problem.solve();
        }
    }

    public NQueensProblem(int n){
        this.n = n;
        this.board = new int[n][n];
    }

    public void solve(){
        if (placeQueen(0)) {
            System.out.println("Success!");
            print();
        } else {
            System.out.println("Failed!");
        }
    }

    private boolean placeQueen(int col){
        if (col == this.n)
            return true;

        for (int row = 0; row < this.n; ++row){
            if (isValidPlacement(row, col)){
                this.board[row][col]=1; //optimistic
                if (placeQueen(col+1))
                    return true;
                //backtracking here
                this.board[row][col]=0; //nope so backtrack, continue and increment queen to next row
            }
        }
        return false; //backtrack.. no rows worked, go back level in DFS and try new parent row position
    }

    private boolean isValidPlacement(int row, int col) {

        // any queens on same row
        for (int colIndex = 0; colIndex < this.n; ++colIndex) {
            if (this.board[row][colIndex] == 1)
                return false;
        }

        //diagonal to upper left
        for (int colIndex = col, rowIndex = row; colIndex >= 0 && rowIndex>=0; --colIndex, --rowIndex){
            if (this.board[rowIndex][colIndex] == 1)
                return false;
        }

        //diagonal to lower left
        for (int colIndex = col, rowIndex = row; colIndex >= 0 && rowIndex<this.n; --colIndex, ++rowIndex){
            if (this.board[rowIndex][colIndex] == 1)
                return false;
        }
        return true;
    }

    public void print(){
        for (int i = 0; i < this.n; ++i){
            for (int j = 0; j < this.n; ++j) {
                if (this.board[i][j] == 1)
                    System.out.print(" * ");
                else
                    System.out.print(" _ ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
