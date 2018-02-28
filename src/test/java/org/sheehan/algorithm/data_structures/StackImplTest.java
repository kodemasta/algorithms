package org.sheehan.algorithm.data_structures;

import junit.framework.TestCase;
import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;
import org.sheehan.algorithm.data_structures.stack.StackListImpl;

public class StackImplTest extends TestCase {

    public void testPushPop() throws Exception {
        int size = 10;
        Stack<Integer> stack = new StackArrayImpl<>(size);
        for (int i = 0; i < size; ++i){
            stack.push(i);
            stack.print();
        }

        for (int i = 0; i  < size; ++i){
            stack.pop();
            stack.print();
        }
    }

    public void testPushPopList() throws Exception {

        Stack<Integer> stack = new StackListImpl<>();
        for (int i = 0; i <10; ++i){
            stack.push(i);
            System.out.println("pushed: " + i);
            stack.print();
        }

        for (int i = 0; i  < 10; ++i){
            Integer pop = stack.pop();
            System.out.println("popped: "+ pop);
            stack.print();
        }
    }

    public void testPop() throws Exception {

    }
}