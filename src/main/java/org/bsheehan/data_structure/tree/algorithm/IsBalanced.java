package org.bsheehan.data_structure.tree.algorithm;

import org.bsheehan.data_structure.tree.Tree;

public class IsBalanced {

    public static boolean isBalanced(Tree tree, Tree.Node node) {
        if (node == null)
            return true;

        int diff = (int)(Math.abs(tree.getMaxDepth(node.left) - tree.getMaxDepth(node.right)));
        boolean balanced = diff <= 1 ? true:false;
        if (!balanced)
            return false;
        return isBalanced(tree, node.left) && isBalanced(tree, node.right);

    }
}
