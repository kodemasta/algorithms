package org.sheehan.algorithm.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by bsheehan on 3/19/16.
 */
public class BlockingQueueSemaphore<T extends Comparable<T>> {

    Queue<T> queue = new LinkedList<T>();

    Semaphore semaphore; //acquire and release permits.

    int capacity=0;

    public BlockingQueueSemaphore(int capacity){
        this.capacity=capacity;
        semaphore=new Semaphore(capacity);
    }

    public void add(T val) throws InterruptedException {
        System.out.println("trying to add, available permits " + semaphore.availablePermits());
        semaphore.acquire();
        System.out.println("added available permits " + semaphore.availablePermits());
        queue.add(val);
    }

    public T remove() throws InterruptedException {
        try {
            T val = queue.remove();
            return val;
        }finally{
            semaphore.release();
            System.out.println("removed available permits " + semaphore.availablePermits());
        }
    }

    synchronized public void printQueue(){
        queue.forEach(x-> {
              System.out.print(" ");
              System.out.print(x);
        });
        System.out.println();
    }
}
