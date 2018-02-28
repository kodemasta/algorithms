package org.sheehan.algorithm;

import junit.framework.TestCase;

import java.util.logging.Logger;

/**
 * Created by bsheehan on 1/25/16.
 */
public class LruCacheTest extends TestCase {

    Logger log = Logger.getLogger(this.getClass().getName());

    public void testEvict() throws Exception {

        LruCache<String,String> cache = new LruCache<>(10);

        //lOAD CACHE
        for (int i = 0; i < cache.capacity; ++i){
            String key = Integer.toString(i);
            cache.put(key, key);
            //log.info("TEST CACHE ADD " + key + " " + key);
            //Thread.sleep(100);
        }

        //log.info("TEST 1 --------------- READ key = 0 should be oldest");

        // read entry to update ts for this key
        String key = Integer.toString(0);
        String val = cache.get(key);
        log.info("TEST CACHE GET " + key + " " + val);

        //log.info("TEST 2 --------------- READ key = 1 --> should be oldest");
        // read uncached entry to see if LRU uses test1 lru index
        key = Integer.toString(1000);
        cache.put(key, key);
        log.info("TEST CACHE PUT " + key + " " + val);
        //log.info("TEST 3 --------------- READ key = 1000 --> should be null " + key + " " + val);
        key = Integer.toString(1001);
        cache.put(key, key);
        log.info("TEST CACHE PUT " + key + " " + val);
        key = Integer.toString(1000);
        cache.put(key, key);
        log.info("TEST CACHE PUT " + key + " " + val);

        val = cache.remove(key);
        log.info("TEST CACHE REMOVE " + key + " " + val);

    }
}