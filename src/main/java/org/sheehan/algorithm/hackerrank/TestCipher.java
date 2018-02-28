package org.sheehan.algorithm.hackerrank;

import java.util.Scanner;

public class TestCipher {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String encoded = in.next();
        int length = encoded.length();
        char[] encodedChars = encoded.toCharArray();
        int[] encodedBits = new int[length];

        int decoded[] = new int[n];
        for (int i = 0; i < n; i++)
            decoded[i] = -1;
        for (int i = 0; i < n; i++) {
            if (encodedChars[i] == '1')
                encodedBits[i] = 1;
            else
                encodedBits[i] = 0;
        }
        for (int i = 0; i < n; i++){
            int result = 0;
            int start = i-k+1;
            if (start < 0)
                start = 0;
            int end = start+k;

            for (int j = start; j < end; j++) {
                if (j < decoded.length && decoded[j] != -1)
                    result ^= decoded[j];
            }

            if (i == 0)
                decoded[i] = encodedBits[i];
            else
                decoded[i] = result^encodedBits[i];
        }


        for (int i : decoded)
            System.out.print(i);

        System.out.println();
    }
}