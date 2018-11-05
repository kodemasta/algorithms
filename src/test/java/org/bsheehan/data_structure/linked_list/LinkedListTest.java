package org.bsheehan.data_structure.linked_list;

import org.bsheehan.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest extends BaseTest {

    @Test
    public void testAddRemove() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addFront(1,"B");
        list.addFront(2,"C");
        list.addFront(3,"D");
        list.print(true);

        System.out.println("find 2:" + list.find(2));

        System.out.println("remove back:" + list.removeBack());
        list.print(true);

        System.out.println("remove front:" + list.removeFront());
        list.print(true);
        list.addBack(4,"E");
        list.print(true);
        list.print(false);
        System.out.println("remove front:" + list.removeFront());
        System.out.println("remove front:" + list.removeFront());
        System.out.println("remove front:" + list.removeFront());
        list.print(true);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testClear() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addFront(1,"B");
        list.addFront(2,"C");
        list.addFront(3,"D");
        list.print(true);
        list.clear();
        list.print(true);
        list.print(false);
    }

    @Test
    public void testSwapNotAdjacent() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addBack(1,"B");
        list.addBack(2,"C");
        list.addBack(3,"D");
        list.addBack(4,"E");
        list.print(true);
        list.swap(1, 3);
        list.print(true);
        list.swap(1, 3);
        list.print(true);

        list.swap(0, 4);
        list.print(true);
        list.swap(0, 4);
        list.print(true);
    }

    @Test
    public void testAddAfter() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addBack(1, "B");
        list.addBack(2, "C");
        list.addBack(3, "D");
        list.addBack(4, "E");
        list.print(true);
        list.addAfter(1, 5,"F");
        list.print(true);
        list.addAfter(4, 6,"G");
        list.print(true);
    }

    @Test
    public void testRemove() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addBack(1, "B");
        list.addBack(2, "C");
        list.addBack(3, "D");
        list.addBack(4, "E");
        list.print(true);
        list.remove(1);
        list.print(true);
        list.remove(0);
        list.print(true);
        list.remove(4);
        list.print(true);
    }

    @Test
    public void testAddBefore() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addBack(1, "B");
        list.addBack(2, "C");
        list.addBack(3, "D");
        list.addBack(4, "E");
        list.print(true);
        list.addBefore(1, 5,"F");
        list.print(true);list.addBefore(0, 6,"G");
        list.print(true);
    }

    @Test
    public void testSwapAdjacent() {
        super.test();
        LinkedList<Integer, String> list = new LinkedList<>(0, "A");
        list.addBack(1,"B");
        list.addBack(2,"C");
        list.addBack(3,"D");
        list.addBack(4,"E");
        list.print(true);
        list.swap(1, 2);
        list.print(true);
        list.swap(1, 2);
        list.print(true);

        list.swap(0, 1);
        list.print(true);
        list.swap(0, 1);
        list.print(true);

        list.swap(3, 4);
        list.print(true);
        list.swap(3, 4);
        list.print(true);
    }
}