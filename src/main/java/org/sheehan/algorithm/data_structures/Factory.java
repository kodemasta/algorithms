package org.sheehan.algorithm.data_structures;

public class Factory {

    public static <T extends Comparable<T>> List<T>  create(T []array) {
        List<T> list = new ListImpl<>();
        for (T elem:array) {
            list.appendFront(elem);
        }
        return list;
    }
}
