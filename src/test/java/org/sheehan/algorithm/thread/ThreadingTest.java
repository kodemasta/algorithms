package org.sheehan.algorithm.thread;

import junit.framework.TestCase;

    public class ThreadingTest extends TestCase {


        public void test1() throws Exception {
            ConsumerProducer1.main(null);
        }

        public void test2() throws Exception {
            ReadWriteLock.main(null);
        }
    }
