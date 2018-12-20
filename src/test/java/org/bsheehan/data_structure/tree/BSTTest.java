package org.bsheehan.data_structure.tree;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void testPathOrder() {
        super.test();

        int arr[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10,10);
        Array.print(arr);
        BST<Integer> bst = new BST<>();

        bst.insertSortedArray(arr);
        bst.print();


        System.out.println();
        List<List<Tree<Integer>.Node<Integer>>> paths = bst.visitPaths();
        System.out.println();
        for (List<Tree<Integer>.Node<Integer>> p: paths) {
            for (Tree<Integer>.Node<Integer> n: p)
                System.out.print(n.id+ " ");
            System.out.println();
        }
    }

}