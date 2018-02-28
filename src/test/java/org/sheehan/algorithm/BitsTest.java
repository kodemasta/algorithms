package org.sheehan.algorithm;

import org.junit.Test;

public class BitsTest {

    @Test
    public void testReplaceStr() throws Exception {
        int num1 = ~0;
        for (int i=0; i<32;i+=2){
            int mask = 1 << i;
            num1 ^= mask;
        }
        //int num1 = 0x222222;
        int num2 = ~0;

        Bits.replaceSubstr(num2, num1, 2, 8 );
    }

    @Test
    public void testLongestRun() throws Exception {
        int num = Bits.str2Int("1111");
        System.out.println(Bits.longsetRun(num));
        num = Bits.str2Int("1101110111111000011");
        System.out.println(Bits.longsetRun(num));
    }

    @Test
    public void testBinStrToInt() throws Exception {
        System.out.println(Bits.str2Int("1111"));
        System.out.println(Bits.str2Int("0101"));

    }

    @Test
    public void tesetReverse() throws Exception {
        Bits.print(Bits.str2Int("110111110000"));
        //int reverse = Bits.reverse2(Bits.str2Int("110111110000"));
        //Bits.print(reverse);
        int reverse = Bits.reverse(Bits.str2Int("110111110000"));
        Bits.print(reverse);

    }

    @Test
    public void testPrint() throws Exception {
        Bits.print(0);
        Bits.print(1);
        Bits.print(2);
        Bits.print(256);
        Bits.print(Integer.MAX_VALUE);
        Bits.print(Integer.MIN_VALUE);
    }

    @Test
    public void testCountOnses() throws Exception {
        Bits.print(Bits.flipBits(0));
        Bits.print(Bits.flipBits(1));
        Bits.print(Bits.flipBits(2));
        Bits.print(Bits.flipBits(256));
        Bits.print(Bits.flipBits(Integer.MAX_VALUE));
        Bits.print(Bits.flipBits(Integer.MIN_VALUE));
    }

    @Test
    public void testFlipBits() throws Exception {
        System.out.println(Bits.countOnes(0));
        //System.out.println(Bits.countOnes2(0));
        System.out.println(Bits.countOnes(1));
       /// System.out.println(Bits.countOnes2(1));
        System.out.println(Bits.countOnes(2));
       // System.out.println(Bits.countOnes2(2));
        System.out.println(Bits.countOnes(256));
        //System.out.println(Bits.countOnes2(256));
        System.out.println(Bits.countOnes(Integer.MAX_VALUE));
        //System.out.println(Bits.countOnes2(Integer.MAX_VALUE));
        System.out.println(Bits.countOnes(Integer.MIN_VALUE));
        //System.out.println(Bits.countOnes2(Integer.MIN_VALUE));
    }
}