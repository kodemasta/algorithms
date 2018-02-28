package org.sheehan.algorithm.hackerrank;


import java.util.Arrays;
import java.util.Scanner;

public class TestPriyankaAndToys {


    public static void main(String[] args) {

        Scanner stdin=new Scanner(System.in);
        int n=stdin.nextInt();
        int weights[]=new int[n];
        for(int i=0;i<n;i++)
            weights[i]=stdin.nextInt();

        int answer = 0;

        Arrays.sort(weights);

        int totalCnt = 0;
        for (int i=0; i < n; ++i){
            int cnt = 0;
            int weight = weights[i];
            for (int j=i+1; j < n; ++j) {
                if(weights[j]>=weight && weights[j]<=weight+4) {
                    i = j-1;
                    cnt++;
                }else {
                    i = j-1;
                    break;
                }
            }
            totalCnt += cnt;
         }

        // Compute the final answer from n,k,prices
        System.out.println(n-totalCnt);
    }
}