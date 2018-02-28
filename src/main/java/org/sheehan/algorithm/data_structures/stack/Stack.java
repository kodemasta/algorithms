package org.sheehan.algorithm.data_structures.stack;

/**
 * Created by bob on 5/26/14.
 */
public interface Stack <T extends Comparable<T>>{
    T pop();

    T peek();

    void push(T value);

    void print();

    String getName();

    T[] toArray();
}
