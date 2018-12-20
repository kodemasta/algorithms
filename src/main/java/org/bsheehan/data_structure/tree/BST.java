package org.bsheehan.data_structure.tree;

import org.sheehan.algorithm.data_structures.tree.BinaryTree;

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

    // O(height of tree)
    // three cases
    // 1. no children - remove
    // 2. one child - splice it out
    // 3. two children - splice out successorWithParent, replace with successorWithParent

    protected Node<V> delete(Node<V> deleteNode) {
        Node<V> spliceNode = null; // either replacement or deleted !

        //case 1, 2
        if (deleteNode.left == null || deleteNode.right == null){
            spliceNode = deleteNode;
        } else //case 3
            spliceNode = successorWithParent(deleteNode); // at most one node ?

        // now do the splice botch
        Node<V> spliceChild = null;
        if (spliceNode.left != null){
            spliceChild = spliceNode.left;
        } else {
            spliceChild = spliceNode.right;
        }
        // point orphaned child to grandparent (new parent)
        if (spliceChild != null)
            spliceChild.parent = spliceNode.parent;

        // point new parent at new child
        if (spliceNode.parent == null) { //root
            root = spliceChild;
        } else if (spliceNode == spliceNode.parent.left) {
            spliceNode.parent.left = spliceChild;
        } else {
            spliceNode.parent.right = spliceChild;
        }

        // case 3
        if (deleteNode != spliceNode) {
            deleteNode.id = spliceNode.id;
            deleteNode.value = spliceNode.value;
        }

        return spliceNode;
    }

    public Node<V> minimum(Node<V> node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public Node<V> maximum(Node<V> node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    // find node with next highest value
    // STEP 1. left most node in right subtree OR
    // STEP 2 if no right subtree go up until you go right !
    public Node<V> successorWithParent(Node<V> node){

        // STEP 1.  if right child then getBstNode min of that right tree !
        if (node.right != null)
            return minimum(node.right);

        // STEP 2.
        Node<V> parent = node.parent;
        Node<V> curr = node;

        // if there is a parent AND the node is the right child,
        // we look up getting smaller for parents to left so getBstNode them out of the way
        // then return the first parent to the RIGHT (child is left of parent)--> will be successorWithParent
        while(parent != null && curr.equals(parent.right)){
            curr = parent; // may not be right
            parent = parent.parent;
        }

        // rightmost node special case
        if (parent != null && parent.id.compareTo(node.id) < 0 && node.id.equals(maximum(this.root).id))
            return node;

        return parent;
    }

    public Node<V> successorWithoutParent(Node<V> root, Node<V> node) {
        if (node.right !=null)
            return minimum(node.right);

        // if in left then we iterate downleft side
        // everytime we compare and find the curr is larger then we have a potential successor so save result.
        // if we find our key is larger than current then shift to right and continue...
        // if we get to k then we hope the result was already captured and break to return the more rescent successor.
        Node<V> curr=root;
        Node<V> result=null;
        while (curr!=null) {
            if (node.id.compareTo(curr.id) < 0) {
                result = curr;
                curr = curr.left;
            } else if (node.id.compareTo(curr.id) > 0)
                curr = curr.right;
            else
                break;
        }
        return result;
    }

    // find node with next smallest value
    // STEP 1. right most node in left subtree OR
    // STEP 2. if no left subtree go up until you go left !
    Node<V> predecessorWithParent(Node<V> node){

        // STEP 1. if left child then getBstNode max of that left tree !
        if (node.left != null)
            return maximum(node.left);

        //STEP 2...
        Node<V> parent = node.parent;
        Node<V> curr = node;

        // if there is a parent AND the node is the right ancestor,
        // we look up getting smaller for parents to left so getBstNode them out of the way
        // then return the first parent to the LEFT (child is right of parent)--> will be successorWithParent
        while(parent != null && curr.equals(parent.left)){
            curr = parent;
            parent = parent.parent;
        }

        // leftmost node special case
        if (parent != null && parent.id.compareTo(node.id) > 0 && node.value.equals(minimum(this.root).id))
            return node;

        return parent;
    }

    public Node<V> predecessorWithoutParent(Node<V> root, Node<V> node) {
        if (node.left !=null)
            return maximum(node.left);

        // if in right then we iterate down right side
        // everytime we compare and find the curr is larger then we have a potential successor so save result.
        // if we find our key is larger than current then shift to right and continue...
        // if we get to k then we hope the result was already captured and break to return the more rescent successor.
        Node<V> curr=root;
        Node<V> result=null;
        while (curr!=null) {
            if (node.id.compareTo(curr.id) > 0) {
                result = curr;
                curr = curr.right;
            } else if (node.id.compareTo(curr.id) < 0)
                curr = curr.left;
            else
                break;
        }
        return result;
    }
}
