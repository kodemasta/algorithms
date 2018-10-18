package org.sheehan.algorithm.data_structures.tree;

import org.sheehan.algorithm.data_structures.queue.QueueInterface;
import org.sheehan.algorithm.data_structures.queue.QueueListImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * Created by bob on 7/9/14.
 *
 * BinaryTree created using linked nodes
 */
public class BinaryTree<K extends Comparable<? super K>, V> {

    protected static final boolean RED   = true;
    protected static final boolean BLACK = false;

    public TreeNode<K, V> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(TreeNode<K,V> node) {
        this.root = node;
    }

    public TreeNode<K, V> getNode(TreeNode<K,V> root, K key) {
        if (root == null)
            return null;
        if (root.key.equals(key)){
            return root;
        }
        TreeNode<K,V> leftNode =  getNode(root.left, key);
        TreeNode<K,V> rightNode =  getNode(root.right, key);

        return leftNode==null?rightNode:leftNode;
    }

    public static class DeepestNode<K extends Comparable<K>,V> {
        int level;
        TreeNode<K,V> node;
    };

    // check level and update result if end node is deeper than previous value
    public void getDeepestNode(TreeNode<K,V> node, DeepestNode result, int level) {
        if (node == null) {
            // nothing to do
            return;
        }

        //at end node
        if (node.left == null && node.right == null) {
            // we are the left leaf node
            if (level > result.level) {
                result.level = level;
                result.node = node;
            }
        }
        getDeepestNode(node.left, result, level + 1);
        getDeepestNode(node.right, result, level + 1);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null)
            return false;
        else if (left.key != right.key)
            return false;

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);

    }

    public void insertSortedArray(K []sortedArray){
       this.root = insertSortedArray(sortedArray, 0, sortedArray.length-1);
    }

    private TreeNode<K,V> insertSortedArray(K[] sortedArray, int l, int r) {
        if (l >= r)
            return null;

        int mid = l + (r-l)/2;

        TreeNode<K,V> node = new TreeNode<>();
        node.key = sortedArray[mid];
        node.left = insertSortedArray(sortedArray, l, mid);
        if (node.left != null)
            node.left.parent = node;
        node.right = insertSortedArray(sortedArray, mid+1, r);
        if (node.right != null)
            node.right.parent = node;
        return node;
    }

    public int getDepth(TreeNode<K,V> root, K key, int level) {
        if(root == null)
            return 0;
        if (root.key.equals(key))
            return level;

        return Math.max(getDepth(root.left, key, level+1), getDepth(root.right, key, level+1));
    }

    public int getMaxDepth(TreeNode<K,V> root) {
        if(root == null)
            return 0;

        return 1 + Math.max(getMaxDepth(root.left), getMaxDepth(root.right));
    }

    public int getMinDepth(TreeNode<K,V> node) {
        if(node == null)
            return 0;

        return 1 + Math.min(getMinDepth(node.left), getMinDepth(node.right));
    }

    //  no leaf nodes are > 1 apart.
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return getMaxDepth(root) - getMinDepth(root) <= 1 ? true:false;
    }


    public boolean isAllBalanced(TreeNode root) {
        if (root == null)
            return true;

        int diff = (int)(Math.abs(getMaxDepth(root.left) - getMaxDepth(root.right)));
        boolean balanced = diff <= 1 ? true:false;
        if (!balanced)
            return false;
        return isAllBalanced(root.left) && isAllBalanced(root.right);

    }

    //recursive
    public int getMaxSum(TreeNode<Integer, Integer> node){
        if (node == null)
            return 0;
        else
            return node.value + Math.max(getMaxSum(node.left), getMaxSum(node.right));
    }


    // pass is accumlated path and list to add it too
    public void getPaths(TreeNode<Integer, Integer> node, List<String> paths, String path) {
        if (node == null)
            return;

        // accumulate path for this stack frame
        path += node.key.toString() + " ";
        // we have reached and end path node so add the accumlated path here
        if (node.left==null && node.right==null)
            paths.add(path);

        getPaths(node.left, paths, path);
        getPaths(node.right,paths, path);
    }

    // pass is accumlated path and list to add it too
    public void getPathSums(TreeNode<Integer, Integer> node, List<Integer> paths, int sum) {
        if (node == null)
            return;

        // accumulate path for this stack frame
        sum += node.key;
        // we have reached and end path node so add the accumlated path here
        if (node.left==null && node.right==null)
            paths.add(sum);

        getPathSums(node.left, paths, sum);
        getPathSums(node.right,paths, sum);
    }

    // perculate up from s=common level to first common parent
    public TreeNode<K,V> getLcaUsingParent(TreeNode<K,V> root, TreeNode<K,V> node1, TreeNode<K,V> node2){
        int depth1 = getDepth(root, node1.key, 0);
        int depth2 = getDepth(root, node2.key, 0);
        TreeNode<K,V> deeper = depth1>depth2?node1:node2;
        TreeNode<K,V> shallower = depth1<=depth2?node1:node2;
        int offset = Math.abs(depth1-depth2);

        //traverse deeper up to same level.
        while(offset != 0 && deeper != null) {
            deeper = deeper.parent;
            System.out.println("deeper up leveled to: " + deeper.toString());
            depth1 = getDepth(root, deeper.key, 0);
            depth2 = getDepth(root, shallower.key, 0);
            offset = Math.abs(depth1-depth2);
        }



        while(deeper != null){
            if (deeper.key.equals(shallower.key))
                return deeper;
            deeper = deeper.parent;
            shallower = shallower.parent;
        }

        return null;
    }

    // POST ORDER TRAVERSAL
    TreeNode<K,V> getLcaWithoutParent(TreeNode<K,V> root, TreeNode<K,V> p, TreeNode<K,V> q){
        if (root == null)
            return null ;

        TreeNode left = getLcaWithoutParent(root.left, p, q);
        TreeNode right = getLcaWithoutParent(root.right, p, q);

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

//    //recursive to print all end node path tot root  node sums
//    public void printEndNodesAndPathSums(TreeNode<Integer, Integer> node){
//        if (node == null)
//            return;
//
//        //when we getBstNode to the end then back propagate utilize parent nodes
//        if (node.left == null && node.right== null){
//            System.out.println("end node: " + node.value);
//            int sum = node.value;
//            TreeNode<Integer, Integer> tmp = node.parent;
//            while (tmp != null){
//                sum += tmp.value;
//                tmp = tmp.parent;
//            }
//            System.out.println("sum path: " + sum);
//
//            return;
//        }
//
//        printEndNodesAndPathSums(node.left);
//        printEndNodesAndPathSums(node.right);
//
//
//    }

    public void getLevelNodes(TreeNode<K,V> node, int cLevel, int rLevel, List<TreeNode<K,V>> nodes ){
        if (node == null)
            return;
        if (cLevel == rLevel) // enqueue to container if level is met
            nodes.add(node);
        else {
            getLevelNodes(node.left, cLevel+1, rLevel, nodes);
            getLevelNodes(node.right, cLevel+1, rLevel, nodes);
        }
    }

    // swap left and right at each level
    public void mirror(TreeNode<K,V> node){
        if (node == null)
            return;
        else { //swap
            TreeNode<K,V> tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }

        //recurse
        mirror(node.left);
        mirror(node.right);
    }

    public void mirror(){
        mirror(root);
    }

    public void printInOrder(){
        printInOrder(this.root);
        System.out.println();
    }

    //one of 3 DFS traversals
    private void printInOrder(TreeNode<K,V> root) {
        if (root == null)
            return;
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }

    //processs left to right
    public void traverseBfs(IntConsumer op){
        traverseBfs(this.root, op);
        System.out.println();
    }

    // 'level order traversal' use QUEUE
    private void traverseBfs(TreeNode<K,V> root, IntConsumer op) {
        if (root == null)
            return;
        QueueInterface<TreeNode<K,V>> q = new QueueListImpl<>();
        q.enqueue(root);
        while (q.peek() != null){

            // getBstNode node
            TreeNode<K,V> node = q.dequeue();

            // do something with it
            op.accept((Integer)node.key);

            // add children
            if (node.left != null)
                q.enqueue(node.left);
            if (node.right != null)
                q.enqueue(node.right);
        }
    }

    //processs left to right
    public List<List<TreeNode<K,V>>> getLevelNodes(IntConsumer op){
        return getLevelNodes(this.root, op);
    }

    // 'level order traversal' use QUEUE
    private List<List<TreeNode<K,V>>> getLevelNodes(TreeNode<K,V> root, IntConsumer op) {
        List<List<TreeNode<K,V>>> result = new ArrayList<List<TreeNode<K,V>>>();

        if (root == null)
            return Collections.emptyList();

        QueueInterface<TreeNode<K,V>> q = new QueueListImpl<>();
        q.enqueue(root);
        while (q.peek() != null){
            List<TreeNode<K,V>> levelNodes = new ArrayList<TreeNode<K,V>>();

            int size = q.size();
            for (int i=0; i<size;++i) {
                // getBstNode node
                TreeNode<K, V> node = q.dequeue();
                levelNodes.add(node);

                // do something with it
                //op.accept((Integer) node.key);

                // add children
                if (node.left != null)
                    q.enqueue(node.left);
                if (node.right != null)
                    q.enqueue(node.right);
            }
            result.add(levelNodes);
        }

        return result;
    }

    public boolean compare(TreeNode<K,V> node){
        return compare(this.root, node);
    }

    private boolean compare(TreeNode<K,V> node1, TreeNode<K,V> node2){

        if (node1 == null && node2 == null)
            return true;

        if (node1 != null && node2 == null)
            return false;

        if (node1 == null && node2 != null)
            return false;

        if (!node1.value.equals(node2.value))
            return false;

        boolean value = true;
        value &= compare(node1.left, node2.left);
        value &= compare(node1.right, node2.right);

        return value;
    }

    //===========================================================================

    public static <K extends Comparable<? super K>, V> TreeNode<K,V> createTreeNode(K key, V value){
        TreeNode<K,V> node = new TreeNode<>();
        node.value = value;
        node.key = key;
        node.left = null;
        node.right= null;
        //node.N = 0;
        node.color = RED;
        return node;
    }

    // execute node and enqueue children
    public static <K extends Comparable<? super K>, V> TreeNode<K,V> createTreeNode(K key, V value, TreeNode<K,V> left, TreeNode<K,V> right){
        TreeNode<K,V> node = new TreeNode<>();
        node.value = value;
        node.key = key;
        node.left = left;
        node.right= right;

        if (left != null)
            left.parent = node;
        if (right != null)
            right.parent = node;

        return node;
    }

    public static class TreeNode <K extends Comparable<? super K>, V> implements Comparable<TreeNode<K, V>> {
        K key;
        V value;
        TreeNode <K,V> left;
        TreeNode <K,V> right;

        TreeNode <K,V> parent; // for successorWithParent BST traversal

        boolean color;     // RB TREE color of parent link
        //int N;             // RB TREE subtree count


        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            if (key!=null)
                sb.append("key:" + key.toString());
            if (value!=null)
                sb.append(" value:" + value.toString());

            sb.append(" color:" + color);

            return sb.toString();
        }

        @Override
        public int compareTo(TreeNode<K, V> o) {
            return 0;
        }
    }

    public void print() {
        print(this.root);
    }

    public void print(TreeNode<K,V> node) {
        print(node, 0, "root");
        System.out.println();
    }

    private void print(TreeNode<K,V> node, int level, String side) {
        if (node == null)
            return;
        System.out.print("(");
        print(node.left, level + 1, "left");
        System.out.print(node.toString());
        print(node.right, level + 1, "right");
        System.out.print(")");
    }

    // print level with either right to left or left to right direction
    public void printLevel(TreeNode<K,V> node, int level, int rLevel, Boolean dir) {
        if (node == null)
            return;
        if (level == rLevel)
            System.out.print(node.toString() + " ");

        if (dir) {
            printLevel(node.left, level + 1, rLevel, dir);
            printLevel(node.right, level + 1, rLevel, dir);
        } else if (!dir) {
            printLevel(node.right, level + 1, rLevel, dir);
            printLevel(node.left, level + 1, rLevel, dir);
        }
    }

    public void printLevelSimple(TreeNode<K,V> node, int level, int rLevel) {

        if (node == null ) {
            if (level == rLevel) {
                System.out.print("- ");
            } else {
                //System.out.print(" ");
            }

            return;
        }
        if (level == rLevel) {
            if (node.parent != null)
                System.out.print(node.key + " (" + node.parent.key  + ") " /*+ node.color + " "*/);
            else
                System.out.print(node.key /*+ " null " + node.color + " "*/);
        }

        printLevelSimple(node.left, level + 1, rLevel);
        printLevelSimple(node.right, level + 1, rLevel);
    }
}
