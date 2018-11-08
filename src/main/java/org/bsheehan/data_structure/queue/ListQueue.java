package org.bsheehan.data_structure.queue;

import org.bsheehan.data_structure.linked_list.LinkedList;

public class ListQueue <V> {
    private LinkedList<V, V> fifo;

    public ListQueue(){
        this.fifo = new LinkedList<>();
    }

    public void enqueue(V value) {
        this.fifo.addBack(value, value);
    }

    public V dequeue() {
        return this.fifo.removeFront();
    }

    public void print() {
        fifo.print(true);
    }

    public int size() {
        return this.fifo.size();
    }
}
