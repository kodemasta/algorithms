package org.sheehan.algorithm.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class Fibonocci {
    Map<Integer,Integer> solutions = new HashMap<Integer, Integer>();

    public static void main(String args[]){
        Fibonocci f = new Fibonocci();
        System.out.println(f.solveRecursive(6));
        System.out.println(f.solveDP(6));
    }

    private Fibonocci() {
        this.solutions.put(0,0);
        this.solutions.put(1,1);
    }

    // O(n)
    private int solveDP(int n) {
        if (this.solutions.containsKey(n)) return this.solutions.get(n);

        int val =  solveDP(n-1) + solveDP(n-2);
        this.solutions.put(n, val);

        return val;
    }

    // NP O(2^n)
    private int solveRecursive(int n) {
        if (n==0)
            return 0;
        else if (n==1)
            return 1;

        return solveRecursive(n-1) + solveRecursive(n-2);
    }
}
