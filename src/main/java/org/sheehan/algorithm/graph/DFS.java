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
public class DFS {
    private boolean marked[];
    private final Graph graph;

    public DFS(Graph graph){
        marked = new boolean[graph.getNumV()];
        this.graph = graph;
    }

    // recursion simulates explicit use of stack with runtime function stack
    public void visitRecursion(GraphNode v){
        v.visited = true;
        for(GraphNode node: graph.getNeighbors(v)){
            if (!node.visited && graph.isEdge(v, node))
                visitRecursion(node);
        }
    }


    // use a stack for DFS - SAME AS BFS with stack instead of queue
    public void visitIterative(GraphNode sourceNode){;
        Stack<GraphNode> stack = new StackArrayImpl<>(this.graph.getNumV());
        stack.push(sourceNode);
        sourceNode.visited = true; // mark after adding to queue
        while (stack.peek() != null) {
            GraphNode node = stack.pop();
            List<GraphNode> neighbors = graph.getNeighbors(node);
            for (GraphNode neighbor: neighbors){
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
        for (GraphNode node: graph.getNodes()){
            if (node.visited)
                System.out.print(node + " ");
        }
        System.out.println();
        System.out.print("not connected: ");
        for (GraphNode node: graph.getNodes()){
            if (!node.visited)
                System.out.print(node + " ");
        }
        System.out.println();
    }


}
