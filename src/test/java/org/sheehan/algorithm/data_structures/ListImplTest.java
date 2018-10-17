package org.sheehan.algorithm.data_structures;

import org.junit.Assert;
import org.junit.Test;
import org.sheehan.algorithm.Array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListImplTest {

    @Test
    public void testAppendDelete() {
        List<Integer> list = new ListImpl<Integer>();

        list.appendFront(1);
        list.print();
        list.deleteFront();
        list.print();

        list.appendBack(1);
        list.print();
        list.deleteBack();
        list.print();

        list.appendFront(1);
        list.appendFront(2);
        list.print();
        list.deleteFront();
        list.print();
        list.deleteFront();
        list.print();

        list.appendBack(1);
        list.appendBack(2);
        list.print();
        list.deleteBack();
        list.print();
        list.deleteBack();
        list.print();
    }

    @Test
    public void testInsert() {
        List<Integer> list = new ListImpl<Integer>();

        list.insertBefore(1,0);
        list.insertBefore(2,0);
        list.insertBefore(3,0);
        list.insertBefore(4,1);
        list.insertBefore(5,3);
        list.print();

        list.clear();
        list.print();

        list.deleteAt(3);
        list.print();
        list.deleteAt(1);
        list.print();
        list.deleteAt(0);
        list.print();
        list.deleteAt(1);
        list.print();
    }


    @Test
    public void testReverse() throws Exception {

        List<Integer> list = new ListImpl<Integer>();

        for (int i = 0; i < 10; i++)
            list.appendBack(i);
        list.print();

        list.reverse();
        list.print();
        list.reverse();
        list.print();

        list.reverseBrute();
        list.print();
        list.reverseBrute();
        list.print();

        list.reverseStack();
        list.print();
        list.reverseStack();
        list.print();

    }

    @Test
    public void testInsertInOrder() throws Exception {

        Integer array1[] = Array.createArray(20, 100, false);

        List<Integer> list = new ListImpl<Integer>();

        for (int arr_i: array1)
            list.insertInOrder(arr_i);


        list.print();

    }

    @Test
    public void testAppendFront() throws Exception {
        List<Integer> list = new ListImpl<Integer>();

        for (int i = 0; i < 10; i++)
            list.appendFront(i);
        Integer array1[] = new Integer[list.size()];
        list.toArray(array1);

        list.print();

        list.reverse();
        list.print();
        list.reverse();
        list.print();

        Integer array2[] = new Integer[list.size()];
        list.toArray(array2);

        Assert.assertArrayEquals(array1, array2);
    }


    @Test
    public void testDelete() throws Exception {
        List<Integer> list = new ListImpl<Integer>();

        for (int i = 0; i < 10; i++) {
            list.appendBack(i);
            list.appendBack(i);
        }

        list.print();

        list.deleteElements(5);
        list.print();
    }

    @Test
    public void testDeleteFrontBack() throws Exception {
        List<Integer> list = new ListImpl<Integer>();

        for (int i = 0; i < 10; i++)
            list.appendBack(i);

        list.print();
        ;
        List.Node<Integer> deleted = list.deleteFront();
        System.out.println(deleted.data);
        list.print();
        deleted = list.deleteBack();
        System.out.println(deleted.data);
        list.print();
    }


    @Test
    public void testCycle() {
        // make list from array
        List<Integer> list = new ListImpl<Integer>();
        Integer[] array = Array.createArray(10, 100, false);
        for(int arr_i:array)
            list.insertInOrder(arr_i);

        list.print();

        list.introduceCycleForTest();

//        Assert.assertNotNull(list.hasCycleSet());
//        beforeCycle = list.findBeforeCycle();
//        Assert.assertNotNull(beforeCycle);
//        //int cycleSize = list.countCycle(cycleStart);
//        System.out.println("Cycle Start: " + beforeCycle.next.data);

        List.Node cycleNode = list.findCycle();
        System.out.println("Cycle Start: " + cycleNode.toString());
    }

    @Test
    public void testOrderedSubList() {
        List<Integer> list1 = new ListImpl<Integer>();

        for (int i = 0; i < 10; i++)
            list1.appendBack(i);

        List<Integer> list2 = new ListImpl<Integer>();
        for (int i = 0; i < 2; i++)
            list2.appendBack(i);

        assertTrue(list1.orderedElementsFound(list2));

        List<Integer> list3 = new ListImpl<Integer>();
        for (int i = 0; i < 10; i++)
            list3.appendBack(i);

        assertTrue(list1.orderedElementsFound(list3));

        List<Integer> list4 = new ListImpl<Integer>();

        list4.appendBack(10);

        assertFalse(list1.orderedElementsFound(list4));

    }

    @Test
    public void testInsertionSortList() throws Exception {
        Integer array[] = Array.createArray(10,10, true);
        ListImpl<Integer> list = new ListImpl<>();
        list.appendBack(5);
        list.appendBack(10);
        list.appendBack(3);
        list.appendBack(7);
        list.appendBack(1);

        list.print();


        ListImpl.insertionSort(list);
        list.print();


        System.out.println();

    }

    @Test
    public void testSelectionSortList() throws Exception {

        ListImpl<Integer> list = new ListImpl<>();
        list.appendBack(5);
        list.appendBack(10);
        list.appendBack(3);
        list.appendBack(7);
        list.appendBack(1);

        list.print();

        ListImpl.selectionSort(list);
        list.print();

        System.out.println();
    }

    @Test
    public void testSwapPairs() throws Exception {

        ListImpl<Integer> list = new ListImpl<>();
        list.appendBack(5);
        list.appendBack(10);
        list.appendBack(3);
        list.appendBack(7);
        list.appendBack(1);

        list.print();

        ListImpl.swapPairs(list);
        list.print();

        System.out.println();
    }
}
