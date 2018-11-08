package org.bsheehan.data_structure.tree.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.tree.Tree;
import org.junit.Test;

public class IsBalancedTest  extends BaseTest {

    @Test
    public void test() {
        super.test();

        boolean balanced = false;

        Tree<Integer> tree = null;

        while(!balanced) {
            tree = new Tree<>();
            while(tree.size(tree.root, 0) < 10) {
                int key = Double.valueOf(Math.floor(Math.random() * 100f)).intValue();
                tree.insert(key, key);
            }
            balanced = IsBalanced.isBalanced(tree, tree.root);
        }

        if (tree != null)
            tree.print();
        System.out.println(IsBalanced.isBalanced(tree, tree.root));

    }

}