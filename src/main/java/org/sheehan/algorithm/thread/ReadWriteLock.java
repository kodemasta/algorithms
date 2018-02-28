package org.sheehan.algorithm.thread;

/**
 * Created by bsheehan on 3/1/16.
 */
public class ReadWriteLock {
    int numReaders = 0;
    int numWriters = 0;
    int numWriterRequesters = 0;

    synchronized void acquireReadLock() throws InterruptedException {
        while (numWriters > 0 || numWriterRequesters > 0) {
            System.out.println(Thread.currentThread().getName() + " read lock wait");
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " has lock");
        numReaders++;

    }

    synchronized void acquireWriteLock() throws InterruptedException {
        numWriterRequesters++;
        while (numWriters > 0 || numReaders > 0) {
            System.out.println(Thread.currentThread().getName() + " write lock wait");
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " has lock");
        numWriterRequesters--;
        numWriters++;

    }

    synchronized void releaseReadLock() {

        System.out.println(Thread.currentThread().getName() + " read lock release");
        --numReaders;
        notifyAll();

    }

    synchronized void releaseWriteLock() {
        System.out.println(Thread.currentThread().getName() + " write lock release");
        --numWriters;
        notifyAll();

    }

    public static void main(String []args) {
        ReadWriteLock lock = new ReadWriteLock();

        Thread r1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireReadLock();
                    System.out.println("--read 1 reading");
                    Thread.sleep(100);
                    lock.releaseReadLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        r1.setName("reader 1");

        Thread r2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireReadLock();
                    System.out.println("--read 2 reading");
                    Thread.sleep(200);
                    lock.releaseReadLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        r2.setName("reader 2");

        Thread w1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireWriteLock();
                    System.out.println("--write 1 writing");
                    Thread.sleep(10);
                    lock.releaseWriteLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        w1.setName("writer 1");

        Thread w2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.acquireWriteLock();
                    System.out.println("--write 2 writing");
                    Thread.sleep(10);
                    lock.releaseWriteLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        w2.setName("writer 2");

        w1.start();
        w2.start();
        r1.start();
        r2.start();


    }
}
