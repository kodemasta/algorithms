package org.sheehan.algorithm.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bsheehan on 3/19/16.
 */
public class BlockingQueueMonitor<T extends Comparable<T>> {

    Queue<T> queue = new LinkedList<T>();

    int capacity=0;

    public BlockingQueueMonitor(int capacity){
        this.capacity=capacity;
    }

    synchronized public void add(T val) throws InterruptedException {
        while (queue.size()==capacity){
            System.out.println("waiting at capacity");
            this.wait();
        }

        System.out.println("added " + val);
        queue.add(val);
        notifyAll();
    }

    synchronized public T remove() throws InterruptedException {
        while (queue.size()==0){
            System.out.println("waiting at 0");
            this.wait();
        }

        T val = queue.remove();
        System.out.println("removed " + val);
        notifyAll();
        return val;
    }


}
