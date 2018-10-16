package org.sheehan.algorithm.thread;

import junit.framework.TestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by bsheehan on 3/19/16.
 */
public class BlockingQueueTest extends TestCase {

    int cnt=0;

    synchronized public int increment(){
        cnt++;
        return cnt;
    }

    public void testMonitor() throws Exception {

        int CAPACITY=4;
        BlockingQueueMonitor<Integer> queue = new BlockingQueueMonitor<>(CAPACITY);

        int NUM_THREADS=10;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i=0; i<NUM_THREADS; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.add(increment());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i=0; i<NUM_THREADS; i++) {
            //Thread.sleep(1000);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public void testReentrant() throws Exception {

        int CAPACITY=4;
        BlockingQueueReentrant<Integer> queue = new BlockingQueueReentrant<>(CAPACITY);

        int NUM_THREADS=10;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i=0; i<NUM_THREADS; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.add(increment());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i=0; i<NUM_THREADS; i++) {
            //Thread.sleep(1000);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    // only a certain number of permits are available to to add to queue.
    // if number of permits exceeded then queue skips the add
    public void testSemaphore() throws Exception {

        int CAPACITY=2;
        BlockingQueueSemaphore<Integer> queue = new BlockingQueueSemaphore<>(CAPACITY);

        int NUM_THREADS=10;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        Future<?> futures[] = new Future<?>[NUM_THREADS];

        // create a bunch of threads that add
        for ( int i=0; i<NUM_THREADS; i++) {
            final int cnt = i;
            futures[i] = executor.submit(() -> {
                    try {
                        System.out.println("running add thread: " + Thread.currentThread().getName());
                        queue.add(cnt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            );
        }


//        // create a bunch of threads that remove
//        for (int i=0; i<NUM_THREADS/2; i++) {
//
//            futures[i+NUM_THREADS/2] =executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println("running remove thread: " + Thread.currentThread().getName());
//                        Thread.sleep(500);
//                        queue.remove();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

//        for (int i=0; i<NUM_THREADS; i++)
//            System.out.println("thread completed: " + futures[i].get());
//
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        queue.printQueue();

    }
}