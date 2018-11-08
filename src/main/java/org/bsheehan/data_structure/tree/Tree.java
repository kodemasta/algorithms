package org.bsheehan.data_structure.tree;

import org.sheehan.algorithm.data_structures.tree.BinaryTree;

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
