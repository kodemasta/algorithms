package org.sheehan.algorithm.hackerrank;


import java.util.Arrays;
import java.util.Scanner;

public class TestMarkAndToys {


    public static void main(String[] args) {

        Scanner stdin=new Scanner(System.in);
        int n=stdin.nextInt(),k=stdin.nextInt();
        int prices[]=new int[n];
        for(int i=0;i<n;i++)
            prices[i]=stdin.nextInt();

        int answer = 0;

        Arrays.sort(prices);

        for (int i =0; i < n; ++i){
            if (k-prices[i]>=0){
                answer++;
                k-=prices[i];
            }
        }

        // Compute the final answer from n,k,prices
        System.out.println(answer);
    }
}