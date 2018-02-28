package org.sheehan.algorithm.data_structures.queue;

import java.lang.reflect.Array;

/**
 * Created by bob on 5/26/14.
 */
public class QueueArrayImpl<T extends Comparable<T>> implements QueueInterface<T> {

    private int firstIndex = 0;
    private int count = 0;
    private int size;
    private T array[];

    public QueueArrayImpl(int size) {
        this.size = size;
        array = (T[]) Array.newInstance(Comparable.class, size);
    }

    // enqueue to back
    @Override
    public void enqueue(T value) {
        if (count == size)
            throw new RuntimeException("Full QueueInterface");
        array[(firstIndex + count)%size] = value;
        count++;
        //TODO add resize for loadfactor
    }

    // dequeue from front
    @Override
    public T dequeue() {
        if (count == 0)
            return null;
        T value = array[(firstIndex)%size];

        firstIndex++;
        count--;
        firstIndex %=size;

        //TODO resize for smaller queue memory
        return value;
    }

    @Override
    public T peek() {
        if (count == 0)
            return null;
        return array[firstIndex];
    }

    @Override
    public void print() {
        for (int i = firstIndex; i < firstIndex + count; ++i){
            int index = i%size;
            System.out.print(array[index] + " ");
        }
        System.out.println();
    }

    public void printArray() {
        System.out.print("raw array ");
        for (int i = 0; i < size; ++i){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int compareTo(QueueInterface<T> o) {
        //TODO if needed
        return 0;
    }
}
