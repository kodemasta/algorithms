package org.sheehan.algorithm.data_structures.tree;

/* constraints

1. root is BLACK
2. no 2 REDS adjacent (i.e every RED node has BLACK children)
3. same # of black nodes in each path
4. every null leaf is BLACK

different cases involve left right rotations and node color changes

first node is root and is BLACK
new nodes are colored RED -> if new node has any RED children -> rebalance

sometimes just change color no rotations !

 */
public class RedBlackBinarySearchTree<K extends Comparable<?super K>, V> extends BinarySearchTree<K,V>{

    public RedBlackBinarySearchTree() {
        super();
    }


    private TreeNode<K,V> grandparent(TreeNode<K,V> node) {
        if (node == null)
            return null;
        if (node.parent == null)
            return null;
        return node.parent.parent;
    }

    private TreeNode<K,V> uncle(TreeNode<K,V> node) {
        TreeNode<K,V> gp = grandparent(node);

        if (gp != null) {
            if (gp.left != node.parent)
                return gp.left;
            else
                return gp.right;
        }
        return null;
    }

    @Override
    protected TreeNode<K,V> insert(K key, V value) {
        TreeNode<K, V> newNode = super.insert(key, value);

        //rebalance the tree - check 5 cases on wikipedia !!!!
        postInsertCase1(newNode);

        return newNode;
    }

    // Case 1: The current node N is at the root of the tree.
    // In this case, it is repainted black to satisfy property 2 (the root is black).
    // Since this adds one black node to every path at once,
    // property 5 (all paths from any given node to its leaf nodes contain the same number of black nodes)
    // is not violated.
    private void postInsertCase1(TreeNode<K,V> node) {
        if (node.parent == null) {
            node.color = BLACK;
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
    private void postInsertCase2(TreeNode<K,V> node) {
        if (node.parent.color == BLACK) {
            return;
        }
        else
            postInsertCase3(node);
    }

    // Case 3: If both the parent P and the uncle U are red, then both of them can be repainted black and
    // the grandparent G becomes red (to maintain property 5
    // (all paths from any given node to its leaf nodes contain the same number of black nodes)).
    private void postInsertCase3(TreeNode<K,V> node) {
        // parent is RED if we getBstNode here ! Check uncle
        TreeNode<K,V> u = uncle(node);
        if ((u != null) && (u.color == RED)) {
            node.parent.color = BLACK;
            u.color = BLACK;
            TreeNode<K, V> g = grandparent(node);
            g.color = RED;
            postInsertCase1(g); //recursive in case this just messed up the constraints on g
        } else {
            postInsertCase4(node);
        }
    }

    private void postInsertCase4(TreeNode<K,V> node) {
        TreeNode<K,V> g = grandparent(node);

        if ((node == node.parent.right) && (node.parent == g.left)) {
            rotate_left(node.parent);
            node = node.left;

        } else if ((node == node.parent.left) && (node.parent == g.right)) {
            rotate_right(node.parent);
            node = node.right;
        }
        postInsertCase5(node);
    }

    private void postInsertCase5(TreeNode<K,V> node) {
        TreeNode<K,V> g = grandparent(node);

        node.parent.color = BLACK;
        g.color = RED;
        if (node == node.parent.left)
            rotate_right(g);
        else
            rotate_left(g);
    }

    private void rotate_left(TreeNode<K,V> x){
        TreeNode<K,V> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x; // reset parent

        y.parent = x.parent; // reset parent

        // handle root or x parent refs
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }
    private void rotate_right(TreeNode<K,V> y){
        TreeNode<K,V> x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y; // reset parent

        x.parent = y.parent; // reset parent

        // handle root or y parent refs
        if (y.parent == null) {
            this.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        x.right = y;
        y.parent = x;
    }

    // O(height of tree)
    // three cases
    // 1. no children - remove
    // 2. one child - splice it out
    // 3. two children - splice out successorWithParent, replace with successorWithParent
    @Override
    protected TreeNode<K,V> delete(TreeNode<K,V> deleteNode) {
        TreeNode<K,V> spliceNode = null; // either replacement or deleted !

        //case 1, 2
        if (deleteNode.left == null || deleteNode.right == null){
            spliceNode = deleteNode;
        } else //case 3
            spliceNode = successorWithParent(deleteNode); // at most one node ?

        // now do the splice botch
        TreeNode<K,V> spliceChild = null;
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
            deleteNode.key = spliceNode.key;
            deleteNode.value = spliceNode.value;
        }

        return spliceNode;
    }

}
