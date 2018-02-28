package org.sheehan.algorithm.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bsheehan on 3/19/16.
 *
 * Condition factors out the Object monitor methods (wait, notify and notifyAll) into distinct objects to give the effect of having multiple wait-sets per object, by combining them with the use of arbitrary Lock implementations.
 * Where a Lock replaces the use of synchronized methods and statements, a Condition replaces the use of the Object monitor methods.
 Conditions (also known as condition queues or condition variables) provide a means for one thread to suspend execution (to "wait") until notified by another thread that some state condition may now be true.
 Because access to this shared state information occurs in different threads, it must be protected, so a lock of some form is associated with the condition.
 The key property that waiting for a condition provides is that it atomically releases the associated lock and suspends the current thread, just like Object.wait.

 A Condition instance is intrinsically bound to a lock.
 To obtain a Condition instance for a particular Lock instance use its newCondition() method.

 As an example, suppose we have a bounded buffer which supports put and take methods.
 If a take is attempted on an empty buffer, then the thread will block until an item becomes available; if a put is attempted on a full buffer, then the thread will block until a space becomes available.
 We would like to keep waiting put threads and take threads in separate wait-sets so that we can use the optimization of only notifying a single thread at a time when items or spaces become available in the buffer.
 This can be achieved using two Condition instances.
 */
public class BlockingQueueReentrant<T extends Comparable<T>> {

    Queue<T> queue = new LinkedList<T>();

    int capacity=0;

    final Lock lock = new ReentrantLock();
    final Condition addCondition = lock.newCondition();
    final Condition removeCondition = lock.newCondition();

    public BlockingQueueReentrant(int capacity){
        this.capacity=capacity;
    }

    public void add(T val) throws InterruptedException {
        try{
            this.lock.lock();
            while (queue.size()==capacity){
                System.out.println("waiting at capacity");
                addCondition.wait();
            }

            System.out.println("added " + val);
            queue.add(val);
            removeCondition.signal();
        }finally{
            lock.unlock();
        }

    }

    public T remove() throws InterruptedException {
        try {
            this.lock.lock();
            while (queue.size() == 0) {
                System.out.println("waiting at 0");
                removeCondition.wait();
            }

            T val = queue.remove();
            System.out.println("removed " + val);
            addCondition.notifyAll();
            return val;
        }finally{
            lock.unlock();
        }
    }
}
