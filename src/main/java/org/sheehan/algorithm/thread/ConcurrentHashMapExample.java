package org.sheehan.algorithm.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by bsheehan on 3/20/16.
 */
public class ConcurrentHashMapExample {

    private static final String CONCURRENCY_LEVEL_DEFAULT = "50";
    private static final String CONCURRENCY_KEY = "concurrency";

    private ConcurrentMap<Double, Double> sqrtCache = new ConcurrentHashMap<Double, Double>();

    // 4 steps as outlined above
    public double getOrPut(Double d) {
        Double sqrt = sqrtCache.get(d);
        if(sqrt == null) {
            sqrt = Math.sqrt(d);
            //System.out.printf("calculated sqrt of %s = %s%n ", d, sqrt);
            try {
                Thread.sleep(1); //change this to get race condition for putIfAbsent.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Double existing = sqrtCache.putIfAbsent(d, sqrt);
            if(existing != null) {
                System.out.printf("DISCARD calculated %s sqrt %s and use the cached sqrt %s\n", d, sqrt, existing);
                sqrt = existing;
            }else{
                System.out.printf("store and return %s calculated sqrt %s\n", d, sqrt);
            }
        }else
            System.out.printf("return %s calculated sqrt %s\n", d, sqrt);

        return sqrt;
    }

    public static void main(String args[]) {
        String testVal[] = {"1.0", "2.0","3.0","4.0","5.0", "6.0","7.0","8.0"};

        final ConcurrentHashMapExample test = new ConcurrentHashMapExample();
        final int concurrencyLevel = Integer.parseInt(System.getProperty(CONCURRENCY_KEY, CONCURRENCY_LEVEL_DEFAULT));
        final ExecutorService executor = Executors.newCachedThreadPool();

        try {
            for(int i = 0; i < concurrencyLevel; i++) {
                for(String s : testVal) {
                    final Double d = Double.valueOf(s);
                    executor.submit(new Runnable() {
                        @Override public void run() {
                            test.getOrPut(d);
                            //System.out.printf("sqrt of %s = %s in thread %s%n", d, test.getOrPut(d), Thread.currentThread().getName());
                        }
                    });
                }
            }
        } finally {
            executor.shutdown();
        }
    }
}
