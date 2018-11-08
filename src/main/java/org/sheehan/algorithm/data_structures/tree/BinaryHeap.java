package org.sheehan.algorithm.data_structures.tree;

/**
 * Created by bob on 7/13/14.
 *
 * A Heap is a specific data structure or implementation of an efficient priority stack, which is simply a type that allows
 * retrieval of elements by priority (min or max).
 *
 * This allows for specification of min or max. As a complete tree we use a fixed size array for heap implementation.
 *
 * implements a dequeue method which provides priority stack functionality
 */
public class BinaryHeap <T extends Comparable<T>> extends BinaryCompleteTree <T> {

    public enum HeapType {MIN_HEAP, MAX_HEAP};

    final private HeapType heapType;

    public BinaryHeap(int size, HeapType heapType){
        super(size);
        this.heapType = heapType;
    }

    // start with last node and (bubbleUp) swap up recursively. Then iterate reverse back upt he tree.
    public void heapify(T array[])
    {
        this.array = array;
        count = array.length;
        for( int i = this.count-1 ; i >= 0; i-- )
            bubbleUp(i);
    }

    // recursive from node index i up to root.
    protected void bubbleUp(int i)
    {
        int parent = parent(i);

        boolean swap = false;

        if( parent >= 0) {
            if (this.heapType==HeapType.MAX_HEAP && this.array[parent].compareTo(this.array[i]) < 0)
                swap = true;
            else if (this.heapType==HeapType.MIN_HEAP && this.array[parent].compareTo(this.array[i]) > 0)
                swap = true;
        }

        if( swap)
        {
            swap(parent, i);
            bubbleUp(parent); //recurse upward toward root
        }
    }

    //iterative from node index i up to root.
    //bubble up
    protected void bubbleUpIterative(int i)
    {
        int parentIndex = parent(i);

        boolean swap = true;

        while(parentIndex >= 0 && swap) {
            swap = false;
            if (this.heapType==HeapType.MAX_HEAP && this.array[parentIndex].compareTo(this.array[i]) < 0)
                swap = true;
            else if (this.heapType==HeapType.MIN_HEAP && this.array[parentIndex].compareTo(this.array[i]) > 0)
                swap = true;

            if (swap) {
                swap(parentIndex, i);
                i = parentIndex;
                parentIndex = parent(parentIndex);
             }
        }
    }

    // for after deleting and swapping top node - establish heap property
    protected void bubbleDown(int i)
    {
        int leftChildIndex = leftChild(i);
        int rightChildIndex = rightChild(i);

        int swap = i;
        if (leftChildIndex < count) {
            if (this.heapType==HeapType.MAX_HEAP && this.array[leftChildIndex].compareTo(this.array[i]) > 0)
                swap = leftChildIndex;
            else if (this.heapType==HeapType.MIN_HEAP && this.array[leftChildIndex].compareTo(this.array[i]) < 0)
                swap = leftChildIndex;

            if (this.heapType==HeapType.MAX_HEAP && rightChildIndex < count &&
                    this.array[rightChildIndex].compareTo(this.array[i]) > 0 &&
                    this.array[rightChildIndex].compareTo(this.array[leftChildIndex]) > 0)
                swap = rightChildIndex;
            else if (this.heapType==HeapType.MIN_HEAP && rightChildIndex < count &&
                    this.array[rightChildIndex].compareTo(this.array[i]) < 0 &&
                    this.array[rightChildIndex].compareTo(this.array[leftChildIndex]) < 0)
                swap = rightChildIndex;

            if (swap != i) {
                swap(swap,i);
                bubbleDown(swap);
            }
        }
    }

    private void swap(int index1, int index2) {
        T tmp = this.array[index1];
        this.array[index1] = this.array[ index2 ];
        this.array[ index2 ] = tmp;
    }

    @Override
    // add to end then bubble up to where it should live to satisfy heap property
    public void add(T value) {
        super.add(value);
        bubbleUp(this.count - 1);
    }

    // pop then swap with last and bubble down from top to satisfy heap property
    public T pop() {
        if (count == 0)
            return null;
        T value = this.array[0];
        swap(0, count-1);
        --count;
        bubbleDown(0);
        return value;
    }

    public T peek() {
        if (count == 0)
            return null;
        return this.array[0];
    }

    public int size() {
        return count;
    }

}
