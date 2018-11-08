package org.bsheehan.data_structure.stack;

import org.bsheehan.data_structure.linked_list.LinkedList;

public class ListStack<V> {

    private LinkedList<V, V> lifo;

    public ListStack(){
        this.lifo = new LinkedList<>();
    }

    public void push(V value){
        this.lifo.addFront(value, value);
    }

    public V pop() {
        return this.lifo.removeFront();
    }

    public void print() {
        this.lifo.print(true);
    }

    public int size() {
        return this.lifo.size();
    }
}
