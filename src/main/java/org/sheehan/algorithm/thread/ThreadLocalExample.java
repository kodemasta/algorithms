package org.sheehan.algorithm.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bob on 9/16/14.
 */

public class ThreadLocalExample {


    public static void main(String args[]){

        ThreadLocal<Integer> threadLocalId = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue()
            {
                Random rand = new Random();
                return rand.nextInt(1000);
            }
        };

        Set<Runnable> runnables = new HashSet<>();

        for (int i = 0; i < 10; i++){
            final int ii = i;
            runnables.add(() -> {System.out.println("thread id: " + Thread.currentThread().getId() + " thread local id:" + threadLocalId.get());});
        }

        // number of threads will create number of local vars !
        ExecutorService es = Executors.newFixedThreadPool(2);
        ThreadFactory.execute(es,runnables);

        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
