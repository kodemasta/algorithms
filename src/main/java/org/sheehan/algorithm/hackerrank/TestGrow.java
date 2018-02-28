package org.sheehan.algorithm.hackerrank;

import java.util.Scanner;

public class TestGrow {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        if (t < 1 || t > 10) {
            System.err.println("t out of range");
            System.exit(1);
        }
        int n;
        for (int i = 0; i < t; i++){
            n = in.nextInt();
            if (n < 0 || n > 60){
                System.err.println("n out of range");
            }
            System.out.println(calculateHeight(1, 0, n));
        }
    }

    public static int calculateHeight(int height, int current, int max){

        if (current == 0)
            ;
        else if (current%2 == 1)
            height *= 2;
        else
            height += 1;

        if (current == max)
            return height;
        return calculateHeight(height, current+1, max);
    }
}