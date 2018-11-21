package org.bsheehan.data_structure.tree;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class BSTTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        BST<Integer> bst = new BST<>();

        bst.insertSortedArray(arr);
        bst.print();
        boolean balanced = bst.isBalanced();
        Assert.assertTrue("BST Is Balanced", balanced);

    }

    @Test
    public void testLevelOrder() {
        super.test();

        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        BST<Integer> bst = new BST<>();

        bst.insertSortedArray(arr);
        bst.print();


        System.out.println();
        bst.visitLevelOrder(s-> System.out.print(s + " "));
        System.out.println();
        bst.visitLevelZigZagOrder(s-> System.out.print(s + " "));
    }

}