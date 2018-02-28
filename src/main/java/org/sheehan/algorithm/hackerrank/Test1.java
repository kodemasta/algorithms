package org.sheehan.algorithm.hackerrank;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;
        int _M;
        _M = Integer.parseInt(in.nextLine());

        int _N;
        _N = Integer.parseInt(in.nextLine());

        res = cubeNumbers(_M, _N);
        System.out.println(res);

    }

    // implement mathematical equation
    private static int cubeNumbers(int m, int n) {
        int cnt = 0;
        for (int a = 1; a <= m; a++){
            for (int b = 1; b <= n; b++){
                double value = Math.pow(Math.cbrt(a) + Math.cbrt(b), 3);
                if (value == (int)value){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}