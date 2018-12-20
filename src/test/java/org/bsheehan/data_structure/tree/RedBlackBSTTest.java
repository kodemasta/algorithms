package org.bsheehan.data_structure.tree;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackBSTTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        Array.print(arr);
        RedBlackBST<Integer> rbTree = new RedBlackBST<>();

        rbTree.insertSortedArray(arr);
        rbTree.print();
        //Assert.assertTrue("BST Is Balanced", rbTree.isBalanced());

        rbTree.insert(11,11);
        rbTree.print();
       // Assert.assertTrue("BST Is Balanced", rbTree.isBalanced());

        rbTree.insert(12,12);
        rbTree.print();
        //Assert.assertTrue("BST Is Balanced", rbTree.isBalanced());

        rbTree.insert(13,13);
        rbTree.print();
        //Assert.assertTrue("BST Is Balanced", rbTree.isBalanced());
    }

    @Test
    public void test2() {
        super.test();
        int arr[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,2);
        Array.print(arr);
        RedBlackBST<Integer> rbTree = new RedBlackBST<>();

        rbTree.insertSortedArray(arr);
        rbTree.print();
        boolean balanced = rbTree.isBalanced();
        // Assert.assertTrue("BST Is Balanced", balanced);
        for (int i = 0; i < 10; i++) {
            rbTree.insert(i,i);
            rbTree.print();
        }

    }

}