package org.sheehan.algorithm.data_structures;

/**
 * Created by bsheehan on 2/22/16.
 *
 * uses chaining as opposed to open addressing with probing linear, quadratic,...)
 */
public class HashMapImpl<K,V> {

    // node that can also do chaining
    class Node<K,V> {
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    int size = 16;

    // index into table using hash function
    Node<K,V> []table = (Node<K,V>[]) new Node[size];

    public void put(K key, V value) {
        int hash = hashify(key);
        Node<K,V> node = table[hash];

        // brand new entry in table
        if (node == null) {
            table[hash] = new Node<>(key, value);
            return;
        }

        // --- COLLISION beyond this point ---

        // update or extend chain for existing node
        Node<K,V> curr = node;
        Node<K,V> prev = node;
        while(curr != null){
            if (curr.key.equals(key)) {
                curr.value = value; // update
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        // got to end of chain new entry
        Node<K,V> newNode = new Node<>(key,value);
        prev.next = newNode;

    }

    public V get(K key) {
        int hash = hashify(key);
        Node<K,V> node = table[hash];

        // explore possible chain
        Node<K,V> curr = node;
        while(curr != null){
            if (node.key.equals(key))
                return node.value;
            curr = curr.next;
        }
        return null;
    }

    public void remove(K key) {
        int hash = hashify(key);
        Node<K,V> node = table[hash];

        Node<K,V> prev = null;
        Node<K,V> curr = node;
        while(curr != null){
            if (curr.key.equals(key))
                break;
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {//not found
            System.out.println("not found");
            return;
        }

        //todo verify if single entry that table[hash] is set to null;
        // handle cases to delete from table
        if (prev == null){ //front
            table[hash]=node.next;
        } else if (curr.next != null){ //middle
            prev.next = curr.next;
        }
        else //end
            prev.next = null;
    }

    private int hashify(K key) {
        // keeps hash positive for table array index.
        return (key.hashCode() & 0x7fffffff)%size;
    }

    public void print() {
        for (int i =0; i < size; ++i){
            System.out.print(i + " - ");
            Node<K,V> curr = table[i];
            if (curr != null) {
                while (curr != null) {
                    System.out.print(" (" +curr.key + " " + curr.value + ") ");
                    curr = curr.next;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
