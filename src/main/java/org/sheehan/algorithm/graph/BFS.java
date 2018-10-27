package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;
import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bob on 7/8/14.
 * BFS - uses queue - mark after enqueue
 */
public class BFS {

    private final Graph graph;

    public BFS(Graph graph){
        this.graph = graph;
    }

    // use a queue for BFS - NO RECURSIVE FOR BSF
    public void visitIterative(GraphNode sourceNode){
        clearVisited();
        QueueInterface<GraphNode> queue = new QueueArrayImpl<>(100);
        queue.enqueue(sourceNode);
        sourceNode.visited = true; // mark after adding to queue
        while (queue.peek() != null) {
            GraphNode node = queue.dequeue();
            List<GraphNode> neighbors = graph.getNeighbors(node);
            for (GraphNode neighbor: neighbors){
                if (!neighbor.visited) {
                    queue.enqueue(neighbor);
                    neighbor.visited = true; // mark after adding to queue
                    neighbor.distance = node.distance+1;
                    neighbor.parent = node;
                }
            }
        }
    }

    private void clearVisited() {
        for (GraphNode node : graph.getNodes()) {
            node.visited = false;
            node.distance = 0;
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

    public Set<GraphNode> getConnected() {
        Set<GraphNode> visitedNodes = new HashSet<>();
        for (GraphNode node: graph.getNodes()){
            if (node.visited)
                visitedNodes.add(node);
        }
        return visitedNodes;
    }

    public void printAllPaths() {
        for (GraphNode destNode: graph.getNodes()) {
             GraphNode node = destNode;
            if (node.visited) {
                Stack<Integer> path = new StackArrayImpl<>(100);
                path.push(node.id);
                while( node != null && node.parent != null){
                    path.push(node.parent.id);
                    node=node.parent;
                }
                System.out.print("path to :" + destNode.id + " - ");
                while(path.peek() != null){
                    System.out.print(path.pop() + " ");
                }
                System.out.println();

            }
        }



    }




}
