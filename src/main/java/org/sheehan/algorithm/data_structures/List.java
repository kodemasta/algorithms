package org.sheehan.algorithm.data_structures;

/**
 * Created by bob on 7/6/14.
 *
 * REVIEW: ArrayList or LinkedList: http://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist
 *
 */
public interface List<T extends Comparable<T>> extends Iterable<T> {


    class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T value) {
            this.data = value;
            this.next = null;
        }

        @Override
        public String toString(){
            return String.valueOf(data);
        }
    }

    void deleteAt(int pos);

    void insertBefore(T value, int pos);

    void insertAfter(T value, int pos);

    void appendBack(T value);

    //brute_n_3 force
    void reverseBrute();

    void reverse();

    void clear();

    Node deleteElements(int val);

    void reverseStack();

    T get(int index);

    void print();

    int size();

    void introduceCycleForTest();

    Node findCycle();

    T set(int j, T t);

    void toArray(T[] array);

    Node<T> deleteFront();

    Node<T> deleteBack();

    void appendFront(T t);

    boolean orderedElementsFound(List<T> subList);

    void insertInOrder(T data);
}
