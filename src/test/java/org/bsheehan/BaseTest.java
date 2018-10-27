package org.bsheehan;

import org.junit.Rule;
import org.junit.rules.TestName;

public class BaseTest {
    @Rule
    public TestName name= new TestName();

    public void test() {
        System.out.println("---TEST:" + this.name.getMethodName());
    }
}
