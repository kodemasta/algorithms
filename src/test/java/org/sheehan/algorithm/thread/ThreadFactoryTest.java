package org.sheehan.algorithm.thread;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ThreadFactoryTest {

    @Test
    public void executeRunnable() {
        Runnable runnable = () -> {
            System.out.println(1);
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadFactory.execute(executorService, runnable, 10);
        executorService.shutdown();
    }

    @Test
    public void executeRunnableSet() {
        Set<Runnable> runnables = new HashSet<>();

        for (int i = 0; i < 10; i++){
            final int ii = i;
            runnables.add(() -> {System.out.print(ii);});
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadFactory.execute(executorService, runnables);
        executorService.shutdown();
    }

    @Test
    public void submitRunnable() {
        Runnable runnable = () -> {
            System.out.println(1);
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = ThreadFactory.submit(executorService, runnable, 10);
        for (Future<?> future: futures){
            System.out.println("isDone:" + future.isDone());
        }
        executorService.shutdown();
    }

    @Test
    public void submitCallable() {
        Callable<Integer> callable = () -> {
            return 1;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = ThreadFactory.submit(executorService, callable, 10);

        for (Future<?> future: futures){
            try {
                System.out.println("get:"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    @Test
    public void submitCallableSet() {
        Set<Callable<Integer>> callables = new HashSet<>();

        for (int i = 0; i < 10; i++){
            final int ii = i;
            callables.add(() -> {return ii;});
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = ThreadFactory.submit(executorService, callables);

        for (Future<Integer> future: futures){
            try {
                System.out.println("get:"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}