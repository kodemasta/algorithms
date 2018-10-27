package org.sheehan.algorithm.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bob on 6/27/14.
 */
public class ConsumerProducer1 {

    Object sharedResource = null;
    ReentrantLock lock = new ReentrantLock();

    public class Producer implements Runnable{

        @Override
        public void run() {
            while (true) { // produce until interrupted !!

                // this is critical section.. wait for lock here
                System.out.println("<producer thread waits for lock");
                synchronized (lock) { //acquire the implicit lock
                    System.out.println("\t<producer thread acquires lock");
                    if (sharedResource == null) {
                        sharedResource = new Object(); //produce sharedResource
                        System.out.println("\t<***PRODUCE SHARED RESOURCE:" + sharedResource.toString());
                    }

                    lock.notify();
                    System.out.println("\t\t<lock notifies consumer thread");

                } //release the intrinsic lock for other notified threads to acquire

//                Thread.yield(); // not recommended

                // use this to control rate of production
                try {
                    System.out.println("\t\t<producer sleeps");
                    System.out.println();
                    System.out.println();
                    Thread.sleep(1000);
                    System.out.println("\t\t<producer wakes up !");
                } catch (InterruptedException e) {
                    System.out.println("<producer interrupted during sleep");
                    return; // sleep interrupted return
                }
                if (Thread.interrupted()) {
                    System.out.println("<producer interrupted during run");
                    return; // thread was running but interrupt flag enqueue so return
                }
            }
        }
    }

    // This runnable keeps waits
    public class Consumer implements Runnable{

        @Override
        public void run() {
            while(true) { //keep checking for new products
                System.out.println(">consumer waits for lock");
                synchronized (lock) { //acquire the implicit lock
                    System.out.println("\t>consumer acquires lock");
                    try {
                        if (sharedResource == null) {
                            System.out.println("\t\t>lock releases consumer hold - waiting");
                            lock.wait();
                        } else {
                            System.out.println(">consumer acquires lock - woke up !");
                            System.out.println("\t>***CONSUME SHARED RESOURCE:" + sharedResource.toString());
                            sharedResource = null; //consume sharedResource
                            System.out.println("\t\t>consumer releases lock - end of block scope");
                        }
                    } catch (InterruptedException e) {
                        System.out.println(">consumer interrupted during wait");
                        return; // sleep interrupted return
                    }
                } //release the intrinsic lock for other notified threads to acquire
                if (Thread.interrupted()) {
                    System.out.println(">consumer interrupted during run");
                    return; // thread was running but interrupt flag enqueue so return
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ConsumerProducer1 tu = new ConsumerProducer1();
        Thread produce = new Thread(tu.new Producer(), "Producer");
        Thread consume = new Thread(tu.new Consumer(), "Consumer");

        consume.start();
        produce.start();

        Thread.sleep(2000); // terminate after 5 secs.. otherwise does not end !

        consume.interrupt();
        produce.interrupt();

        //produce.join(1000);
        //consume.join(1000);
    }


}
