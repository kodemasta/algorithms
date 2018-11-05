package org.bsheehan.data_structure.hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.hash;

//associative array, random access of key into array O(1) after hash calc.
public class HashMap <K,V> {

    private List<HashChain<K,V>> table;
    private int capacity;
    private int size;

    ///////////////////////////////////////////////////////////////////////////////////
    // key-value node
    class HashNode<K,V> {
        K key;
        V value;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // supports chaining for collision resolution
    class HashChain<K,V> {
        private List<HashNode<K,V>> chain;

        public HashChain(K key, V value) {
            chain = new ArrayList<>();
            chain.add(new HashNode<>(key,value));
        }

        public void clear() {
            chain.clear();
        }

        public boolean update(K key, V value){
            Iterator<HashNode<K, V>> iterator = chain.iterator();
            while(iterator.hasNext()){
                HashNode<K, V> node = iterator.next();
                if (node.key.equals(key)) {
                    node.value = value;
                    return true;
                }
            }

            return false;
        }

        public boolean put(K key, V value) {
            if (!update(key,value)) {
                chain.add(new HashNode<>(key, value));
                return true;
            }
            return false;
        }

        public V get(K key) {
            Iterator<HashNode<K, V>> iterator = this.chain.iterator();
            while(iterator.hasNext()){
                HashNode<K, V> node = iterator.next();
                if (node.key.equals(key))
                    return node.value;
            }

            return null;
        }

        public V remove(K key) {
            Iterator<HashNode<K, V>> iterator = this.chain.iterator();
            while(iterator.hasNext()){
                HashNode<K, V> node = iterator.next();
                if (node.key.equals(key)) {
                    iterator.remove();
                    return node.value;
                }
            }

            return null;

        }

        public void print(){
            Iterator<HashNode<K, V>> iterator = this.chain.iterator();
            while(iterator.hasNext()) {
                HashNode<K, V> node = iterator.next();
                System.out.print(" (" +node.key + " " + node.value + ") ");
            }

            System.out.println();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public HashMap (int capacity) {
        this.table = new ArrayList<HashChain<K,V>>(capacity);
        // Create empty chains
        for (int i = 0; i < capacity; i++)
            table.add(null);
        this.capacity = capacity;
        this.size = 0;
    }

    public V get(K key){
        int index = getIndex(key);
        if (this.table.get(index) == null) {
            return null;
        }
        HashChain<K,V> list =  this.table.get(index);
        if (list != null) {
            return list.get(key);
        }
        return null;
    }

    public void put(K key, V value){

        int index = getIndex(key);
        HashChain<K,V> list = this.table.get(index);
        // no list at table index, add one
        if (list == null) {
            list = new HashChain<K,V>(key,value);
            this.table.set(index, list);
            this.size++;

        }
        else {
            // find list and update if key found in list
            if (list.put(key, value))
                this.size++;
        }
    }

    private int getIndex(K key) {
        return Math.abs(hash(key))% this.capacity;
    }

    public int size(){
        return this.size;
    }

    public void clear() {
        for (int i =0; i < table.size(); ++i){
            if (this.table.get(i) != null)
                this.table.get(i).clear();
        }
        this.table.clear();
        this.size = 0;
    }

    public V remove(K key){
        int index = getIndex(key);
        if (this.table.get(index) == null) {
            return null;
        }
        V removedVal = this.table.get(index).remove(key);
        if (removedVal != null)
            this.size--;

        return removedVal;
    }

    public boolean contains(K key){
        int index = getIndex(key);
        if (this.table.get(index) == null) {
            return false;
        }
        if (this.table.get(index).get(key) != null)
            return true;

        return false;
    }


    public void print() {
        for (int i =0; i < this.table.size(); ++i) {
            System.out.print(i + " - ");
            if (this.table.get(i) != null)
                this.table.get(i).print();
            else
                System.out.println();

        }
        if (this.table.size() == 0){
            System.out.println("Empty");
        }
        System.out.println("SIZE: " + this.size);
        System.out.println();
    }
}
