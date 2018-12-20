package org.bsheehan.algorithms.math;

import org.bsheehan.data_structure.stack.ListStack;

public class Simple {

    public static void main(String[] args) {
        Simple.divisorsOf(20);
        Simple.divisorsOfBetter(20);
    }

    // O(n)
    static void divisorsOf(int n) {
        System.out.println(n);

        for (int i = 1; i <= n; i++) {
            if (n%i == 0)
                System.out.print(i + " ");
        }
        System.out.println();
    }

    // O(sqrt(n))
    static void divisorsOfBetter(int n) {
        System.out.println(n);
        ListStack<Integer> q = new ListStack<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n%i==0) {
                System.out.print(i + " ");
                q.push( n / i);
            }
        }
        while(q.size() != 0)
            System.out.print(q.pop() + " ");
    }


    static int factorial (int val){
        if (val == 0)
            return 1;
        return val*factorial(val-1);
    }

    static int fibonocci(int index) {
        if (index < 0)
            throw new RuntimeException("no negative indexes allowed");
        if (index <=1)
            return index;

        return fibonocci(index-1) + fibonocci(index - 2);
    }

    static int fibonocciIter(int index) {
        if (index < 0)
            throw new RuntimeException("no negative indexes allowed");

        int prev = 0;
        int curr = 1;
        if (index <=1)
            return index;

        for (int i = 2; i <= index; ++i) {
            int lastCurr = curr;
            curr = prev + curr;
            prev = lastCurr;
        }

        return curr;
    }


    static void hanoi(int n, char src, char dst, char tmp)
    {
        if (n == 0)
            return;
        hanoi(n-1, src, tmp, dst);
        System.out.println("Move " + n + " from " + src + " to " + dst) ;
        hanoi(n-1, tmp, dst, src);
    }

    public static int reverseInt(int n) {

        long reverse = 0;

        while(n != 0){
            long digit = n%10;
            reverse = reverse*10 + digit;
            n /= 10;
        }

        if (reverse < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        if (reverse > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        return (int)reverse;
    }
}
