package org.bsheehan.data_structure.tree;

import org.bsheehan.data_structure.queue.ListQueue;
import org.bsheehan.data_structure.stack.ListStack;
import org.sheehan.algorithm.data_structures.tree.BinaryTree;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class Tree <V> {
    ///////////////////////////////////////////////
    public class Node<V> {

        Integer id;
        V value;
        public Node<V> parent;
        public Node<V> left;
        public Node<V> right;

        public Color color; // RB Tree

        public Node(Integer id, V value){
            this.id = id;
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public Node(){
            this.id = 0;
            this.value = null;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    ///////////////////////////////////////////////
    public Node root;

    public Tree() {
        this.root = null;
    }

    public Node<V> insert(int id, V value) {
        if (root == null) {
            root = new Node(id, value);
            return root;
        }
        return insert(root, id, value);
    }

    private Node<V> insert(Node node, int id, V value) {

        if (id >= node.id && node.right != null)
            return insert(node.right, id, value);
        else if (id >= node.id && node.right == null) {
            node.right = new Node(id, value);
            node.right.parent = node;
            return node.right;
        } else if (id < node.id && node.left != null)
            return insert(node.left, id, value);
        else if (id < node.id && node.left == null) {
            node.left = new Node(id, value);
            node.left.parent = node;
            return node.left;
        }
        return null;
    }

    public void print(){
        System.out.println();
        this.print(this.root, 0);
        System.out.println();
    }

    // infix layout - horizontal view of tree
    private void print(Node root, int level){
        if(root == null)
            return;
        print(root.right, level+1); //top of console

        if(level !=0 ) {
            for(int i=0; i<level-1; i++)
                System.out.print("|\t");
            System.out.println("|-------"+root.id);
        }
        else
            System.out.println(root.id);

        print(root.left, level+1); //bottom of console
    }

    public void mirror(){
        mirror(this.root);
    }

    // swap left and right at each level
    private void mirror(Node<V> node){
        if (node == null)
            return;
        else { //swap
            Node<V> tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }

        //recurse
        mirror(node.left);
        mirror(node.right);
    }

    public int size(Node<V> node, int cnt) {
        if (node == null)
            return 0;
        cnt += size(node.left, cnt) + size(node.right, cnt) + 1;
        return cnt;
    }

    public void visitInOrderIterative(Consumer op) {
        visitInOrderIterative(this.root, op);
    }

    public void visitPreOrderIterative(Consumer op) {
        visitPreOrderIterative(this.root, op);
    }

    public void visitPostOrderIterative(Consumer op) {
        visitPostOrderIterative(this.root, op);
    }


    public void visitInOrder(Consumer op) {
        visitInOrder(this.root, op);
    }

    public void visitPreOrder(Consumer op) {
        visitPreOrder(this.root, op);
    }

    public void visitPostOrder(Consumer op) {
        visitPostOrder(this.root, op);
    }

    public void visitLevelOrder(Consumer op) { visitLevelOrder(this.root, op); }

    public void visitVerticalOrder(Consumer op) { visitVerticalOrder(this.root, op); }

    public void visitLevelZigZagOrder(Consumer op) { visitLevelZigZagOrder(this.root, op); }

    public List<List<Node<V>>> visitPaths() {
        List<List<Node<V>>> paths = new ArrayList<List<Node<V>>>();
        Stack<Node<V>> path = new Stack<Node<V>>();
        visitPaths(this.root, paths, path);
        return paths;
    }

    private void visitInOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;
        visitInOrder(node.left, op);
        op.accept(node.id);
        visitInOrder(node.right, op);
    }

    private void visitPreOrderIterative(Node<V> root, Consumer op) {
        // stack is for tracking purposes only
        Stack<Node<V>> visitedStack = new Stack<>();

        Node<V> node = root;
        while (true) {
            // traverse left as far as possible
            // push used nodes on stack
            while (node != null) {
                op.accept(node.id);
                visitedStack.push(node);
                node = node.left;
            }

            // end of left run pop off a node and go right
            if (visitedStack.isEmpty())
                break;
            node = visitedStack.pop();
            node = node.right;
        }

    }

    private void visitInOrderIterative(Node<V> root, Consumer op) {
        // stack is for tracking purposes only
        Stack<Node<V>> visitedStack = new Stack<>();

        Node<V> node = root;
        while (true) {
            // traverse left as far as possible
            // push used nodes on stack
            while (node != null) {
                visitedStack.push(node);
                node = node.left;
            }

            // end of left run pop off a node and go right
            if (visitedStack.isEmpty())
                break;
            node = visitedStack.pop();
            op.accept(node.id);
            node = node.right;
        }

    }

    // one stack version
    private void visitPostOrderIterative(Node<V> root, Consumer op) {
        // stack is for tracking purposes only
        Stack<Node<V>> visitedStack = new Stack<>();

        Node<V> node = root;
        while (true) {
            // traverse left as far as possible
            // push used nodes on stack
            while (node != null) {
                visitedStack.push(node);
                node = node.left;
            }

            // end of left run pop off a node and go right
            if (visitedStack.isEmpty())
                break;

            node = visitedStack.peek(); // get here after going left as far as we can

            // case 1: leaf node
            if (node.right == null) {
                node = visitedStack.pop(); // get the node for processing
                op.accept(node.id);
                // special case of right child nodes. need to move up right child chain !
                Node<V> parent;
                while (!visitedStack.isEmpty() && (parent = visitedStack.peek()) != null) {
                    if (parent.right != null && parent.right.id == node.id) {
                        node = visitedStack.pop();
                        op.accept(node.id);
                    } else
                        break;
                }
            }

            // move to right
            if (!visitedStack.isEmpty())
                node = node.right;
            else
                node = null;
        }
    }

    public Node<V> findPreOrder(int key) {
        return findPreOrder(this.root, key);
    }

    private Node<V> findPreOrder(Node<V> node, int key) {
        if (node == null)
            return null;
        if (node.id == key)
            return node;
        Node<V> n1 = findPreOrder(node.left, key);
        if (n1 != null)
            return n1;
        Node<V> n2 = findPreOrder(node.right, key);
        if (n2 != null)
            return n2;
        return null;
    }

    private void visitPreOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;
        op.accept(node.id);
        visitPreOrder(node.left, op);
        visitPreOrder(node.right, op);
    }

    private void visitPostOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;

        visitPostOrder(node.left, op);
        visitPostOrder(node.right, op);
        op.accept(node.id);
    }

    // use queue, and mark end of level with null
    // this uses a marker to delimit the levels, however not required
    private void visitLevelOrder(Node<V> node, Consumer op) {

        ListQueue<Node<V>> queue = new ListQueue<>();
        queue.enqueue(node);
        queue.enqueue(null); // mark end of level
        while (queue.size() != 0) {
            Node<V> n = queue.dequeue();
            if (n != null) {
                op.accept(n.id);
                if (n.left != null)
                    queue.enqueue(n.left);
                if (n.right != null)
                    queue.enqueue(n.right);
            } else if (queue.size() != 0) {//special case: do not do at end of tree
                op.accept(" - ");
                queue.enqueue(null); // mark end of level
            }
        }
    }

    // use queue, and mark end of level with null
    private void visitLevelZigZagOrder(Node<V> node, Consumer op) {
        ListQueue<Node<V>> queue = new ListQueue<>();
        queue.enqueue(node);
        queue.enqueue(null); // mark end of level
        List<Node<V>> list = new ArrayList<>();
        list.add(node);
        list.add(null);
        while (queue.size() != 0) {
            Node<V> n = queue.dequeue();
            if (n != null) {
                if (n.left != null) {
                    queue.enqueue(n.left);
                    list.add(n.left);
                }
                if (n.right != null) {
                    queue.enqueue(n.right);
                    list.add(n.right);
                }
            } else { // new level yo !
                if (queue.size() != 0) { //special case: do not do at end of tree
                    queue.enqueue(null); // mark end of level
                    list.add(null);
                }
            }
        }

        boolean reverse = false;
        ListStack<Node<V>> stack = new ListStack<>();
        for (Node<V> n: list) {
            if (n != null) {
                if (reverse)
                    stack.push(n);
                else
                    op.accept(n.id);
            } else { // end of level terminator
                if (reverse) {
                    while (stack.size() != 0){
                        op.accept(stack.pop().id);
                    }
                }
                reverse  = !reverse;
                op.accept(" - ");
            }
        }
    }

    // use queue, and mark end of level with null
    // this uses a marker to delimit the levels, however not required
    private void visitVerticalOrder(Node<V> node, Consumer op) {
    }

        // use stack to manage each path
    // clone path at leaf node and add to collection of paths
    private void visitPaths(Node<V> node, List<List<Node<V>>> paths, Stack<Node<V>> path) {
        path.push(node);
        if (node.left == null && node.right == null) {
            // clone
            List<Node<V>> newPath = new ArrayList<Node<V>>();
            newPath.addAll(path);
            paths.add(newPath);
            return;
        }

        if (node.left != null) {
            visitPaths(node.left, paths, path);
            path.pop();
        }
        if (node.right != null) {
            visitPaths(node.right, paths, path);
            path.pop();
        }

    }

    public int getMaxDepth() {
        return getMaxDepth(this.root);
    }

    public int getMaxDepth(Node<V> root) {
        if(root == null)
            return 0;

        return 1 + Math.max(getMaxDepth(root.left), getMaxDepth(root.right));
    }

    public int getMinDepth() {
        return getMinDepth(this.root);
    }

    public int getMinDepth(Node<V> node) {
        if(node == null)
            return 0;

        return 1 + Math.min(getMinDepth(node.left), getMinDepth(node.right));
    }


    /* A wrapper over diameter(Node root) */
    public int diameter()
    {
        return diameter(root);
    }

    /**
     * More than just max height. Neet to combine left and right max heights and recurse to get max
     * @param node
     * @return
     */
    private int diameter(Node<V> node)
    {
        /* base case if tree is empty */
        if (node == null)
            return 0;

        return Math.max(getMaxDepth(node.left) + getMaxDepth(node.right) + 1,
                Math.max(diameter(node.left), diameter(node.right)));

    }


    public int numLeaves()
    {
        return numLeaves(root);
    }

    private int numLeaves(Node<V> node)
    {
        /* base case if tree is empty */
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        return numLeaves(node.left) + numLeaves(node.right);

    }

    protected void rotate_left(Node<V> x){
        Node<V> y = x.right;
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

    protected void rotate_right(Node<V> y){
        Node<V> x = y.left;
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

    public Node<V> getLca(Node<V> p, Node<V> q){
        return getLca(this.root,p,q);
    }

    private Node<V> getLca(Node<V> root, Node<V> p, Node<V> q){
        if (root == null)
            return null ;

        Node<V> left = getLca(root.left, p, q);
        Node<V> right = getLca(root.right, p, q);

        // 5 cases !
        if (root == p || root == q) //its the root !
            return root;
        if (left != null && right == null)
            return left;
        else if (left == null && right != null)
            return right;
        else if (left == null && right == null) //neither found
            return null;
        else
            return root; // expected case if in different trees
    }
}
