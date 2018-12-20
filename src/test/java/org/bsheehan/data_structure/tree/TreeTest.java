package org.bsheehan.data_structure.tree;

import org.bsheehan.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest extends BaseTest {

    @Test
    public void test() {
        super.test();

        Tree<Integer> tree = new Tree<>();

        while (tree.size(tree.root, 0) < 10) {
            int key = Double.valueOf(Math.floor(Math.random() * 100f)).intValue();
            tree.insert(key, key);
        }

        tree.print();
        tree.mirror();
        tree.print();

        tree.visitPreOrder(s -> System.out.println(s));
        System.out.println();
        tree.visitInOrder(s -> System.out.println(s));
        System.out.println();
        tree.visitPostOrder(s -> System.out.println(s));

        System.out.println("Diameter:" + tree.diameter());

        System.out.println("Leaves:" + tree.numLeaves());
    }


    @Test
    public void testPreOrder() {
        super.test();

        Tree<Integer> tree = new Tree<>();

        while (tree.size(tree.root, 0) < 5) {
            int key = Double.valueOf(Math.floor(Math.random() * 10f)).intValue();
            tree.insert(key, key);
        }

        tree.print();

        tree.visitPreOrder(s -> System.out.println(s));
        System.out.println();
        tree.visitPreOrderIterative(s -> System.out.println(s));
    }


    @Test
    public void testInOrder() {
        super.test();

        Tree<Integer> tree = new Tree<>();

        while (tree.size(tree.root, 0) < 5) {
            int key = Double.valueOf(Math.floor(Math.random() * 10f)).intValue();
            tree.insert(key, key);
        }

        tree.print();

        tree.visitInOrder(s -> System.out.println(s));
        System.out.println();
        tree.visitInOrderIterative(s -> System.out.println(s));
    }

    @Test
    public void testPostOrder() {
        super.test();

        Tree<Integer> tree = new Tree<>();

        while (tree.size(tree.root, 0) < 10) {
            int key = Double.valueOf(Math.floor(Math.random() * 10f)).intValue();
            tree.insert(key, key);
        }

        tree.print();

        tree.visitPostOrder(s -> System.out.println(s));
        System.out.println();
        tree.visitPostOrderIterative(s -> System.out.println(s));
    }

    @Test
    public void testLca() {
        super.test();

        RedBlackBST<Integer> tree = new RedBlackBST<>();

        int cnt = 0;
        while (tree.size(tree.root, 0) < 10) {
            int key = cnt++;
            tree.insert(key, key);
        }

        tree.print();

        Tree<Integer>.Node<Integer> lca = tree.getLca(tree.findPreOrder(4), tree.findPreOrder(6));
        System.out.println("4 and 6 LCA:" + lca.id);
        lca = tree.getLca(tree.findPreOrder(8), tree.findPreOrder(9));
        System.out.println("8 and 9 LCA:" + lca.id);

    }
}