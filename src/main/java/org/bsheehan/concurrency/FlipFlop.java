package org.bsheehan.concurrency;

public class FlipFlop {

    static Object sharedLock = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 2 threads and 1 printer with 1 sharedLock !
        SharedPrinter p = new SharedPrinter(10, sharedLock);
        Thread flip = new Thread(p, "flip");
        Thread flop = new Thread(p, "flop");

        flip.start();
        flop.start();

        flip.join();
        flop.join();
    }


    static class SharedPrinter implements Runnable {

        Object sharedLock;
        int max = 1;
        boolean toggle = false;

        public SharedPrinter(int max, Object lock) {
            this.sharedLock = lock;
            this.max = max;
        }

        @Override
        public void run() {
            for (int i = 0; i < max; i++) {
                synchronized (sharedLock) { // current thread gets sharedLock. Needed for wait notify on sharedLock to work !
                    toggle = !toggle;
                    if (toggle)
                        System.out.println("flip:"+Thread.currentThread().getName());
                    else
                        System.out.println("flop:"+Thread.currentThread().getName());
                    try {
                        sharedLock.notify(); // Wakes up a single sleeping thread that is waiting on this object's monitor.
                        sharedLock.wait(); // current thread release sharedLock and waits. Now other thread can work !
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}