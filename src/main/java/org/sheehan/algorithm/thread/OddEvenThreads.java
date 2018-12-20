package org.sheehan.algorithm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class OddEvenThreads implements Runnable {


    private int max;
    private PrintMonitor print;
    private boolean isEvenNumber;

    OddEvenThreads(PrintMonitor print, int max, boolean isEvenNumber){
        this.print = print;
        this.max = max;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {

        int number = isEvenNumber == true ? 2 : 1;
        while(number<= max){
            if(isEvenNumber)
                print.printEven(number);
            else
                print.printOdd(number);

            number+=2;
        }
    }

    public static void main(String ... args){
        PrintMonitor print = new PrintMonitor();
        Thread t1 = new Thread(new OddEvenThreads(print, 10, false));
        t1.setName("ODD");
        Thread t2 = new Thread(new OddEvenThreads(print, 10, true));
        t2.setName("EVEN");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintMonitor {
    boolean isOdd= false;

    Lock lock = new ReentrantLock();
    Condition oddCondition, evenCondition;

    public PrintMonitor(){
        oddCondition = lock.newCondition();
        evenCondition = lock.newCondition();
    }

    void printEven(int number) {

        //todo verify number is even
        try {
            lock.lock();
            while (isOdd == false) {
                try {
                    evenCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // isOdd now true
            System.out.print(" " + number + " " +  Thread.currentThread().getName());
            isOdd = false;
            oddCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    void printOdd(int number) {
        //todo verify number is odd
        try {
            lock.lock();
            while (isOdd == true) {
                try {
                    oddCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(" " + number + " "  + Thread.currentThread().getName());
            isOdd = true;
            evenCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
