package org.bsheehan.data_structure.tree;

import org.bsheehan.data_structure.queue.ListQueue;
import org.bsheehan.data_structure.stack.ListStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tree <V> {
    ///////////////////////////////////////////////
    public class Node<V> {

        Integer id;
        V value;
        public Node<V> parent;
        public Node<V> left;
        public Node<V> right;

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

        if (id > node.id && node.right != null)
            insert(node.right, id, value);
        else if (id > node.id && node.right == null) {
            node.right = new Node(id, value);
            return node.right;
        } else if (id < node.id && node.left != null)
            insert(node.left, id, value);
        else if (id < node.id && node.left == null) {
            node.left = new Node(id, value);
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

    public void visitLevelZigZagOrder(Consumer op) { visitLevelZigZagOrder(this.root, op); }

    private void visitInOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;
        visitInOrder(node.left, op);
        op.accept(node.id);
        visitInOrder(node.right, op);
    }

    private void visitPreOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;
        op.accept(node.id);
        visitInOrder(node.left, op);
        visitInOrder(node.right, op);
    }

    private void visitPostOrder(Node<V> node, Consumer op) {
        if (node == null)
            return;

        visitInOrder(node.left, op);
        visitInOrder(node.right, op);
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
            } else {
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

}
