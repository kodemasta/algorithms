package org.bsheehan.algorithms.math;

// convert a string of chars to count repeated sequences of chars and vreate encoded string with counts and char.
// example 0122333444 --> 1011223334
public class CountAndSay {

    static public String countAndSay(String s){

        char arr[] = s.toCharArray();

        StringBuilder encodedStr = new StringBuilder();
        int cnt = 1;
        int i = 0;

        // for each char.. iterate across repetitions
        for ( i = 0; i < s.length()-1; ++i){
            while(i < s.length()-1 && arr[i]==arr[i+1]){
                cnt++;
                i++;
            }
            encodedStr.append(String.valueOf(cnt));
            encodedStr.append(arr[i]);

            cnt = 1;
        }

        // end case if last char was not repeated
        if (i == s.length()-1){
            encodedStr.append(String.valueOf(cnt));
            encodedStr.append(arr[i]);
        }
        return encodedStr.toString();
    }
}
