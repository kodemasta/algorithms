package org.sheehan.algorithm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Based on Executors thread pool
 */
public class ThreadFactory {
    public static void execute(ExecutorService executorService, Runnable runnable, int numTasks) {
        for (int i =0; i < numTasks; ++i) {
            executorService.execute(runnable);
        }
    }

    public static void execute(ExecutorService executorService, Set<Runnable> runnables) {
        for (Runnable runnable: runnables) {
            executorService.execute(runnable);
        }
    }

    public static List<Future<?>> submit(ExecutorService executorService, Runnable runnable, int numTasks) {
        List<Future<?>> futures = new ArrayList<>();
        for (int i =0; i < numTasks; ++i) {
            futures.add(executorService.submit(runnable));
        }
        return futures;
    }

    public static List<Future<Integer>> submit(ExecutorService executorService, Callable<Integer> callable, int numTasks) {
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i =0; i < numTasks; ++i) {
            futures.add(executorService.submit(callable));
        }
        return futures;
    }

    public static List<Future<Integer>> submit(ExecutorService executorService, Set<Callable<Integer>> callables) {
        List<Future<Integer>> futures = new ArrayList<>();
        try {
            return executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futures;
    }
}
