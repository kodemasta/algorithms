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

        //check and evict
        if (cache.size() >= capacity){
            K lruKey = (K) lruFifo.poll(); // removes from fifo
            cache.remove(lruKey);
            log.info("evicted "+ lruKey);
        }

        cache.put(key, val);

        //update with new key
        if (lruFifo.contains(key))
            lruFifo.remove(key);
        lruFifo.add(key);

        log.info ("PUT " + lruFifo.toString());
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
