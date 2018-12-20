package org.bsheehan.algorithms.dp;

import org.bsheehan.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class EggDropTest  extends BaseTest {

    @Test
    public void test() {
        super.test();
        System.out.println(EggDrop.recursive(2, 10));
        System.out.println(EggDrop.dp(2, 10));
        System.out.println(EggDrop.recursive(3, 20));
        System.out.println(EggDrop.dp(3, 20));
    }
}