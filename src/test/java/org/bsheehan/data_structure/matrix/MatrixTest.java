package org.bsheehan.data_structure.matrix;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.array.Array;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void rotateCW90() {
        final Integer N = 5;
        Integer[][] arr = new Integer[N][N];

        int k = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                arr[i][j] = k++;
            }
        }

        Matrix.print(arr);
        Matrix.rotateCW90(arr);
        Matrix.print(arr);
    }
}