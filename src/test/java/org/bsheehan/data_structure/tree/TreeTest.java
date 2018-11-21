package org.bsheehan.data_structure.tree;

import org.bsheehan.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest extends BaseTest {

        @Test
        public void test() {
            super.test();

            Tree<Integer> tree = new Tree<>();

            while(tree.size(tree.root, 0) < 10) {
                int key = Double.valueOf(Math.floor(Math.random() * 100f)).intValue();
                tree.insert(key,key);
            }

            tree.print();
            tree.mirror();
            tree.print();

            tree.visitPreOrder(s-> System.out.println(s));
            System.out.println();
            tree.visitInOrder(s-> System.out.println(s));
            System.out.println();
            tree.visitPostOrder(s-> System.out.println(s));
        }
    }