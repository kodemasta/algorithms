package org.bsheehan.data_structure.queue;

import org.bsheehan.BaseTest;
import org.junit.Test;

public class ListQueueTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        ListQueue<Integer> queue = new ListQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();
        System.out.println("dequeue: " + queue.dequeue());
        queue.print();
    }
}