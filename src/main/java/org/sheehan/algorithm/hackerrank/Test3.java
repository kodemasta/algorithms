package org.sheehan.algorithm.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test3 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        String _str;
        try {
            _str = in.nextLine();
        } catch (Exception e) {
            _str = null;
        }

        res = palindrome(_str);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }

    /** Cycle through all substrings and check if palindrome
     *  Returns number of palindromes for all substrings of input string
     *  Contraint on length of string to 5000

     * @param str
     * @return
     */
    private static int palindrome(String str) {
        if (str.length() > 5000)
            throw new RuntimeException("string too long folks !");
        Set<String> pals = new HashSet<String>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String subStr = str.substring(i, j);
                if (isPalindrome(subStr)) {
                    pals.add(subStr); // no duplicates
                }
            }
        }
        return pals.size();
    }

    /**
     * This will verify if a specific string is a palindrome.
     * Returns True of input string is a palindrome.
     *
     * Basically check against its reverse
     *
     * @param str
     * @return
     */
    private static boolean isPalindrome(String str)
    {
        if(str.length() == 0 || str.length() == 1)
            return true;

        return str.equals(reverse(str));
    }

    // in place reversal
    static String reverse(String str) {
        char[] buffer = str.toCharArray();

        final int length = str.length();
        final int pivot = length/2;

        for (int i = 0, cnt = 0; i < pivot; ++i, ++cnt){
            char c = buffer[i];
            buffer[i] = buffer[length - 1 - cnt];
            buffer[length - 1 - cnt] = c;
        }
        return new String(buffer);
    }
}