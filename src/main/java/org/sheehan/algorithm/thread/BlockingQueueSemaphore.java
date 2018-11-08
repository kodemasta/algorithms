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

    // when threads add they acquire. We only want a limited number of threads to add
    public void add(T val) throws InterruptedException {
        try {

            // TEST IF PERMITS ARE AVAILABLE, IF NOT SKIP
            // IF THERE ARE FEW PERMITS AND LOTS OF THREADS MANY WILL BE SKIPPED
            // KIND OF A THROTTLE
            if (!semaphore.tryAcquire(1)) {
                System.out.println("...cannot acquire semaphore to add " + val + ", available permits " + semaphore.availablePermits());
                System.out.println("skipped " + val);
                return;
            }
            System.out.println("...acquiring semaphore to add " + val + ", available permits " + semaphore.availablePermits());

            queue.add(val);
            System.out.println("added " + val);

            // RELEASE PERMIT. DONE WITH TASK
            semaphore.release(1);
            System.out.println("...released semaphore to add " + val + ", available permits " + semaphore.availablePermits());

        } finally { }
    }

    // when threads remove they release toa llow more threads to add.
    public int remove() throws InterruptedException {
//        try {
//
//            T val = stack.remove();
//
//            System.out.println("removed " + val);
//            //printQueue();
//            return val;
//        } finally {
//            semaphore.release(1);
//            System.out.println("...released semaphore to remove, available permits " + semaphore.availablePermits());
//        }

        return 0;
    }

    public void printQueue(){
        System.out.print('{');
        queue.forEach(x-> {
              System.out.print(" ");
              System.out.print(x);
        });
        System.out.println(" }");
    }
}
