package org.sheehan.algorithm;

import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 6/27/14
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class Strings {

    static public String countAndSay(String s){

        char arr[] = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        int i = 0;
        for ( i=0; i < s.length()-1; ++i){
            while(i<s.length()-1 && arr[i]==arr[i+1]){
                cnt++;
                i++;
            }
            sb.append(String.valueOf(cnt));
            sb.append(arr[i]);

            cnt = 1;
        }

        // end case
        if (i == s.length()-1){
            sb.append(String.valueOf(cnt));
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static boolean isStrobogrammatic(String num) {
        char[] str = num.toCharArray();
        for (int i=0; i< str.length; ++i){
            if (!(str[i]=='1' || str[i]=='8' || str[i]=='0' || str[i]=='6' || str[i]=='9'))
                return false;
        }

        for (int i=0; i< str.length; ++i){
            if (str[i]=='6' || str[i]=='9'){
                if (str[i]=='6' && str[str.length-i-1]!='9')
                    return false;
                else if (str[i]=='9' && str[str.length-i-1]!='6')
                    return false;
            }
            else if (str[i] != str[str.length-1-i])
                return false;
        }

        return true;

    }

    //call with 3,3,char[6],0
    static public void generateBalancedParenthesis(int l, int r, char[] str, int count) {
        // if remaining left is negative
        // or if remaining right are less than lefts
        // then invalid
        if (l < 0 || r < l)
            return; //invalid

        if (l==0 && r== 0){
            System.out.println(str); //valid
        }else {
            if (l > 0){
                str[count] = '(';
                generateBalancedParenthesis(l-1, r, str, count+1);
            }
            if (r > l){
                str[count] = ')';
                generateBalancedParenthesis(l, r-1, str, count+1);
            }
        }
    }

    public static boolean validParenthesis(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();

        for (int i=0; i< s.length(); i++){
            if (s.charAt(i) == '(')
                stack.push('(');
            if (s.charAt(i) == '[')
                stack.push('[');
            if (s.charAt(i) == '{')
                stack.push('{');

            if (s.charAt(i) == ')'){
                if (stack.size()!=0 && stack.peek()=='(')
                    stack.pop();
                else
                    return false;
            }
            if (s.charAt(i) == '}'){
                if (stack.size()!=0 && stack.peek()=='{')
                    stack.pop();
                else
                    return false;
            }
            if (s.charAt(i) == ']'){
                if (stack.size()!=0 && stack.peek()=='[')
                    stack.pop();
                else
                    return false;
            }
        }

        if (stack.size()!=0)
            return false;
        return true;

    }

    public static String reverse(String str) {
        char[] buffer = str.toCharArray();
        reverse(buffer, 0, buffer.length - 1);
        return String.valueOf(buffer);
    }

    public static <T> void reverse(char[] buffer, int i, int j) {
        while (j > i){
            char c = buffer[i];
            buffer[i++] = buffer[j];
            buffer[j--] = c;
        }
    }

    static String reverseWords(String str) {
        String reverseStr = reverse(str);

        int start = 0;
        int end = 0;
        char[] buffer = reverseStr.toCharArray();

        int i;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' ) {
                end = i - 1;
                reverse(buffer, start, end);
                start = i + 1;
            }
        }

        //last word ****
        end = i-1;
        reverse(buffer, start, end);

        return new String(buffer);
    }

    public static String reverseRecursively(String str) {

        //base case to handle one char string and empty string
        if (str.length() <= 1) {
            return str;
        }

        return reverseRecursively(str.substring(1)) + str.charAt(0);
    }

//    public static void getPermutations(String prefix, String str, Set<String> cache) {
//        //System.out.println("\tpermutation pre:" + prefix + " str:" + str + " level:" + level);
//        int n = str.length();
//        if (n == 0) {
//            //System.out.println("\tEND permutation pre:" + prefix + " str:" + str + " level:" + level);
//            //System.out.println();
//            //System.out.println(prefix);
//            cache.add(prefix);
//        } else {
//            for (int i = 0; i < n; i++) {
//                //System.out.println("\t\tloop in  i:" + i + " pre:" + prefix + " str:" + str + " level:" + level);
//                //String prefix2 = prefix + str.charAt(i);
//                //String str2 = str.substring(0, i) + str.substring(i + 1, n);
//                //System.out.println("\t\tloop out i:" + i + " pre:" + prefix2 + " str:" + str2 + " level:" + level);
//
//                getPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), cache);
//            }
//        }
//    }
//
//    // start with k = 0
//    //  swap i and k
//    // inorder recursion k+1
//    // swap k with i
//    public static void getPermutations2(java.util.List<Character> arr, int k, Set<String> cache) {
//
//        //loop of recursions !
//        for (int i = k; i < arr.size(); i++) {
//            java.util.Collections.swap(arr, i, k);
//            getPermutations2(arr, k + 1, cache);
//            java.util.Collections.swap(arr, k, i);
//        }
//
//        // when we iterate to the end for a given recursion we have a permutation !
//        if (k == arr.size() - 1) {
//            Character[] cArr = (Character[]) arr.toArray(new Character[0]);
//            char str[] = new char[cArr.length];
//            int i = 0;
//            for (Character c : cArr)
//                str[i++] = c;
//            cache.add(new String(str));
//            //System.out.println(java.util.Arrays.toString(arr.toArray()));
//        }
//    }

    // O(n!) permutations !
    public static Set<String> getPermutationsRecursive(String s){
        if (s == null)
            return null;

        Set<String> perms = new HashSet<String>();

        //base case
        if (s.length() == 0){
            perms.add("");
            return perms;
        }

        //shave off first char then get sub perms on remaining chars.
        //...then insert the first into each position of each sub perm.
        char first = s.charAt(0);
        String remainder = s.substring(1);
        Set<String> subPerms = getPermutationsRecursive(remainder); //RECURSE !!!

        // for each substring perm insert first in each position and add as new perm to set
        for (String subPerm: subPerms){
            for (int i=0; i <= subPerm.length(); ++i){ // '<='   IMPORTANT insert at each position!!!
                // combine at each location in substring
                String start = subPerm.substring(0,i);
                String end = subPerm.substring(i);
                perms.add(start + first + end);
            }
        }

        return perms;
    }

    // number of unique substrings is n(n+1)/2
    public static void getSubstrings(String s, Set<String> cache) {
        for (int i = 0; i < s.length(); ++i)
            for (int j = i; j < s.length(); ++j)
                cache.add(substring(s, i, j));

    }

    private static String substring(String s, int i, int j) {
        StringBuffer buffer = new StringBuffer();

        for (int k = i; k <= j; k++)
            buffer.append(s.charAt(k));

        return buffer.toString();
    }

    public static int findSubstring(String s1, String s2) {
        boolean found = true;
        for (int i = 0; i < s1.length(); ++i) {
            found = true;
            for (int j = 0; j < s2.length(); ++j) {
                if (s1.charAt(i + j) != s2.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found)
                return i;
        }
        return -1;
    }

    public static Character getFirstNonRepeatingChar(String str) {
        char[] chars = str.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.get(c) == null)
                map.put(c, 1);
            else {
                map.replace(c, map.get(c) + 1);
            }
        }

        //now iterate over the string and locate first.
        for (char c : chars) {
            if (map.get(c) == 1)
                return c;
        }

        return null;
    }

    public static Character getFirstRepeatingChar(String str) {
        char[] chars = str.toCharArray();

        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if (set.contains(c))
                return c;
            else {
                set.add(c);
            }
        }

        return null;
    }

    // brute force
    public static Set<Character> findDuplicatesBrute(String str) {

        char[] chars = str.toCharArray();

        Set<Character> duplicates = new HashSet<Character>();

        for (int i = 0; i < str.length(); ++i) {
            for (int j = 0; j < str.length(); ++j) {
                if (i != j && chars[i] == chars[j]) {
                    duplicates.add(chars[i]);
                }
            }
        }

        return duplicates;
    }

    //ASCII
    public static Set<Character> findDuplicatesMask(String str) {

        char[] chars = str.toCharArray();

        int checker = 0; //init or use array of 256 for ASCII

        Set<Character> duplicates = new HashSet<Character>();

        for (char s : chars) {
            int mask = 1 << (s - 'a');
            if ((checker & mask) > 0) {
                duplicates.add(s);
            } else {
                checker |= mask;
            }
        }
        return duplicates;
    }

    //ASCII
    public static Set<Character> findDuplicatesSet(String str) {
        char[] chars = str.toCharArray();

        //List inputList = Arrays.asList(str);
        //Set inputSet = new HashSet(inputList);

        Set<Character> duplicates = new HashSet<Character>();
        Set<Character> foundChars = new HashSet<>();
        for (char c : chars) {
            if (!foundChars.add(c)) //if already in set then is a dupe
                duplicates.add(c);
        }

        return duplicates;
    }

    // need inner loop to check already scanned elements then if not found then
    // add to existing array moving forward.
    public static String removeDuplicatesNoMem(String str) {
        //TODO
        return null;
    }


        // removes duplicates
    // reset array skipping dupes
    public static String removeDuplicatesMask(String str) {
        char[] chars = str.toCharArray();

        int checker = 0; //init for 256 ascii chars.

        int dst = 0;
        for (int i = 0; i < chars.length; ++i) {
            int mask = 1 << (chars[i] - 'a'); //execute mask
            // if NOT a duplicate so dequeue from array and increment
            if ((checker & mask) == 0) { //check mask
                chars[dst++] = chars[i];
            }
            checker |= mask; //set mask
        }
        chars[dst] = 0;

        return new String(chars, 0, dst);
    }

    public static String removeDuplicatesSet(String str) {
        char[] sChars = str.toCharArray();

        //could have used mask or boolean flag array
        // only size of alphabet at most so not a O(n) issue here
        Set<Character> foundSet = new HashSet<>();

        int dst = 0;

        for (int i=0; i<sChars.length;i++){
            if (!foundSet.contains(sChars[i]))
                sChars[dst++]=sChars[i];
            foundSet.add(sChars[i]);
        }

        return new String(sChars, 0,dst);
    }

    public static String removeCharsSet(String str, String remove) {
        char[] sChars = str.toCharArray();
        char[] rChars = remove.toCharArray();

        //could have used mask or boolean flag array
        // only size of alphabet at most so not a O(n) issue here
        Set<Character> removeSet = new HashSet<>();
        for (char c:rChars){
            removeSet.add(c);
        }

        int dst = 0;

        for (int i=0; i<sChars.length;i++){
            if (!removeSet.contains(sChars[i]))
                sChars[dst++]=sChars[i];
        }

        return new String(sChars, 0,dst);
    }

    public static boolean isPalindrome(String str) {
        if (reverse(str).equals(str))
            return true;
        return false;
    }

    public static boolean isPalindrome2(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length/2; ++i){
            if (chars[i] != chars[chars.length-1-i])
                return false;
        }

        return true;
    }

    public static boolean isRotation(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        if ((str1 + str1).contains(str2))
            return true;

        return false;
    }

    public static boolean isAnagram(String str1, String str2){
        //TODO - could just sortBucket and compare !
        // remove all white spaces
        //check length (odd or even)
        // build map of char 1 letter count
        // scan str2 and decrement
        // for each map key check is 0.. if not then false

        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////
    // RADIX SORT
    // LSD on fixed length lexical keys
    // bucket is stack for each ascii char
    // sortBucket iteratively by single character moving left
    /////////////////////////////////////////////////////////////////////////////////
    public static void radixSortLexicalFixedLsd(String words[]) {
        // 256 ASCII character positions
        final int numBuckets = 256;
        org.sheehan.algorithm.data_structures.List<QueueInterface<String>> buckets = new ListImpl<QueueInterface<String>>();
        for (int i = 0; i < numBuckets; i++){
            buckets.appendBack(new QueueArrayImpl<String>(words.length));
        }

        Integer max = Integer.MIN_VALUE;
        for (String value: words)
            max = (max < value.length()) ? value.length():max;

        // while there is a max element larger positional value, iterate another bucket sorting pass
        // moving the position from left to right by one
        for (int position=max-1; position>=0; position--) {
            //FILL BUCKETS
            // each pass checks a rt to left position and buckets based on that digit
            for (String value : words){
                char c = value.charAt(position);
                buckets.get(Character.getNumericValue(c)).enqueue(value);
            }

            //EMPTY BUCKETS into orignal array !
            // reset array to new order after sorting this pass
            // the new order is obtained by removing elements from the bucket queues in FIFO order
            // starting from least valued bucket
            int i = 0;
            for (int bucketIndex = 0; bucketIndex < numBuckets; ++bucketIndex){
                QueueInterface<String> bucket = buckets.get(bucketIndex);
                String value;
                while ((value = bucket.dequeue()) != null){
                    words[i++] = value;
                }
            }
        }
    }

    // 1. bucket the strings by length (maxlen buckets)
    // 2. reset the input array to be sorted by length using the buckets
    // 3. left to right radix sortBucket into 256 (ASCII) alpha buckets.
    // 3a. start on left most position on longest strings,
    // 3b. then as the position is moved to the right include additional bucket of that smaller
    //     length.
    // 3c. Each pass reset input array to new order determined by alpha buckets
    // 3d. By the time you are down the last rightmost char input array will be reset to sorted ordered

    public static void radixSortVarLengthMsd( String []words, int maxLen ) {

        java.util.List<java.util.List<String>> lengthBuckets = new ArrayList<java.util.List<String>>();

        for (int i = 0; i < words.length; i++)
            lengthBuckets.add(new ArrayList<String>());


        // execute buckets for each length and sortBucket the strings by length into each bucket.
        for (String s : words)
            lengthBuckets.get(s.length()).add(s);

        //FILL LENGTH BUCKETS
        // reinit array so all strings are sorted by length, not alpha yet !
        int idx = 0;
        for (java.util.List<String> lengthBucket : lengthBuckets)
            for (String fixedLengthStr : lengthBucket)
                words[idx++] = fixedLengthStr;

        // now starting with longest strings, go bucket by bucket to shortest strings
        // subsequent passes as we move the position to the right will include the already
        // sorted longer strings
        int startingStrIndex = words.length;

        java.util.List<java.util.List<String>> alphaBuckets = new ArrayList<java.util.List<String>>();
        for (int i = 0; i < 256; i++)
            alphaBuckets.add(new ArrayList<String>());

        for (int charPos = maxLen - 1; charPos >= 0; charPos--) {
            // index into arr for strings of the same length
            startingStrIndex -= lengthBuckets.get(charPos + 1).size();

            // FILL ALPHA BUCKET
            // index into arr for strings of the same length
            // NOW WE ADD TO ALPHA BUCKET based on pos value from arr
            // Do this for each string of this length
            for (int i = startingStrIndex; i < words.length; i++) {
                alphaBuckets.get(words[i].charAt(charPos)).add(words[i]);
            }

            //EMPTY ALPHA BUCKET
            // NOW we iterate over ALPHA buckets one at a time and
            // enqueue in order to arr starting from startingIndex.
            // This sorts all the strings of that length
            idx = startingStrIndex;
            for (java.util.List<String> alphaBucket : alphaBuckets) {
                for (String s : alphaBucket) {
                    words[idx++] = s; // adds in sorted order !
                }

                alphaBucket.clear();
            }
        }
    }
}
