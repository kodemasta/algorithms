package org.bsheehan.data_structure.string.algorithm;

import org.bsheehan.BaseTest;
import org.junit.Test;

public class StringTest extends BaseTest {

    @Test
    public void test2str() {
        super.test();
        System.out.println(StringAlgs.int2str(123));
        System.out.println(StringAlgs.int2str(-123));
    }

    @Test
    public void test2int() {
        super.test();
        System.out.println(StringAlgs.str2int("123"));
        System.out.println(StringAlgs.str2int("-123"));
    }
}