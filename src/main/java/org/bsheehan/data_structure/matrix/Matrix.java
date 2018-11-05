package org.bsheehan.data_structure.matrix;

import org.sheehan.algorithm.data_structures.array.Array;

public class Matrix {

    // Trick here is think of top row up to one before the end.
    // The right column starts with the end of the top row
    // i fixes the ring offset, j is the iterator for swapping
    public static void rotateCW90(Integer[][] a){

        // i is an annular inset from all sides
        for (int inset = 0; inset < a.length/2; ++inset) {
            // iterate CW around annular region
            for (int j = inset; j < a.length-inset-1; ++j) { // not all the way to end, which is start of next col or row!!!

                // iterate CW around annular region
                int topRow = a[inset][j]; // iterate across with j
                int rightCol = a[j][a.length-1-inset]; //iterate down on j
                int bottomRow = a[a.length-1-inset][a.length-1-j]; //iterate back across on j
                int leftCol = a[a.length-1-j][inset]; //iterate up on j

                //shift each 90 degrees
                a[j][a.length-1-inset] = topRow;
                a[a.length-1-inset][a.length-1-j] = rightCol;
                a[a.length-1-j][inset] = bottomRow;
                a[inset][j] = leftCol;
            }
        }
        System.out.println();
    }


    public static void print(Integer array[][]){
        for (int i = 0; i<array.length; ++i){
            for (int j = 0; j<array[i].length; ++j){
                System.out.print(String.format("%2d ", array[i][j]));
            }
            System.out.println();
        }

    }

}
