package org.sheehan.algorithm.thread;



import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bob on 6/27/14.
 */
public class ConsumerProducer2 {

    final int MAX_MESSAGES = 10;

    // object that threads will synchronize on (see the get/enqueue methods)
    public class Mailbox {

        Queue<String> messages = new LinkedList<String>();

        synchronized public String get() throws InterruptedException {
            System.out.println("get acquire lock");
            while(messages.peek() == null) {
                System.out.println("get release lock - wait");
                try {
                    wait();
                }catch(InterruptedException e){
                    System.out.println("mbox get wait interrupted");
                    throw e;
                }
                System.out.println("get acquire lock - wait end");
            }
            System.out.println("dequeue message");
            String message = messages.remove();
            notifyAll();
            System.out.println("get release lock");
            return message;
        }

        synchronized public void add(String message) throws InterruptedException {
            System.out.println("enqueue acquire lock");
            //thread wait until msgQueue have been read
            while(messages.size() >= MAX_MESSAGES) {
                System.out.println("enqueue release lock - wait");
                try {
                    wait();
                }catch(InterruptedException e){
                    System.out.println("mbox enqueue wait interrupted");
                    throw e;
                }
                System.out.println("enqueue acquire lock - wait end");
            }

            System.out.println("ADD message");
            messages.add(message);
            notifyAll();
            System.out.println("enqueue release lock");
        }
    }

    public class Producer implements Runnable{

        private final Mailbox mbox;
        public Producer(Mailbox mailbox){
            mbox = mailbox;
        }

        int message = 0;

        @Override
        public void run() {
            while(true){
                try {
                    mbox.add("message " + message);
                    System.out.println("PRODUCE: " + "message " + message++);
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500); // control rate of production
                } catch (InterruptedException e) {
                    System.out.println("producer interrupt  exception");
                    return;
                }

                if (Thread.interrupted()) {
                    System.out.println("producer interrupted during run");
                    return; // thread was running but interrupt flag enqueue so return
                }
            }
        }
    }

    public class Consumer implements Runnable{
        private final Mailbox mbox;
        public Consumer(Mailbox mailbox){
            mbox = mailbox;
        }
        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println("CONSUME: " + mbox.get());
                    System.out.println();
                } catch (InterruptedException e) {
                    System.out.println("consumer interrupted exception");
                    return;

                }

                if (Thread.interrupted()) {
                    System.out.println("consumer interrupted during run");
                    return; // thread was running but interrupt flag enqueue so return
                }
            }
        }
    }



    public static void main(String []args) throws InterruptedException {

        ConsumerProducer2 consumerProducer2 = new ConsumerProducer2();
        ConsumerProducer2.Mailbox mailbox = consumerProducer2.new Mailbox();

        Thread produce = new Thread(consumerProducer2.new Producer(mailbox), "Producer");
        Thread consume = new Thread(consumerProducer2.new Consumer(mailbox), "Consumer");
        produce.start();
        consume.start();

        Thread.sleep(5000);

        consume.interrupt();
        produce.interrupt();



    }


}
