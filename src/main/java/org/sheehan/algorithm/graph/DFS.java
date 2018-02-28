package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;
import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;

/**
 * Created by bob on 7/8/14.
 *
 * DFS - stack - mark after pop
 *
 * no shortest paths
 */
public class DFS <T extends Comparable<T>>{
    private boolean marked[];
    private final Graph<T> graph;

    public DFS(Graph graph){
        marked = new boolean[graph.getNumV()];
        this.graph = graph;
    }

    public void visitRecursion(GraphNode<T> v){
        v.visited = true;
        for(GraphNode<T> node: graph.getNeighbors(v)){
            if (!node.visited && graph.isEdge(v, node))
                visitRecursion(node);
        }
    }


    // use a stack for DFS - SAME AS BFS with stack instead of queue
    public void visitIterative(GraphNode<T> sourceNode){;
        Stack<GraphNode<T>> stack = new StackArrayImpl<>(graph.getNumV());
        stack.push(sourceNode);
        sourceNode.visited = true; // mark after adding to queue
        while (stack.peek() != null) {
            GraphNode<T> node = stack.pop();
            List<GraphNode<T>> neighbors = graph.getNeighbors(node);
            for (GraphNode<T> neighbor: neighbors){
                if (!neighbor.visited) {
                    stack.push(neighbor);
                    neighbor.visited = true; // mark after adding to queue
                    neighbor.distance = node.distance+1;
                }
            }
        }
    }

    public void printConnected() {
        System.out.print("connected: ");
        for (GraphNode<T> node: graph.getNodes()){
            if (node.visited)
                System.out.print(node + " ");
        }
        System.out.println();
        System.out.print("not connected: ");
        for (GraphNode<T> node: graph.getNodes()){
            if (!node.visited)
                System.out.print(node + " ");
        }
        System.out.println();
    }


}
