package org.bsheehan.algorithms.math;

import org.bsheehan.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountAndSayTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        System.out.println("0122333444");
        System.out.println(CountAndSay.countAndSay("0122333444"));

        System.out.println("01223334");
        System.out.println(CountAndSay.countAndSay("01223334"));
    }
}