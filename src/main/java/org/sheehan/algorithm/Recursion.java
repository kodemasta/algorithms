package org.sheehan.algorithm;

import org.sheehan.algorithm.data_structures.stack.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 5/20/14
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recursion {

    static int factorial (int val){
        if (val == 0)
            return 1;
        return val*factorial(val-1);
    }

    static int fibonocci(int index) {
        if (index < 0)
            throw new RuntimeException("no negative indexes allowed");
        if (index <=1)
            return index;

        return fibonocci(index-1) + fibonocci(index - 2);
    }

    static int fibonocciIter(int index) {
        if (index < 0)
            throw new RuntimeException("no negative indexes allowed");

        int prev = 0;
        int curr = 1;
        if (index <=1)
            return index;

        for (int i = 2; i <= index; ++i) {
            int lastCurr = curr;
            curr = prev + curr;
            prev = lastCurr;
        }

        return curr;
    }

    // basic
    static void hanoi(int n, char src, char dst, char tmp)
    {
        if (n == 0)
            return;
        hanoi(n-1, src, tmp, dst);
        System.out.println("Move " + n + " from " + src + " to " + dst) ;
        hanoi(n-1, tmp, dst, src);
    }

    // using stacks for towers
    static <T extends Comparable<T>> void hanoiStack(int n, Stack<T> src, Stack<T> dst, Stack<T> tmp)
    {
        if (n == 0)
            return;
        //recursive call 1
        hanoiStack(n-1, src, tmp, dst);

        // move remaining source to dest
        dst.push(src.pop());

        src.print();
        tmp.print();
        dst.print();
        System.out.println();

        //recursive call 2
        hanoiStack(n-1, tmp, dst, src);
    }




    public class EightQueens {
        // each row has a single queen at which column
        int queenColForRow[] = new int[8];

        public void printBoard(){
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    if (j == queenColForRow[i])
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
                int diff = (int)Math.abs(queenColForRow[row]-queenColForRow[candidateRow]);
                if (diff == 0)
                    return false; //cannot have queen on same column as a previous row
                if (diff == candidateRow-row)
                    return false; // queen is on diagonal with a previous queen

            }
            return true;
        }

        //recursive start with row 0
        public void placeQueen(final int row){
            if (row == 8){
                printBoard();
                return;
            }

            // check each column in current row.. recurses on every permutation !
            for (int col = 0; col < 8; ++col){
                queenColForRow[row] = col; //candidate.. there may be several that succeed here !
                if (checkRow(row))
                    placeQueen(row+1); //next row recurse
                else{
                    // BACKTRACK - bad row placement
                }
            }
        }
    }

    static public void main(String[]args){
       // Recursion.EightQueens queens = new Recursion().new EightQueens();
       // queens.placeQueen(0);


    }
}
