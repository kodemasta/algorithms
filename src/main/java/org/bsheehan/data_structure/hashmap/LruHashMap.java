package org.bsheehan.data_structure.hashmap;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LruHashMap<K,V> {
    private HashMap<K,V> hashMap;
    private java.util.Queue lruFifo;
    private int capacity;

    public LruHashMap(int capacity) {
        this.hashMap = new HashMap<K,V>(capacity);
        this.lruFifo = new ConcurrentLinkedQueue();
        this.capacity = capacity;
    }

    // either update or add to cache. handle lru update if at capacity
    public void put(K key, V val) {

        // hashMap or evict from fifo and cache
        if (hashMap.size() >= capacity){
            // if key then item is already in cache.. just refresh
            if (lruFifo.contains(key)) {
                lruFifo.remove(key); // will add later as most recent used
                System.out.println("REFRESHED LRU KEY "+ key); //add back in later

            } else {
                // remove from fifo cache
                K lruKey = (K) lruFifo.poll(); // get oldest key (LRU) from fifo AND removes from fifo
                hashMap.remove(lruKey); //remove from cache
                System.out.println("EVICTED LRU KEY "+ lruKey);
            }
        }

        // update fifo
        lruFifo.add(key); // most recently used

        // update cache
        hashMap.put(key, val);
    }

    public V get(K key) {
        V value = this.hashMap.get(key);

        // update fifo with remove/add
        if (lruFifo.contains(key)) {
            lruFifo.remove(key);
            lruFifo.add(key);
        }
        //System.out.println("GET " + lruFifo.toString());

        return value;
    }

    public V remove(K key){
        V val = null;
        if (hashMap.contains(key)) {
            val = hashMap.remove(key);
        }
        if (lruFifo.contains(key))
            lruFifo.remove(key);

        //System.out.println("REMOVE " + lruFifo.toString());

        return val;
    }

    public void print() {
        System.out.println("fifo: " + lruFifo.toString());
        this.hashMap.print();
    }

    public void clear() {
        this.hashMap.clear();
        this.lruFifo.clear();
    }
}
