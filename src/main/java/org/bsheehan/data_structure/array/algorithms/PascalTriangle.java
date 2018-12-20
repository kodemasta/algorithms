package org.bsheehan.data_structure.array.algorithms;

// alternate between two arrays (rows)
// visualize left justified triangle !!!
// b[i] = a[i] + a[i-1] for non edge
public class PascalTriangle {

    // use swap or two rows
    public static void print(int maxRow) {

        int a[] = new int[maxRow+1];
        a[0] = 1;

        int b[] = new int[maxRow+1];
        for (int row = 0; row <= maxRow; ++row){

            for (int col=0; col<=row; ++col){
                b[col] = a[col]; // assign direct above
                if (col-1 >= 0)
                    b[col] += a[col-1]; // add above left
            }
            // swap b to a for next iter
            for (int k=0; k<=row;++k) {
                a[k] = b[k];
                System.out.print(b[k] + " ");
            }
            System.out.println();
        }
    }
}
