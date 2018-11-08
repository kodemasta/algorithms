package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;
import org.sheehan.algorithm.data_structures.stack.Stack;
import org.sheehan.algorithm.data_structures.stack.StackArrayImpl;
import org.sheehan.algorithm.data_structures.tree.BinaryHeap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bob on 7/8/14.
 */
public class DjikstraShortestPath {
    private final Graph graph;
    private Map<GraphNode, GraphNode> predecessorMap;

    public DjikstraShortestPath(Graph graph){
        this.graph = graph;
        predecessorMap = new LinkedHashMap<>();
    }

    // not optimized with PQ
    public void execute(GraphNode sourceNode) {
        for (GraphNode node:graph.getNodes()){
            node.distance = Integer.MAX_VALUE;
            node.visited = false;

        }
        sourceNode.distance = 0;

        // calculate shortest distance to each node from source
        for (int i = 0; i < graph.getNumV(); ++i) {
            // of all unvisited nodes which one has the minimal distance
            GraphNode minDistanceNode = getMinDistanceNode();
            // enqueue this to visited
            minDistanceNode.visited = true;
            // starting at this node look at all neighbors and update distance cost and predecessor
            // if improved.
            List<GraphNode> neighborNodes = this.graph.getNeighbors(minDistanceNode);
            for (GraphNode neighborNode : neighborNodes) {
                // if whatever the neighbor had as a distance is improved by connecting from this new node and edge
                // then update the neighbor of this new node with better distance
                int newEdgeDistance = this.graph.getEdgeWeight(minDistanceNode, neighborNode);
                int newTotalDistanceFromSource = minDistanceNode.distance + newEdgeDistance;
                if (neighborNode.distance > newTotalDistanceFromSource){
                    neighborNode.distance = newTotalDistanceFromSource;
                    predecessorMap.put(neighborNode, minDistanceNode);
                }
            }
        }
    }

    // optimized with PQ
    // edges have weights. nodes have mutable field for accumulating total weight or distance.
    public void executePQIterative(GraphNode sourceNode) {
        BinaryHeap<GraphNode> minHeap = new BinaryHeap<>(graph.getNumV(), BinaryHeap.HeapType.MIN_HEAP);

        // ADD SOURCE TO MIN HEAP
        minHeap.add(sourceNode);

        // INIT ALL NODES TO MAX DISTANCE
        for (GraphNode node:graph.getNodes()){
            node.distance = Integer.MAX_VALUE;
        }
        sourceNode.distance = 0;

        // calculate shortest distance to each node from source
        while(!minHeap.isEmpty()) {
            // of all unvisited nodes which one has the minimal distance
            GraphNode currMinNode = minHeap.pop();
            currMinNode.visited = true;
            // starting at this node look at all neighbors and update distance cost and predecessor
            // if improved.
            List<GraphNode> neighborNodes = this.graph.getNeighbors(currMinNode);
            for (GraphNode neighborNode : neighborNodes) {
                // if whatever the neighbor had as a distance is improved by connecting from this new node and edge
                // then update the neighbor of this new node with better distance
                int newTotalDistanceFromSource = currMinNode.distance + this.graph.getEdgeWeight(currMinNode, neighborNode);
                if (neighborNode.distance > newTotalDistanceFromSource){
                    neighborNode.distance = newTotalDistanceFromSource;
                    predecessorMap.put(neighborNode,currMinNode);
                    minHeap.add(neighborNode);
                }
            }
        }
    }

    // to print the path push onto LIFO queue then pop and print
    public void printPath(GraphNode srcNode, GraphNode dstNode) {
        Stack<GraphNode> path = new StackArrayImpl<>(predecessorMap.size());
        path.push(dstNode);

        while (dstNode != null){
            dstNode = predecessorMap.get(dstNode);
            if (dstNode != null)
                path.push(dstNode);
        }

        System.out.print("path: ");
        while(path.peek() != null){
            System.out.print(path.pop() + " ");
        }
        System.out.println();
    }

    public Stack<GraphNode> getPath(GraphNode srcNode, GraphNode dstNode) {
        Stack<GraphNode> path = new StackArrayImpl<>(predecessorMap.size());
        path.push(dstNode);

        while (dstNode != null){
            dstNode = predecessorMap.get(dstNode);
            if (dstNode != null)
                path.push(dstNode);
        }

        return path;
    }

    // how about use PQ instead !
    private GraphNode getMinDistanceNode() {
        int minDistance = Integer.MAX_VALUE;
        GraphNode minDistanceNode = null; // -1 if not found.
        for (GraphNode node : graph.getNodes()) {
            if (!node.visited && node.distance < minDistance){
                minDistanceNode = node;
                minDistance = minDistanceNode.distance;
            }
        }

        return minDistanceNode;
    }
}
