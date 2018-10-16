package org.sheehan.algorithm.thread;

import java.util.concurrent.*;

/**
 * Created by bsheehan on 3/20/16.
 */
public class CyclicBarrierExample {

    static class MyService implements Runnable {


        // a global barrier exists across all threads
        // Last thread to meet limit runs first.. then in order of arrival
        CyclicBarrier barrier;
        String name;

        public MyService(String name, CyclicBarrier barrier){
            this.barrier = barrier;
            this.name=name;
        }

        @Override
        public void run() {
            try {
                // threads waiting
                System.out.println(this.name + " waiting " + barrier.getNumberWaiting() + " of " + barrier.getParties());
                int arrivalIndex = barrier.await();
                System.out.println(this.name + " arrival Index " +  arrivalIndex + " tripped " + barrier.getNumberWaiting() + " of " + barrier.getParties());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


            //System.out.println(this.name + " after latch countdown " + latch.getCount());
        }
    }

    public static void main(String []args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("BARRIER TRIPPED"));

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0; i<5; ++i){
            executor.submit(new MyService("BOB'S THREAD " + String.valueOf(i), barrier));
        }


        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("OK ALL DONE");

    }
}
