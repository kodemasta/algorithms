package org.sheehan.algorithm;

/**
 * Created by bob on 8/30/14.
 */
public class Bits {

    static int MASK_32_BIT = 0x80000000;

    public int setBit(int n, int i, boolean on) {
        if (on) {
            n |= (1 << i);
        } else
            n &= ~(1 << i);

        return n;
    }

    // xor each bit
    static int flipBits(int n) {
        for (int i = 0; i < 32; i++)
            n ^= 1 << i;
        return n;
    }

    // just xor against -1 integer (all 1s)
    static int flipBits2(int n) {
        int mask = -1;
        // or mask = ~0;
        return n ^ mask;
    }

    // just use ~ (inverse operator)
    static int flipBits3(int n) {
        return ~n;
    }

    public static int bitsToConvert(int a, int b){
        int xor = a^b;
        return getWeight(xor);
    }

    // grade school shift and add method
    public static int multiply(int a, int b) {

        int product = 0;

        while(a!= 0){
            if ((a & 1) != 0 ){
                product = add(product, b);
            }
            a >>= 1;
            b <<= 1;
        }

        return product;
    }
    public static int add(int a, int b) {
        int sum = 0;
        int carry = 0;

        for (int i = 0; i < 32; ++i) {
            int aBit = (a >> i) & 1;
            int bBit = (b >> i) & 1;
            int sumBit = aBit + bBit + carry;
            if (sumBit == 1) {
                sum += Math.pow(2, i);
                carry = 0;
            } else if (sumBit == 2) {
                carry = 1;
            } else if (sumBit == 3) {
                sum += Math.pow(2, i);
                carry = 1;
            }
        }
        return sum;
    }

    public static String add(String a, String b) {
        return String.valueOf(add(str2Int(a), str2Int(b)));
    }

    public static int swapEvenOdd(int n){
        int evenMask = 0xAAAAAAAA;
        int oddMask = 0x55555555;

        int even = n & evenMask;
        int odd = n & oddMask;

        return (odd << 1) | (even >> 1);
    }

    public static int longsetRun(int n) {
        int cnt = 0;
        int max = 0;

        for (int i = 0; i < 32; i++) {

            // if we hit a zero reset else increment
            if (((n >> i) & 1) == 0) {
                max = max > cnt ? max : cnt;
                cnt = 0;
            } else
                cnt++;

        }
        return max;
    }

    public static int reverse(int n) {
        for (int i = 0; i < 16; i++) { // just to half way or else you unswap the swap !
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    public static int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1; // masking with 1 gives either 1 or 0
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) { // if they are different
            //print((1 << i) | (1 << j));
            //System.out.println();
            return n ^= (1 << i) | (1 << j); //xor does the swap
        }

        return n;
    }

    public static int swapNibbles(int n) {

        return ((n & 0xF0) >> 4) | ((n & 0x0F) << 4);
    }


    public static int str2Int(String s) {
        s = s.trim();

        int value = 0;
        for (int i = 0; i < s.length(); ++i) {

            //convert to int (0 or 1)
            int bit = (int) (s.charAt(s.length() - i - 1) - '0');

            // multiply by power of 2
            value += bit * (1 << i);
        }

        return value;
    }

    //shift the number right
    static void print(int n) {

        for (int i = 0; i < 32; i++) {
            if (((n >> (31 - i) & 1) == 0)) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }
        }

        System.out.println();
    }

    // left to right LSB swap
    // what is nearset number with same weight ?
    static int getSameWeightNearestInt(int n) {
        for (int i = 0; i < 31; i++) {
            int leftBit = ((n >> i) & 1);
            int nextBit = ((n >> i + 1) & 1);
            if (leftBit != nextBit) {
                return (swapBits(n, i, i + 1));
            }
        }

        return n; //not found
    }

    //get number of ones
    static int getWeight(int n) {

        int weight = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) //shift and mask with 1 to get 0 or 1, compare
                weight++;
        }

        return weight;
    }

    public static void replaceSubstr(int num, int replaceNum, int i, int j) {

        // execute a custom mask for i to j and 0 out that range in num1
        int mask = ~0; // all 1's
        for (int index = i; index <= j; ++index) { // 0's in range
            mask ^= (1 << index);
        }

        // e.g. 1111111100001111

        // blank out the target number in range to 0's
        num &= mask;

        // shift the substr to correct position
        replaceNum <<= i;

        // simply or them together now !
        print(num | replaceNum);

    }

    public static void rotate(int num, int n, boolean left) {
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            mask |= (1 << i);
        }
        int chopped = 0;
        if (left) {
            chopped = (num >> (32 - n)) & mask;
            num = num << n;
        } else {
            chopped = num & mask;
            num = num >>> n;
        }

        num |= chopped;
        print(num);
    }
}
