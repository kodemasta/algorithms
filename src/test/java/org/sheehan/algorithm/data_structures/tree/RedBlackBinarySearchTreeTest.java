package org.sheehan.algorithm.data_structures.tree;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by bsheehan on 2/18/16.
 */
public class RedBlackBinarySearchTreeTest extends TestCase {

    @Test
    public void testBalanceInsert() throws Exception {

        RedBlackBinarySearchTree<Integer, Integer> bst = new RedBlackBinarySearchTree<>();

        bst.insert(1, 1);
        bst.insert(2, 2);
        bst.insert(3, 3);
        bst.insert(4, 4);
        bst.insert(5, 5);
        bst.insert(6, 6);
        bst.insert(7, 7);
        bst.insert(8, 8);
        System.out.println(); System.out.println();
        int height = bst.getMaxDepth(bst.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst.root, 0, i);
            System.out.println();
        }

        bst.traverseBfs(x->System.out.println(x+" "));

        bst = new RedBlackBinarySearchTree<>();

        bst.insert(7, 7);
        bst.insert(6, 6);
        bst.insert(5, 5);
        bst.insert(4, 4);
        bst.insert(3, 3);
        bst.insert(2, 2);
        bst.insert(1, 1);
        System.out.println(); System.out.println();
        height = bst.getMaxDepth(bst.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst.root, 0, i);
            System.out.println();
        }

        RedBlackBinarySearchTree<Integer, Integer> bst2 = new RedBlackBinarySearchTree<>();
        bst2.insert(4, 4);
        bst2.insert(2, 2);
        bst2.insert(6, 6);
        bst2.insert(1, 1);
        bst2.insert(3, 3);
        bst2.insert(5, 5);
        bst2.insert(7, 7);
        System.out.println(); System.out.println();
        height = bst2.getMaxDepth(bst2.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst2.root, 0, i);
            System.out.println();
        }
    }
}