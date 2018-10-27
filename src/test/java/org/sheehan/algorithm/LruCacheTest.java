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

        // read entry to update ts for this key
        String key = Integer.toString(1000);
        cache.put(key, key);

        key = Integer.toString(1001);
        cache.put(key, key);

        key = Integer.toString(1000);
        cache.put(key, key);
        key = Integer.toString(1001);
        cache.put(key, key);
        key = Integer.toString(1002);
        cache.put(key, key);
        //cache.remove(key);

    }
}