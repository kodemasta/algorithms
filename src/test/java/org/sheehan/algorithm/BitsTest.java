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
    public void testStrToInt() throws Exception {
        System.out.println(Bits.str2Int("1111"));
        System.out.println(Bits.str2Int("0101"));
    }

    @Test
    public void testAdd() throws Exception {
        int a = 0x00000011;
        int b = 0x00000010;
        System.out.print(a + " + " + b +" = " +  (a+b) + " ");
        int sum = Bits.add(a, b);
        System.out.print(sum + " ");
        Bits.print(sum);

        System.out.println(Bits.add(String.valueOf(17), String.valueOf(16)));
    }

    @Test
    public void testSwapEvenOdd() throws Exception {
        int num = 0xAAAAAAAA;
        System.out.println(num);
        Bits.print(num);
        int prod = Bits.swapEvenOdd(num);
        System.out.println(prod);
        Bits.print(prod);
    }

    @Test
    public void testMultiply() throws Exception {
        int a = 0x00000011;
        int b = 0x00000010;
        System.out.print(a + " * " + b +" = " +  (a*b) + " ");
        int prod = Bits.multiply(a, b);
        System.out.print(prod + " ");
        Bits.print(prod);
    }

    @Test
    public void testGetSameWeightNearestInt() {
        int inputVal = 10;
        System.out.print(inputVal + " ");
        Bits.print(inputVal);
        int val = Bits.getSameWeightNearestInt(inputVal);
        System.out.print(val + " ");
        Bits.print(val);

        inputVal = 11;
        System.out.print(inputVal + " ");
        Bits.print(inputVal);
        val = Bits.getSameWeightNearestInt(inputVal);
        System.out.print(val + " ");
        Bits.print(val);

        inputVal = 100;
        System.out.print(inputVal + " ");
        Bits.print(inputVal);
        val = Bits.getSameWeightNearestInt(inputVal);
        System.out.print(val + " ");
        Bits.print(val);
    }

    @Test
    public void testReverse() throws Exception {
        Bits.print(Bits.str2Int("110111110000"));
        //int reverse = Bits.reverse2(Bits.str2Int("110111110000"));
        //Bits.print(reverse);
        int reverse = Bits.reverse(Bits.str2Int("110111110000"));
        Bits.print(reverse);

        Bits.print(100);
        Bits.print(Bits.swapNibbles(100));
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
    public void testFlipBits() throws Exception {
        Bits.print(0);
        Bits.print(Bits.flipBits(0));
        Bits.print(Bits.flipBits2(0));
        Bits.print(Bits.flipBits2(0));
        System.out.println();
        Bits.print(1);
        Bits.print(Bits.flipBits(1));
        Bits.print(Bits.flipBits2(1));
        Bits.print(Bits.flipBits2(1));
        System.out.println();
        Bits.print(256);
        Bits.print(Bits.flipBits(256));
        Bits.print(Bits.flipBits2(256));
        Bits.print(Bits.flipBits2(256));
        System.out.println();
        Bits.print(Integer.MIN_VALUE);
        Bits.print(Bits.flipBits(Integer.MIN_VALUE));
        Bits.print(Bits.flipBits2(Integer.MIN_VALUE));
        Bits.print(Bits.flipBits2(Integer.MIN_VALUE));
        System.out.println();
        Bits.print(Integer.MAX_VALUE);
        Bits.print(Bits.flipBits(Integer.MAX_VALUE));
        Bits.print(Bits.flipBits2(Integer.MAX_VALUE));
        Bits.print(Bits.flipBits2(Integer.MAX_VALUE));
        System.out.println();
    }

    @Test
    public void testWeight() throws Exception {
        Bits.print(0);
        System.out.println(Bits.getWeight(0));
        Bits.print(1);
        System.out.println(Bits.getWeight(1));
        Bits.print(2);
        System.out.println(Bits.getWeight(2));
        Bits.print(256);
        System.out.println(Bits.getWeight(256));
        Bits.print(Integer.MAX_VALUE);
        System.out.println(Bits.getWeight(Integer.MAX_VALUE));
        Bits.print(Integer.MIN_VALUE);
        System.out.println(Bits.getWeight(Integer.MIN_VALUE));
    }


    @Test
    public void testRotate() throws Exception {
        Bits.print(0xFF00FF00);
        Bits.rotate(0xFF00FF00, 3, true);
        Bits.rotate(0xFF00FF00, 3, false);
    }
}