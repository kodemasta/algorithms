package org.sheehan.algorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * Created by bsheehan on 1/25/16.
 *
 * Assumptions
 *
 * THREAD SAFE ?
 *
 * Look at LinkedHashMap which allows override of removeEldestEntry method for stale cache updates.
 * This might suffice instead of this homegrown approach.
 */
public class LruCache<K,V> {

    private Map<K, V> cache; //ConcurrentHashMap

    private java.util.Queue lruFifo = new ConcurrentLinkedQueue();

    Logger log = Logger.getLogger(this.getClass().getName());

    public final int capacity;

    public LruCache(int capacity){
        cache = new ConcurrentHashMap<>(capacity, 1.0f);
        this.capacity = capacity;
    }

    // either update or add to cache. handle lru update if at capacity
    public void put(K key, V val) {

        // refresh or evict from fifo and cache
        if (cache.size() >= capacity){
            // if key then item is already in cache.. just refresh
            if (lruFifo.contains(key)) {
                lruFifo.remove(key);
                log.info("REFRESHED LRU KEY "+ key); //add back in later

            } else {
                // remove from fifo cache
                K lruKey = (K) lruFifo.poll(); // get oldest key from fifo and remove from fifo
                cache.remove(lruKey); //remove from cache
                log.info("EVICTED LRU KEY "+ lruKey);
            }
        }

        // update fifo
        lruFifo.add(key);

        // update cache
        cache.put(key, val);

        log.info("PUT fifo" + lruFifo.toString());
        log.info("PUT cache:" + cache.toString());
    }

    public V get(K key) {
        V value = cache.get(key);

        // update fifo with remove/add
        if (lruFifo.contains(key)) {
            lruFifo.remove(key);
            lruFifo.add(key);
        }
        log.info ("GET " + lruFifo.toString());

        return value;
    }

    public V remove(K key){
        V val = null;
        if (cache.containsKey(key)) {
            val = cache.remove(key);
        }
        if (lruFifo.contains(key))
            lruFifo.remove(key);

        log.info ("REMOVE " + lruFifo.toString());

        return val;
    }
}
