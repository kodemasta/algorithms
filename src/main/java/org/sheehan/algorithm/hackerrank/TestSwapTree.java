package org.sheehan.algorithm.hackerrank;


import java.util.*;

public class TestSwapTree {

    static class TreeNode <T> {
        public T value;
        public TreeNode <T> left;
        public TreeNode <T> right;
        TreeNode <T> parent; // for successor BST traversal

        @Override
        public String toString(){
            return value.toString();
        }
    }

    static <T> TreeNode<T>  createTreeNode(T value){
        TreeNode<T> node = new TreeNode<>();
        node.value = value;
        node.left = null;
        node.right= null;

        return node;
    }

    static private <T> int getHeight(TreeNode<T> node){
        if (node == null)
            return 0;
        else
            return 1 + Math.max(getHeight(node.left ), getHeight(node.right ));
    }

    static public <T> void getLevelNodes(TreeNode<T> node, int cLevel, int rLevel, List<TreeNode<T>> nodes ){

        if (node == null)
            return;
        if (cLevel == rLevel)
            nodes.add(node);
        else {
            getLevelNodes(node.left, cLevel+1, rLevel, nodes);
            getLevelNodes(node.right, cLevel+1, rLevel, nodes);
        }
    }

    static public  <T> void mirror(TreeNode<T> node){
        if (node == null)
            return;
        else { //swap
            swap(node);
        }

        //recurse
        if (node.left != null)
            mirror(node.left);
        if (node.right != null)
            mirror(node.right);
    }

    private static <T> void swap(TreeNode<T> node) {
        TreeNode<T> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    static private <T> void printInOrder(TreeNode<T> root) {
        if (root == null)
            return;
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeNode<Integer> root = TestSwapTree.createTreeNode(1);
        Map<Integer, TreeNode<Integer>> treeNodes = new HashMap<>();

        treeNodes.put(1, root);
        for (int i = 1; i <= n; ++i) {
            TreeNode<Integer> parent = treeNodes.get(i);
            int left = in.nextInt();
            int right = in.nextInt();
            TreeNode<Integer> leftNode = null;
            TreeNode<Integer> rightNode = null;
            if (left != -1) {
                leftNode = TestSwapTree.createTreeNode(left);
                treeNodes.put(left, leftNode);
            }
            if (right != -1) {
                rightNode = TestSwapTree.createTreeNode(right);
                treeNodes.put(right, rightNode);
            }
            parent.left = leftNode;
            parent.right = rightNode;
        }


        int t = in.nextInt();
        int levels[] = new int[t];
        for (int i = 0; i < t; ++i)
            levels[i] = in.nextInt();

        int height = TestSwapTree.getHeight(root);
        for (int level : levels) {
            for (int i=1; i < height; ++i) {
                int rLevel = level*i;
                if (rLevel > height)
                    break;
                List<TreeNode<Integer>> nodes = new ArrayList<>();
                TestSwapTree.getLevelNodes(root, 1, rLevel, nodes);

                for (TreeNode<Integer> node : nodes) {
                    swap(node);
                }
            }
            printInOrder(root);
            System.out.println();
        }
    }
}