//package org.sheehan.algorithm;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//
//public class TestSkyscraper {
//
//    public static void main(StringAlgs[] args) throws IOException {
//        //INPUT
//        Scanner in = new Scanner(System.in);
//        int num = in.nextInt();
//
//        final int[] heights = new int[num];
//
//        int min = IntegerAlgs.MAX_VALUE;
//        int max = IntegerAlgs.MIN_VALUE;
//        for(int i = 0; i < num; ++i){
//            heights[i] = in.nextInt();
//            if (heights[i] > max)
//                max = heights[i];
//            if (heights[i] < min)
//                min = heights[i];
//        }
//
//        if (min == max)
//            return
//
//        int count = 0;
//        for(int i = 0; i < num; ++i){
//            int max2 = heights[i];
//            for(int j = i+1; j < num; ++j) {
//                if (heights[j] > max2) {
//                    break;
//                } else if (heights[j] == max2)
//                    count++;
//            }
//        }
//
//        //OUTPUT
//        System.out.print(count*2);
//    }
//}