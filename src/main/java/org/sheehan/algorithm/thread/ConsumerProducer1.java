package org.sheehan.algorithm.thread;

/**
 * Created by bob on 6/27/14.
 */
public class ConsumerProducer1 {

    Object product;

    public class Producer implements Runnable{

        @Override
        public void run() {
            while (true) { // produce until interrupted !!
                synchronized (ConsumerProducer1.this) { //acquire the implicit lock
                    System.out.println("producer acquires lock");
                    product = new Object(); //produce product
                    System.out.println("\tPRODUCE:" + product.toString());
                    System.out.println();
                    ConsumerProducer1.this.notify();
                    System.out.println("\t\tproducer releases lock - notified");
                } //release the intrinsic lock for other notified threads to acquire

                try {
                    System.out.println("producer sleeps");
                    Thread.sleep(500);
                    System.out.println("producer wakes up !");
                } catch (InterruptedException e) {
                    System.out.println("producer interrupted during sleep");
                    return; // sleep interrupted return
                }
                if (Thread.interrupted()) {
                    System.out.println("producer interrupted during run");
                    return; // thread was running but interrupt flag enqueue so return
                }
            }
        }
    }

    // This runnable keeps waits
    public class Consumer implements Runnable{

        @Override
        public void run() {
            while(product == null) { //keep checking for new products
                synchronized (ConsumerProducer1.this) { //acquire the implicit lock
                    System.out.println("consumer acquires lock");
                    try {
                        System.out.println("\t\tconsumer releases lock - waiting");
                        ConsumerProducer1.this.wait();
                        System.out.println("consumer acquires lock - woke up !");
                        System.out.println("\tCONSUME:" + product.toString());
                        System.out.println();
                        product = null; //consume product
                    } catch (InterruptedException e) {
                        System.out.println("consumer interrupted during wait");
                        return; // sleep interrupted return
                    }
                    System.out.println("\t\tconsumer releases lock");
                } //release the intrinsic lock for other notified threads to acquire
                if (Thread.interrupted()) {
                    System.out.println("consumer interrupted during run");
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

        Thread.sleep(5000);

        consume.interrupt();
        produce.interrupt();

        //produce.join(1000);
        //consume.join(1000);
    }


}
