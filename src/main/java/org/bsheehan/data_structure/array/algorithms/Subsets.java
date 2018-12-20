package org.bsheehan.data_structure.array.algorithms;

public class Subsets {
    static void find(int []set)
    {
        /* set_size of power set of a set with set_size n is (2**n -1) */
        long pow_set_size = (long)Math.pow(2, set.length);
        int counter, j;

        /* Run from counter 000..0 to 111..1 */
        for(counter = 0; counter < pow_set_size; counter++)
        {
            // one subset arrangement
            for(j = 0; j < set.length; j++)
            {
                /* Check if jth bit in the counter is set If set then print jth element from set */
                if((counter & (1 << j)) > 0)
                    System.out.print(set[j] + " ");
            }

            System.out.println();
        }
    }
}
