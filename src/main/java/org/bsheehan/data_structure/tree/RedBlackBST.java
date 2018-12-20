package org.bsheehan.data_structure.tree;

import java.awt.*;

/* constraints

1. root is BLACK
2. no 2 REDS adjacent (i.e every RED node has BLACK children)
3. same # of black nodes in each path
4. every null leaf is BLACK

different cases involve left right rotations and node color changes

new nodes are colored RED -> if new node has any RED children -> rebalance

sometimes just change color no rotations!

The balancing of the tree is not perfect, but it is good enough to allow it to guarantee searching in O(log n) time, where n is the total number of elements in the tree.
The insertion and deletion operations, along with the tree rearrangement and recoloring, are also performed in O(log n) time.

*/

public class RedBlackBST<V> extends BST<V> {

    public RedBlackBST() {
        super();
    }

    private Node<V> grandparent(Node<V> node) {
        if (node == null)
            return null;
        if (node.parent == null)
            return null;
        return node.parent.parent;
    }

    private Node<V> uncle(Node<V> node) {
        Node<V> gp = grandparent(node);

        if (gp != null) {
            if (gp.left != node.parent)
                return gp.left;
            else
                return gp.right;
        }
        return null;
    }

    @Override
    public Node<V> insert(int key, V value) {
        Node<V> newNode = super.insert(key, value);
        newNode.color = Color.RED;

        //rebalance the tree - check 5 cases on wikipedia !!!!
        postInsertCase1(newNode);

        return newNode;
    }

    // Case 1: The current node N is at the root of the tree.
    // In this case, it is repainted black to satisfy property 2 (the root is black).
    // Since this adds one black node to every path at once,
    // property 5 (all paths from any given node to its leaf nodes contain the same number of black nodes)
    // is not violated.
    private void postInsertCase1(Node<V> node) {
        if (node.parent == null) {
            node.color = Color.BLACK;
        }
        else
            postInsertCase2(node);
    }

    // Case 2: The current node's parent P is black, so property 4 (both children of every red node are black)
    // is not invalidated. In this case, the tree is still valid.
    // Property 5 (all paths from any given node to its leaf nodes contain the same number of black nodes)
    // is not threatened, because the current node N has two black leaf children,
    // but because N is red, the paths through each of its children have the same number of black nodes as
    // the path through the leaf it replaced, which was black, and so this property remains satisfied.
    private void postInsertCase2(Node<V> node) {
        if (node.parent.color == Color.BLACK) {
            return;
        }
        else
            postInsertCase3(node);
    }

    // Case 3: If both the parent P and the uncle U are red, then both of them can be repainted black and
    // the grandparent G becomes red (to maintain property 5
    // (all paths from any given node to its leaf nodes contain the same number of black nodes)).
    private void postInsertCase3(Node<V> node) {
        // parent is RED if we getBstNode here ! Check uncle
        Node<V> u = uncle(node);
        if ((u != null) && (u.color == Color.RED)) {
            node.parent.color = Color.BLACK;
            u.color = Color.BLACK;
            Node<V> g = grandparent(node);
            g.color = Color.RED;
            postInsertCase1(g); //recursive in case this just messed up the constraints on g
        } else {
            postInsertCase4(node);
        }
    }

    private void postInsertCase4(Node<V> node) {
        Node<V> g = grandparent(node);

        if (g == null)
            return;

        if ((node == node.parent.right) && (node.parent == g.left)) {
            rotate_left(node.parent);
            node = node.left;

        } else if ((node == node.parent.left) && (node.parent == g.right)) {
            rotate_right(node.parent);
            node = node.right;
        }
        postInsertCase5(node);
    }

    private void postInsertCase5(Node<V> node) {
        Node<V> g = grandparent(node);
        if (g == null)
            return;

        node.parent.color = Color.BLACK;
        g.color = Color.RED;
        if (node == node.parent.left)
            rotate_right(g);
        else
            rotate_left(g);
    }



    // O(height of tree)
    // three cases
    // 1. no children - remove
    // 2. one child - splice it out
    // 3. two children - splice out successorWithParent, replace with successorWithParent
    @Override
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

}
