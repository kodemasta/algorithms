package org.bsheehan.concurrency;

/**
 * Created by bsheehan on 3/1/16.
 */
public class ReadWriteLock {
    int numReaders = 0;
    int numWriters = 0;
    int numWriterRequesters = 0; // prioritizes writers in wait before new reads

    // no reading allowed if writers are on the scene
    // but welcome to have many readers if that all there is !
    synchronized void acquireReadLock() throws InterruptedException {
        while (numWriters > 0 || numWriterRequesters > 0) {
            System.out.println(Thread.currentThread().getName() + " read lock wait");
            wait();
        }
        numReaders++;
        this.print();
    }

    public void print() {
        System.out.println("ReadWriteLock - " + Thread.currentThread().getName() + " readers:" + numReaders + " writers:" + numWriters + " writeRequestors:" + numWriterRequesters);

    }

    synchronized void acquireWriteLock() throws InterruptedException {
        numWriterRequesters++;
        while (numWriters > 0 || numReaders > 0) {
            System.out.println(Thread.currentThread().getName() + " write lock wait");
            wait();
        }
        numWriterRequesters--;
        numWriters++;
        this.print();

    }

    synchronized void releaseReadLock() {

        System.out.println(Thread.currentThread().getName() + " read lock release");
        --numReaders;
        notifyAll();
        this.print();

    }

    synchronized void releaseWriteLock() {
        System.out.println(Thread.currentThread().getName() + " write lock release");
        --numWriters;
        notifyAll();
        this.print();

    }

    public static void main(String []args) throws InterruptedException {
        ReadWriteLock lock = new ReadWriteLock();

        Thread r1 = new Thread(() -> {
                try {
                    lock.acquireReadLock();
                    System.out.println(Thread.currentThread().getName() + " READING");
                    Thread.sleep(100);
                    lock.releaseReadLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        , "Reader Thread 1");

        Thread r2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireReadLock();
                    System.out.println(Thread.currentThread().getName() + " READING");
                    Thread.sleep(200);
                    lock.releaseReadLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader Thread 2");


        Thread w1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireWriteLock();
                    System.out.println(Thread.currentThread().getName() + " WRITING");
                    Thread.sleep(10);
                    lock.releaseWriteLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Writer Thread 3");


        Thread w2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireWriteLock();
                    System.out.println(Thread.currentThread().getName() + " WRITING");
                    Thread.sleep(10);
                    lock.releaseWriteLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Writer Thread 4");

        w1.start();
        w2.start();
        r1.start();
        r2.start();

        Thread.sleep(5000);


    }
}
