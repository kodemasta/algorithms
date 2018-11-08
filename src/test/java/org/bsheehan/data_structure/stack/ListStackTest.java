package org.bsheehan.data_structure.stack;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.queue.ListQueue;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListStackTest extends BaseTest {
    @Test
    public void test() {
        super.test();
        ListStack<Integer> queue = new ListStack<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.print();
        System.out.println("pop: " + queue.pop());
        queue.print();
    }
}