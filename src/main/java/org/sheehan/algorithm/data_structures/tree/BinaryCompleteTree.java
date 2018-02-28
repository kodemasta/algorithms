package org.sheehan.algorithm.data_structures.tree;

import java.util.Arrays;

/**
 * Created by bob on 7/9/14.
 *
 * A tree that is compactly formed with no holes from left to right.
 *
 *
 */
public class BinaryCompleteTree<T extends Comparable<T>> {

    T array[];
    int count;

    public BinaryCompleteTree(int size) {
        array = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, size);
    }

    public void add(T value){
        if (count == array.length)
            resize();
        array[count++] = value;
    }

    protected T[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }

    public boolean isEmpty(){
        return (count == 0) ? true : false;
    }

    public int parent(int i) {
        if (i == 0)
            return -1;
       return (i-1)/2;
    }

    public int leftChild(int i) {
        return 2*i + 1;
    }

    public int rightChild(int i) {
        return 2*i + 2;
    }

    public void print(int index) {
        if (index >= count)
            return;
        System.out.println(array[index]);
        print(leftChild(index));
        print(rightChild(index));
    }
}
