package org.sheehan.algorithm;

/**
 * Created by bob on 8/30/14.
 */
public class Bits {

    static int MASK_32_BIT = 0x80000000;

    public int setBit(int n, int i, boolean on){
        if (on) {
            n |= (1<<i);
        }
        else
            n &= ~(1<<i);

        return n;
    }

    static int flipBits(int n){

        // xor each bit
        for (int i = 0; i < 32; i++){
            n ^= 1<<i;
        }

        return n;
    }

    public String addBinaryStrings(String a, String b) {
        char aChars[]=a.toCharArray();
        char bChars[]=b.toCharArray();
        StringBuilder sb = new StringBuilder();

        int i=aChars.length-1;
        int j=bChars.length-1;
        int carry = 0;

        while(i>=0 || j>=0 || carry==1){

            // get char and decrement
            int aVal=0;
            if (i>=0)
                aVal=(int)(aChars[i--]-'0');
            int bVal=0;
            if (j>=0)
                bVal=(int)(bChars[j--]-'0');

            // sum and handle cases
            int sum = aVal+bVal+carry;
            if (sum==0){
                sb.insert(0,'0');
            }
            else if (sum==1){
                sb.insert(0,'1');
                carry=0;
            }
            else if (sum==2){
                sb.insert(0,'0');
                carry=1;
            }
            else if (sum==3){
                sb.insert(0,'1');
                carry=1;
            }
        }

        return sb.toString();
    }

    public static int longsetRun(int n){
        int cnt = 0;
        int max = 0;

        for (int i = 0; i < 32; i++){

            // if we hit a zero reset else increment
            if (((n >> i) & 1) == 0){
                max = max>cnt?max:cnt;
                cnt=0;
            } else
                cnt++;

        }
        return max;
    }

//    public static int reverse(int n) {
//        int reverse = 0;
//        for (int i = 0; i < 32; i++) {
//            int digit = (n >> i) & 1;
//            reverse |= digit;
//            if (i!=31)
//                reverse <<= 1;
//        }
//        return reverse;
//    }

    public static int reverse(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    public static int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            //print((1 << i) | (1 << j));
            //System.out.println();
            return n ^= (1 << i) | (1 << j);
        }

        return n;
    }


    public static int str2Int(String s) {
        s = s.trim();

        int value = 0;
        for (int i=0; i < s.length(); ++i){

            //convert to int (0 or 1)
            int bit = (int)( s.charAt(s.length()-i-1)-'0');

            // multiply by power of 2
            value += bit * (1 << i);
        }

        return value;
    }

    //shift the number right
    static void print(int n){

         for (int i = 0; i < 32; i++){
            if ( (n >> (31-i) & 1) == 0 )
                System.out.print("0");
            else
                System.out.print("1");
        }

        System.out.println();
    }

    //shift the number right and mask with 1
    static int countOnes(int n){

        int cnt = 0;
        for (int i = 0; i < 32; i++){
            if ( (n >> (31-i) & 1) == 1 )
               cnt++;
        }

        return cnt;
    }

    public static void replaceSubstr(int num, int replaceNum, int i, int j){

        // execute a custom mask for i to j and 0 out that range in num1
        int mask = ~0; // all 1's
        for (int index = i; index <=j; ++index){ // 0's in range
            mask ^= (1<<index);
        }

        // blank out the target number in range to 0's
        num &= mask;

        // shift the substr to correct position
        replaceNum <<=i;

        // simply or them together now !
        print(num|replaceNum);

    }
}
