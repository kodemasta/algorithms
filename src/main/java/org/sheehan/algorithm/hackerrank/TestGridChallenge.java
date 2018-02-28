package org.sheehan.algorithm.hackerrank;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestGridChallenge {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < n; ++j) {
                list.add(in.next());
            }
            if (verify(list,n)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean verify(List<String> list, int n) {
        List<String> sortedList = new ArrayList<String>();
        for (String s: list){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder build = new StringBuilder();
            StringBuilder sorted = build.append(chars);
            sortedList.add(sorted.toString());
        }
        for (int i = 0; i < n-1; i++) {
            String s1 = sortedList.get(i);
            String s2 = sortedList.get(i+1);
            for (int j= 0; j < n; j++) {
                if (s1.charAt(j) > s2.charAt(j))
                    return false;
            }
        }
        return true;

    }


}