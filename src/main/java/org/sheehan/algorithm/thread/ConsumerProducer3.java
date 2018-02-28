package org.sheehan.algorithm.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bob on 6/27/14.
 */
public class ConsumerProducer3 {

    final int MAX_MESSAGES = 10;

    public class Mailbox {

        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>();

        public String get() throws InterruptedException {
            String message = msgQueue.take();
            return message;
        }

        public void set(String message) throws InterruptedException {
            msgQueue.put(message);
        }
    }

    public class Producer implements Runnable{

        private final Mailbox mbox;
        private final String id;

        public Producer(Mailbox mailbox, String id){

            mbox = mailbox;
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_MESSAGES; ++i){
                try {
                    mbox.set(id + " : " + i);
                    //System.out.println(id +" enqueue: " +  i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Consumer implements Runnable{
        private final Mailbox mbox;
        private final String id;

        public Consumer(Mailbox mailbox, String id){
            mbox = mailbox; this.id = id;
        }
        @Override
        public void run() {
            for (int i = 0; i < MAX_MESSAGES; ++i) {
                try {
                    System.out.println(id +" get: " +  mbox.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static public void main(String args[]) throws InterruptedException {
        ConsumerProducer3 tu = new ConsumerProducer3();
        ConsumerProducer3.Mailbox mailbox = tu.new Mailbox();

        Thread p1 = new Thread(tu.new Producer(mailbox, "P1"), "Producer");
        Thread c1 = new Thread(tu.new Consumer(mailbox, "C1"), "Consumer");
        Thread p2 = new Thread(tu.new Producer(mailbox, "P2"), "Producer");
        Thread c2 = new Thread(tu.new Consumer(mailbox, "C2"), "Consumer");

        c1.start();
        p1.start();
        c2.start();
        p2.start();
        p1.join(5000);
        p2.join(5000);


    }

}
