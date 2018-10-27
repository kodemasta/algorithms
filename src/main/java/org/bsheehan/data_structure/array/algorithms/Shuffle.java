package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.data_structure.array.Array;

import java.util.Random;

public class Shuffle {

    public static void shuffle(int[] arr) {
        // knuth shuffle O(n)
        // invariant: left side of i is shuffled
        Random r = new Random();

        // as we move down array restrict random index to right side past i
        for (int i = 0; i < arr.length; ++i) {
            //select random index (i, length)
            int randomIndexAboveI = r.nextInt(arr.length - i) + i;

            // swap with i
            Array.swap(arr, i, randomIndexAboveI);
        }
    }
}
