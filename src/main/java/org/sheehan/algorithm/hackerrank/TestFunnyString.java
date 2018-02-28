package org.sheehan.algorithm.hackerrank;

import org.sheehan.algorithm.Strings;

import java.util.Scanner;

public class TestFunnyString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int i = 0; i < t; i++){
            String s = in.next();
            String r = Strings.reverse(s);
            boolean funny = true;
            for (int j =0; j<s.length()-1;++j){
                int val1 = Math.abs((int)s.charAt(j) - (int)s.charAt(j+1));
                int val2 =  Math.abs((int)r.charAt(j) - (int)r.charAt(j+1));
                if (val1 != val2) {
                    funny = false;
                    break;
                }

            }
            if (!funny)
                System.out.println("Not Funny");
            else
                System.out.println("Funny");
        }
    }
}