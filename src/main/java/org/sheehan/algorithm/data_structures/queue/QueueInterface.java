package org.sheehan.algorithm.data_structures.queue;

/**
 * Created by bob on 5/26/14.
 */
public interface QueueInterface<T extends Comparable<T>> extends Comparable<QueueInterface<T>> {
    void enqueue(T value);
    T dequeue();
    T peek();
    void print();
    void printArray();
    int size();
}
