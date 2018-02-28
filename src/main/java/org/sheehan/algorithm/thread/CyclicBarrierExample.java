package org.sheehan.algorithm.thread;

import java.util.concurrent.*;

/**
 * Created by bsheehan on 3/20/16.
 */
public class CyclicBarrierExample {

    static class MyService implements Runnable {

        CyclicBarrier barrier;
        String name;

        public MyService(String name, CyclicBarrier barrier){
            this.barrier = barrier;
            this.name=name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.name + " waiting run " + barrier.getParties());
                barrier.await();
                System.out.println(this.name + " completed run " + barrier.getParties());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


            //System.out.println(this.name + " after latch countdown " + latch.getCount());
        }
    }

    public static void main(String []args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0; i<5; ++i){
            executor.submit(new MyService(String.valueOf(i), barrier));
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
