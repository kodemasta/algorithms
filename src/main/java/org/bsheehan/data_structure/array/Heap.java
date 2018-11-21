package org.bsheehan.data_structure.array;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

public class Heap<V extends Comparable> {
    static int idCnt = 0;

    ///////////////////////////////////////////////
    public static class Node<V extends Comparable> implements Comparable<Node> {
        Integer id;
        V value;

        @Override
        public int compareTo(Node o) {
            return this.value.compareTo(o.value);
        }

        public Node(V value) {
            this.id = idCnt++;
            this.value = value;
        }
    }

    ///////////////////////////////////////////////
    public enum HeapType {
        MIN_HEAP, MAX_HEAP
    }

    final private HeapType heapType;

    List<Node<V>> array;

    public static <V extends Comparable<V>> Heap<V> createHeap(V array[], HeapType type) {
        return new Heap<V>(type, array);
    }

    private Heap(HeapType heapType, V arr[]) {
        this.heapType = heapType;

        this.array = new ArrayList<Node<V>>(arr.length);

        for (int i = 0; i < arr.length; ++i) {
            add(arr[i]);
        }
    }

    public void add(V value){
        this.array.add(new Node<V>(value));
        if (heapType==HeapType.MAX_HEAP)
            bubbleUp(this.array.size()-1, Heap::maxHeapCompare);
        else
            bubbleUp(this.array.size()-1, Heap::minHeapCompare);
    }

    // pop then swap with last and bubble down from top to satisfy heap property
    public V remove() {
        if (this.array.size() == 0)
            return null;
        V value = this.array.get(0).value;
        swap(0, this.array.size()-1);

        this.array.remove(this.array.size()-1);
        if (this.array.size() > 0 && this.heapType == HeapType.MAX_HEAP)
            bubbleDown(0, Heap::maxHeapCompare);
        else if (this.array.size() > 0 && this.heapType == HeapType.MIN_HEAP)
            bubbleDown(0, Heap::minHeapCompare);

        return value;
    }

    public V peek() {
        if (this.array.size() == 0)
            return null;
        return this.array.get(0).value;
    }

    public int parent(int i) {
        if (i == 0)
            return -1;
        return ((i - 1) / 2);
    }

    public int leftChild(int i) {
        return (2 * i + 1);
    }

    public int rightChild(int i) {
        return (2 * i + 2);
    }

    //recursive from node index i up to root.
    //bubble up
    protected void bubbleUp(int i, BiPredicate<Node<V>, Node<V>> cmp) {
        int parentIndex = parent(i);
        if (parentIndex == -1)
            return;
        Node<V> parent = this.array.get(parentIndex);
        Node<V> child = this.array.get(i);

        boolean swap = false;

        if (parent != null) {
            if (cmp.test(parent,child))
                swap = true;
        }

        if (swap) {
            swap(parentIndex, i);
            bubbleUp(parentIndex, cmp); //recurse upward toward root
        }
    }

    //recursive from node index i
    protected void bubbleDown(int i, BiPredicate<Node<V>, Node<V>> cmp) {
        int leftChildIndex = this.leftChild(i);
        int rightChildIndex = this.rightChild(i);
        Node<V> leftChild = null;
        if (leftChildIndex < this.array.size())
            leftChild = this.array.get(leftChildIndex);
        Node<V> rightChild = null;
        if (rightChildIndex < this.array.size())
            rightChild = this.array.get(rightChildIndex);
        Node<V> node = this.array.get(i);

        if (leftChild != null && rightChild == null) {
            if (cmp.test(node, leftChild)) {
                swap(leftChildIndex, i);
                bubbleDown(leftChildIndex, cmp);
            }
        } else if (rightChild != null && leftChild == null) {
            if (cmp.test(node, rightChild)) {
                swap(rightChildIndex, i);
                bubbleDown(rightChildIndex, cmp);
            }
        } else if (rightChild != null && leftChild != null) { //swap with larger of children
            if (rightChild.value.compareTo(leftChild.value) > 0){
                if (cmp.test(node, rightChild)) {
                    swap(rightChildIndex, i);
                    bubbleDown(rightChildIndex, cmp);
                }
            } else if (node.compareTo(leftChild) < 0) {
                swap(leftChildIndex, i);
                bubbleDown(leftChildIndex, cmp);
            }
        }
    }

    private void swap(int index1, int index2) {
        Node<V> tmp = this.array.get(index1);
        this.array.set(index1, this.array.get(index2));
        this.array.set(index2, tmp);
        //System.out.println("swapped:" + this.array.get(index1).value + " " + this.array.get(index2).value);
    }

    public void print() {
        displayHeap();
    }

    public boolean isHeap() {
        if (this.array.size() == 1 || this.array.size() == 0)
            return true;
        for (int j = 0; j < this.array.size(); ++j) {
            Node<V> parent = this.array.get(j);
            Node<V> leftChild = null;
            Node<V> rightChild = null;
            if (this.leftChild(j) < this.array.size())
                leftChild = this.array.get(this.leftChild(j));

            if (this.rightChild(j) < this.array.size())
                rightChild = this.array.get(this.rightChild(j));


            BiPredicate<V, V> cmp;
            if (heapType == HeapType.MAX_HEAP)
                cmp = (V v1,V v2) -> v1.compareTo(v2) >= 0;
            else
                cmp = (V v1,V v2) -> v1.compareTo(v2) <= 0;
            boolean isHeap = false;
            isHeap = (leftChild != null) && cmp.test(parent.value, leftChild.value);
            isHeap |= (rightChild != null) &&  cmp.test(parent.value, rightChild.value);

            return isHeap;
        }
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");    // array format
        for (int m = 0; m < this.array.size(); m++)
            if (array.get(m) != null)
                System.out.print(array.get(m).value + " ");
            else
                System.out.print("-- ");
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while (this.array.size() > 0)              // for each heap item
        {
            if (column == 0)                  // first item in row?
                for (int k = 0; k < nBlanks; k++)  // preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(this.array.get(j).value);

            if (++j == this.array.size())           // done?
                break;

            if (++column == itemsPerRow)        // end of row?
            {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            } else                             // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');     // interim blanks
        }  // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }  // end displayHeap()

    public static <V extends Comparable<Node<V>>> boolean maxHeapCompare(Node<V> n1, Node<V> n2){
        return n1.compareTo(n2) < 0;
    }

    public static  <V extends Comparable<Node<V>>> boolean minHeapCompare(Node<V> n1, Node<V> n2){
        return n1.compareTo(n2) > 0;
    }
}
