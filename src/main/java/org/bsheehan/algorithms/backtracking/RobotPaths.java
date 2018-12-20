package org.bsheehan.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

// PASCAL TRIANGLE !
public class RobotPaths {

    public static List<String> findPathsRecurse(int n){
        List<String> pathList = new ArrayList<String>();
        findPathsRecurse(n, n, 1,1, "", pathList);
        return pathList;
    }

    public static void findPathsRecurse(int dstX, int dstY, int srcX, int srcY, String path, List<String> pathList){
        path += String.format(" (%d,%d)", srcX , srcY);
        if( srcX == dstX && srcY == dstY){ //reach the (n,n) point
            pathList.add(path);
        } else if( srcX > dstX || srcY > dstY){//wrong way
            return; //backtrack
        } else {
            findPathsRecurse(dstX, dstY, srcX+1, srcY, path, pathList);
            findPathsRecurse(dstX, dstY, srcX, srcY+1, path, pathList);
        }
    }

    public static int findPathNumberDp(int n, int m){
        int soln[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            soln[0][i] = 1;
            soln[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                soln[i][j] = soln[i][j-1] + soln[i-1][j];
            }
        }

        return soln[n-1][m-1];
    }
}
