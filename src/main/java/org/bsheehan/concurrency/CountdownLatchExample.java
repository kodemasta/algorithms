package org.sheehan.algorithm.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bsheehan on 3/20/16.
 */
public class CountdownLatchExample {

    static class MyService implements Runnable {

        CountDownLatch latch;
        String name;

        public MyService(String name, CountDownLatch latch){
            this.latch = latch;
            this.name=name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.name + " completed run - countdown ");

            // Decrements the count of the latch, releasing all waiting threads if the count reaches zero.
            latch.countDown();
        }
    }

    public static void main(String []args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0; i<5; ++i){
            executor.submit(new MyService(String.valueOf(i), latch));
        }

        latch.await();
        System.out.println("OK ALL 5 UP AND RUNNING - Latch Open");

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
