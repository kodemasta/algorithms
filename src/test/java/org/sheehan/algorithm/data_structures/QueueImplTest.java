package org.sheehan.algorithm.data_structures;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;
import org.sheehan.algorithm.data_structures.queue.QueueListImpl;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueueImplTest {

    @Test
    public void testAddOverflow() {
        try {
            int size = 10;
            QueueInterface q = new QueueArrayImpl<Integer>(size);

            for (int i = 0; i < 10; ++i) {
                q.enqueue(i);
                q.print();
                //q.printArray();
            }

            for (int i = 0; i < 5; ++i) {
                q.dequeue();
                q.print();
                //q.printArray();
            }

            for (int i = 10; i < 15; ++i) {
                q.enqueue(i);
                q.print();
                //q.printArray();
            }
        }catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddOverflowList() {
        try {
            int size = 10;
            QueueInterface q = new QueueListImpl<Integer>();

            for (int i = 0; i < 10; ++i) {
                q.enqueue(i);
                q.print();
                q.printArray();
            }

            for (int i = 0; i < 5; ++i) {
                q.dequeue();
                q.print();
                q.printArray();
            }

            for (int i = 10; i < 15; ++i) {
                q.enqueue(i);
                q.print();
                q.printArray();
            }
        }catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddRemove() throws Exception {
        int size = 10;
        QueueInterface<Integer> q = new QueueArrayImpl<>(size);

        for (int i = 0; i < size; ++i){
            q.enqueue(i);
            q.print();
        }

        for (int i = 0; i < size; ++i){
            Integer remove = q.dequeue();
            assertEquals(i, remove.intValue());
            q.print();
        }

        q.enqueue(10);
        q.enqueue(20);
        assertEquals(10, q.dequeue().intValue());
        q.enqueue(30);
        q.enqueue(40);
        assertEquals(20, q.dequeue().intValue());

    }

    @Test
    public void testAddRemove2() throws Exception {
        int size = 10;
        QueueInterface q = new QueueArrayImpl<Integer>(size);

        // fill stack
        for (int i = 0; i < size; ++i){
            q.enqueue(i);
            q.print();
        }

        //dequeue half
        for (int i = 0; i < size/2; ++i){
            q.dequeue();
            q.print();
        }

        // enqueue 5 more stack
        for (int i =  size/2; i < size; ++i){
            q.enqueue(i * 10);
            q.print();
        }

        //dequeue half
        for (int i = 0; i < size/2; ++i){
            q.dequeue();
            q.print();
        }
    }

    @Test
    public void testAddRemove3() {
        int size = 10;
        QueueInterface q = new QueueArrayImpl<Integer>(size);
        q.enqueue(5);
        Integer val = (Integer)q.dequeue();
        assertEquals(new Integer(5), val);

    }
}

