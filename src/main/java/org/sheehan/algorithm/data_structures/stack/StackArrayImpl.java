package org.sheehan.algorithm.data_structures.stack;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;


/**
 * Created by bob on 5/25/14.
 */
public class StackArrayImpl<T extends Comparable<T>>  implements Stack<T> {
    private int top;
    private int size;
    private T array[];
    private String name;

    public StackArrayImpl(int size) {
        top = -1;
        this.size = size;
        array = (T[])Array.newInstance(Comparable.class, this.size);
    }

    public StackArrayImpl(int size, String name) {
        this(size);
        name = name;
    }

    @Override
    public void push (T value) {
        if (top == this.size)
            throw new NoSuchElementException();
        array[++top] = value;
    }

    @Override
    public T pop () {
        if (top == -1)
            throw new NoSuchElementException();
        T value =  array[top--];

        return value;
    }

    @Override
    public T peek() {
        if (top == -1)
            return null;
        return array[top];
    }

    @Override
    public void print() {
        if (name != null)
            System.out.print(name + ":");

        for (int i = 0; i <= top; ++i){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public T[] toArray() {
        //TODO
        return array;
    }
}
