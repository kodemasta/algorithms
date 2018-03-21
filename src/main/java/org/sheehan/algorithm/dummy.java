package org.sheehan.algorithm;

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main (String[] args) {
        //code
        Scanner sc = new Scanner(System.in);
        int sz = sc.nextInt();
        for(int i = 0; i < sz; i++){
            String num = sc.next();
            Long num1 = Long.parseLong(num);
            System.out.println(num1/2 + 1);

        }

    }
}