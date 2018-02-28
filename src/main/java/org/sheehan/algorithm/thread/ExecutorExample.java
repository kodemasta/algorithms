package org.sheehan.algorithm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bob on 7/1/14.
 */
public class ExecutorExample {
    static class Worker implements Runnable {

        final private String command;

        public Worker(String command) {
            this.command = command;
        }

        @Override
        public void run() {
            System.out.println("worker thread start: " + this.command);
            processCommand(command);
            System.out.println("worker thread end: " + this.command);
        }

        private void processCommand(String command) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        final int NUM_THREADS = 5;
        //sequential execution of each thread
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < NUM_THREADS; ++i)
            es.submit(new Worker(new Integer(i).toString()));
        es.shutdown();
        while (!es.isTerminated()) {
        }
        System.out.println("Finished all threads");

        // 3 threads at a time
        ExecutorService es2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < NUM_THREADS; ++i)
            es2.submit(new Worker(new Integer(i).toString()));

        es2.shutdown();
        while (!es2.isTerminated()) {
        }
        System.out.println("Finished all threads");


    }
}
