package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;
import org.sheehan.algorithm.data_structures.queue.QueueArrayImpl;
import org.sheehan.algorithm.data_structures.queue.QueueInterface;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bob on 7/8/14.
 * BFS - uses queue - mark after enqueue
 */
public class BFS <T extends Comparable<T>>{
    private final Graph<T> graph;

    public BFS(Graph<T> graph){
        this.graph = graph;
    }

    // use a queue for BFS - NO RECURSIVE FOR BSF
    public void visitIterative(GraphNode<T> sourceNode){;
        clearVisited();
        QueueInterface<GraphNode<T>> queue = new QueueArrayImpl<>(100);
        queue.enqueue(sourceNode);
        sourceNode.visited = true; // mark after adding to queue
        while (queue.peek() != null) {
            GraphNode<T> node = queue.dequeue();
            List<GraphNode<T>> neighbors = graph.getNeighbors(node);
            for (GraphNode<T> neighbor: neighbors){
                if (!neighbor.visited) {
                    queue.enqueue(neighbor);
                    neighbor.visited = true; // mark after adding to queue
                    neighbor.distance = node.distance+1;
                }
            }
        }
    }

    private void clearVisited() {
        for (GraphNode<T> node : graph.getNodes()) {
            node.visited = false;
            node.distance = 0;
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

    public Set<GraphNode> getConnected() {
        Set<GraphNode> visitedNodes = new HashSet<>();
        for (GraphNode<T> node: graph.getNodes()){
            if (node.visited)
                visitedNodes.add(node);
        }
        return visitedNodes;
    }




}
