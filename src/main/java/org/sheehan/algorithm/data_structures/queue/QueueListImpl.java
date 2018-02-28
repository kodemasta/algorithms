package org.sheehan.algorithm.data_structures.queue;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;

/**
 * Created by bob on 5/26/14.
 *
 * Uses List
 */
public class QueueListImpl<T extends Comparable<T>> implements QueueInterface<T> {

    List<T> list = new ListImpl<>();

    @Override
    public void enqueue(T value) {
        list.appendBack(value);
    }

    @Override
    public T dequeue() {
        List.Node<T> node = list.deleteFront();
        if (node != null)
            return node.data;
        return null;    }

    @Override
    public T peek() {
        return list.get(list.size()-1);
    }

    @Override
    public void print() {
        list.print();
    }

    @Override
    public void printArray() {

    }

    @Override
    public int compareTo(QueueInterface<T> o) {
        return 0;
    }

    @Override
    public int size() {
        return list.size();
    }
}
