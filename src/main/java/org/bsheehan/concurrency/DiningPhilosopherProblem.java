package org.bsheehan.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopherProblem {

    /////////////////////////////////////////////////////////////////////////////////////
    public static class Philosopher implements Runnable {

        private ReentrantLock leftChopStick;
        private ReentrantLock rightChopStick;
        private int id;

        public AtomicBoolean isTummyFull=new AtomicBoolean(false);

        //To randomize eat/Think time
        private Random randomGenerator = new Random();

        private int noOfTurnsToEat=0;

        public int getId(){
            return this.id;
        }
        public int getNoOfTurnsToEat(){
            return noOfTurnsToEat;
        }

        /****
         *
         * @param id Philosopher number
         *
         * @param leftChopStick
         * @param rightChopStick
         */
        public Philosopher(int id, ReentrantLock leftChopStick, ReentrantLock rightChopStick) {
            this.id = id;
            this.leftChopStick = leftChopStick;
            this.rightChopStick = rightChopStick;
        }

        @Override
        public void run() {

            while ( !isTummyFull.get()) {
                try {
                    think();
                    if (pickupLeftChopStick() && pickupRightChopStick()) {
                        eat();
                    }
                    putDownChopSticks();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void think() throws InterruptedException {
            System.out.println(String.format("Philosopher %s is thinking", this.id));
            System.out.flush();
            Thread.sleep(randomGenerator.nextInt(1000));
        }

        private void eat() throws InterruptedException {
            System.out.println(String.format("Philosopher %s is eating", this.id));
            System.out.flush();
            noOfTurnsToEat++;
            Thread.sleep(randomGenerator.nextInt(1000));
        }

        private boolean pickupLeftChopStick() throws InterruptedException {
            if (leftChopStick.tryLock(10, TimeUnit.MILLISECONDS)) {
                System.out.println(String.format("Philosopher %s pickedup Left ChopStick", this.id));
                System.out.flush();
                return true;
            }
            return false;
        }

        private boolean pickupRightChopStick() throws InterruptedException {
            if (rightChopStick.tryLock(10, TimeUnit.MILLISECONDS)) {
                System.out.println(String.format(
                        "Philosopher %s pickedup Right ChopStick", this.id));
                System.out.flush();
                return true;
            }
            return false;
        }

        private void putDownChopSticks() {
            if (leftChopStick.isHeldByCurrentThread()) {
                leftChopStick.unlock();
                System.out.println(String.format("Philosopher %s putdown Left ChopStick", this.id));
                System.out.flush();
            }
            if (rightChopStick.isHeldByCurrentThread()) {
                rightChopStick.unlock();
                System.out.println(String.format("Philosopher %s putdown Right ChopStick", this.id));
                System.out.flush();
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    private static final int NO_OF_PHILOSOPHER = 5;
    private static final int SIMULATION_MILLIS = 10000;

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = null;

        Philosopher[] philosophers = null;
        try {

            philosophers = new Philosopher[NO_OF_PHILOSOPHER];

            //As many forks as Philosophers
            ReentrantLock[] forks = new ReentrantLock[NO_OF_PHILOSOPHER];
            Arrays.fill(forks, new ReentrantLock());

            executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);

            for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
                philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1)% NO_OF_PHILOSOPHER]);
                executorService.execute(philosophers[i]);
            }
            //Main thread sleeps till time of simulation
            Thread.sleep(SIMULATION_MILLIS);
            for (Philosopher philosopher : philosophers) {
                philosopher.isTummyFull.set(true);
            }
            //all philosophers are done eating...

        } finally {
            executorService.shutdown();

            // Wait period for all thread to finish
            Thread.sleep(1000);

            //Time for check
            for (Philosopher philosopher : philosophers) {
                System.out.println("Philosopher (" + philosopher.getId()
                        + ") => No of Turns to Eat = "
                        + philosopher.getNoOfTurnsToEat());
                System.out.flush();
            }
        }
    }
}