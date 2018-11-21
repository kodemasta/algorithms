package org.bsheehan.data_structure.tree;

public class BST<V> extends Tree<V> {

    public BST() {
        super();
    }

    public void insertSortedArray(int []sortedArray){
        this.root = insertSortedArray(sortedArray, 0, sortedArray.length-1);
    }

    private Node<V> insertSortedArray(int[] sortedArray, int l, int r) {
        if (l >= r)
            return null;

        int mid = l + (r-l)/2;

        Node<V> node = new Node<V>();
        node.id = sortedArray[mid];
        node.left = insertSortedArray(sortedArray, l, mid);
        if (node.left != null) {
            node.left.parent = node;
            //  node.count += node.left.count;
        }
        node.right = insertSortedArray(sortedArray, mid+1, r);
        if (node.right != null) {
            node.right.parent = node;
            //  node.count += node.right.count;
        }

        return node;
    }

    public boolean isBalanced() {
        return this.isBalanced(this, this.root);
    }


    private boolean isBalanced(Tree tree, Tree.Node node) {
        if (node == null)
            return true;

        int diff = (int)(Math.abs(tree.getMaxDepth(node.left) - tree.getMaxDepth(node.right)));
        boolean balanced = diff <= 1 ? true:false;
        if (!balanced)
            return false;
        return isBalanced(tree, node.left) && isBalanced(tree, node.right);

    }
}
