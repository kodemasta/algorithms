package org.sheehan.algorithm.data_structures.tree;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.array.Array;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BinarySearchTreeTest {


    @Test
    public void testInsertSortedList() throws Exception {
        Integer array[] = Array.create(Array.ArrayType.LINEAR_SORTED, 10,10);
        org.sheehan.algorithm.data_structures.ListImpl<Integer> list = new ListImpl<Integer>();
        for (int arr_i:array)
            list.appendBack(arr_i);

        BinarySearchTree<Integer,Integer> bst = new BinarySearchTree<>();
        bst.insertSortedList(list);

        System.out.println("TREE");
        bst.printLevels();
        System.out.println("PATHS");
        List<String> paths = new ArrayList<String>();
        bst.getPaths(bst.root, paths, "");
        for (String s : paths) {
            System.out.println(s);
        }

        System.out.println("PATH SUMS");
        List<Integer> pathSums = new ArrayList<Integer>();
        bst.getPathSums(bst.root, pathSums, 0);
        for (Integer sum : pathSums) {
            System.out.println(sum);
        }

    }

    @Test
    public void testPrintLevel() throws Exception {

        BinarySearchTree<Integer,Integer> bst = new BinarySearchTree<>();

        BinaryTree.TreeNode node6 = bst.insert(6,6);
        BinaryTree.TreeNode node4 = bst.insert(4,4);
        BinaryTree.TreeNode node8 = bst.insert(8,8);
        BinaryTree.TreeNode node2 = bst.insert(2,2);
        BinaryTree.TreeNode node10 = bst.insert(10,10);
        BinaryTree.TreeNode node5 = bst.insert(5,5);
        BinaryTree.TreeNode node9 = bst.insert(9,9);

        bst.printLevels();
//        int height = bst.getMaxDepth(bst.root);
//        boolean dir = true;
//        for (int i = 0; i < height; ++i){
//            bst.printLevel(bst.root, 0, i, dir);
//            System.out.println();
//            dir = !dir;
//        }

        List<String> paths = new ArrayList<String>();
        bst.getPaths(bst.root, paths, "");

        System.out.println("PATHS");
        for (String s : paths) {
            System.out.println(s);
        }
    }

    @Test
    public void testIsBst() throws Exception {

        BinarySearchTree<Integer,Integer> bst = new BinarySearchTree<>();

        BinaryTree.TreeNode node6 = bst.insert(6,6);
        BinaryTree.TreeNode node4 = bst.insert(4,4);
        BinaryTree.TreeNode node8 = bst.insert(8,8);
        BinaryTree.TreeNode node2 = bst.insert(2,2);
        BinaryTree.TreeNode node10 = bst.insert(10,10);
        BinaryTree.TreeNode node5 = bst.insert(5,5);
        BinaryTree.TreeNode node9 = bst.insert(9,9);

        bst.print(bst.root);

        assertTrue(BinarySearchTree.isBst(bst.root));

        System.out.println("tree height: " + bst.getMaxDepth(bst.root));
        bst.printInOrder();

        printStuff(bst, node6);
        printStuff(bst, node4);
        printStuff(bst, node8);
        printStuff(bst, node2);
        printStuff(bst, node10);
        printStuff(bst, node5);
        printStuff(bst, node9);

        List<String> paths = new ArrayList<String>();
        bst.getPaths(bst.root, paths, "");

        System.out.println("PATHS");
        for (String s : paths) {
            System.out.println(s);
        }

        bst.mirror(bst.root);
        bst.printInOrder();
    }

    private void printStuff(BinarySearchTree<Integer,Integer> bst, BinaryTree.TreeNode node) {
        int height = bst.getMaxDepth(bst.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst.root, 0, i);
            System.out.println();
        }

        if (bst.successorWithParent(node) != null) {
            System.out.println("successorWithParent " + node.toString() + " is " + bst.successorWithParent(node).toString());
            System.out.println("successorWithoutParent " + node.toString() + " is " + bst.successorWithoutParent(bst.root,node).toString());
        } else {
            System.out.println("successorWithParent " + node.toString() + " is null");
        }

        if (bst.predecessorWithParent(node) != null) {
            System.out.println("predecessorWithParent " + node.toString() + " is " + bst.predecessorWithParent(node).toString());
            System.out.println("predecessorWithoutParent " + node.toString() + " is " + bst.predecessorWithoutParent(bst.root,node).toString());

        } else {
            System.out.println("predecessorWithParent " + node.toString() + " is null");
        }

        if (bst.minimum(node) != null) {
            System.out.println("min " + node.toString() + " is " + bst.minimum(node).toString());

        } else {
            System.out.println("min " + node.toString() + " is null");
        }

        if (bst.maximum(node) != null) {
            System.out.println("max " + node.toString() + " is " + bst.maximum(node).toString());
        } else {
            System.out.println("maximum " + node.toString() + " is null");
        }

        System.out.println();
    }


    @Test
    public void testInsertDelete() throws Exception {

        BinarySearchTree<Integer,Integer> bst = new BinarySearchTree<>();

        bst.insert(100,100);
        bst.insert(90,90);
        bst.insert(110,110);
        bst.insert(80,80);
        bst.insert(95,95);
        bst.insert(92,92);
        bst.insert(93,93);
        bst.insert(91,91);

        int height = bst.getMaxDepth(bst.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst.root, 0, i);
            System.out.println();
        }
        System.out.println();
        System.out.println();

        BinaryTree.TreeNode<Integer,Integer> node = bst.getBstNode(bst.root,90);
        bst.delete(node);
        height = bst.getMaxDepth(bst.root);
        for (int i = 0; i < height; ++i) {
            bst.printLevelSimple(bst.root, 0, i);
            System.out.println();
        }
    }
}