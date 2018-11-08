package org.bsheehan.algorithms.backtracking;

public class EightQueens {
    // each row has a single queen at which column
    int queenColumns[] = new int[8];

    public void printBoard(){
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (j == queenColumns[i])
                    System.out.print("Q ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkRow(int candidateRow){
        for (int row = 0; row < candidateRow; ++row)  {
            int diff = (int)Math.abs(queenColumns[row]- queenColumns[candidateRow]);
            if (diff == 0)
                return false; //cannot have queen on same column as a previous row
            if (diff == candidateRow-row)
                return false; // queen is on diagonal with a previous queen

        }
        return true;
    }

    public boolean checkBoard(){
        for (int row = 0; row < 8; ++row)  {
           if (!checkRow(row))
               return false;
        }
        return true;
    }

    // recursive place queens starting with row 0
    // loop over cols for each row... backtrack and continue if invalid placement
    public void placeQueen(final int row){
        System.out.println("ATTEMPT:"+row);
        // end condition. may not be ok as other cols will be iterated over even if
        if (row == 8){
            if (checkBoard()) {
                //printBoard();
                System.out.println("\t\t\t\t================>>> FOUND SOLUTION");
            }
            return;
        }

        // check each column in current row.. recurses on every permutation !
        for (int col = 0; col < 8; ++col){
            this.queenColumns[row] = col; //candidate.. there may be several that succeed here !
            if (checkRow(row)) {
                System.out.println("GOOD:"+row + "-" + col);
                placeQueen(row + 1); //next row recurse
            } else{
                for (int j = 0; j < col; ++j)
                    System.out.print("\t");
                System.out.println("BACKTRACK:"+row + "-" + col);
            }

            // break out so we don't overwrite for bad col == 7 after
            if (checkBoard())
                break;
        }
    }
}
