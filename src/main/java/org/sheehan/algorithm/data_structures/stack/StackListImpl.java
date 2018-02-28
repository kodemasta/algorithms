package org.sheehan.algorithm.data_structures.stack;


import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;

/**
 * Created by bob on 5/25/14.
 */
public class StackListImpl<T extends Comparable<T>>  implements Stack<T> {

    List<T> list = new ListImpl<>();

    @Override
    public T pop() {
        List.Node<T> node = list.deleteBack();
        if (node != null)
            return node.data;
        return null;
    }

    @Override
    public T peek() {
        return list.get(list.size()-1);
    }

    @Override
    public void push(T value) {
        list.appendBack(value);

    }

    @Override
    public void print() {
        list.print();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public T[] toArray() {
        return null;
    }
}
