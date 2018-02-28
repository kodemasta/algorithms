package org.sheehan.algorithm;

import junit.framework.Assert;
import org.junit.Test;
import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 5/20/14
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecursionTest {
    @Test
    public void testFactorial() throws Exception {
        Assert.assertEquals(24, Recursion.factorial(4));
    }

    @Test
    public void testFibonocci() throws Exception {
        Assert.assertEquals(5, Recursion.fibonocci(5));
        Assert.assertEquals(5, Recursion.fibonocciIter(5));
        Assert.assertEquals(8, Recursion.fibonocci(6));
        Assert.assertEquals(8, Recursion.fibonocciIter(6));
    }

    @Test
    public void testHanoi() {
        Recursion.hanoi(3, 'A', 'C', 'B');
    }

    @Test
    public void testHanoi2() {
        int size = 3;
        Stack<Integer> source = new StackArrayImpl<>(size, "A");
        Stack<Integer> dest = new StackArrayImpl<>(size, "C");
        Stack<Integer> spare = new StackArrayImpl<>(size, "B");

        for (int i = 0; i < size; ++i)
            source.push(size-i-1);

        source.print();
        spare.print();
        dest.print();

        Recursion.hanoiStack(size, source, dest, spare);
    }

//    @Test
//    public void testBalParenthesis() {
//        char str[] = new char[10];
//        Recursion.generateBalancedParenthesis(5, 5, str, 0);
//    }
}

