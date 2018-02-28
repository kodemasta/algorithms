package org.sheehan.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 2/27/16.
 */
public class NumericalTest {

    @Test
    public void testRobot(){
        List<String> strings = Numerical.robotPaths(1);
//        for (String s:strings){
//            System.out.println(s);
//        }
        System.out.println("number of paths: "+ strings.size());

        strings = Numerical.robotPaths(2);
        System.out.println("number of paths: "+ strings.size());
        strings = Numerical.robotPaths(3);
        System.out.println("number of paths: "+ strings.size());
        strings = Numerical.robotPaths(4);
        System.out.println("number of paths: "+ strings.size());
        strings = Numerical.robotPaths(5);
        System.out.println("number of paths: "+ strings.size());
    }

//    @Test
//    public void testCntAndSay(){
//        String s = "1";
//        for (int i=0; i<5;++i){
//            s = Numerical.countAndSay(s);
//            System.out.println(s);
//        }
//
//    }

    @Test
    public void testGcd(){
        System.out.println(Numerical.gcd(5,15));
        System.out.println(Numerical.gcd(32,12));
    }

    @Test
    public void testFactors(){
        List<Integer> factors = new ArrayList<Integer>();
        Numerical.getFactors(20, factors);
        System.out.println(factors);
    }

    @Test
    public void testGetPrimes(){
        Numerical.getPrimes(1000);

    }
}