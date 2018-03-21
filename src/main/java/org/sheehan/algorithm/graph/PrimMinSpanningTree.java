package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphEdge;
import org.sheehan.algorithm.data_structures.graph.GraphNode;
import org.sheehan.algorithm.data_structures.tree.BinaryHeap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by bob on 7/8/14.
 */
public class PrimMinSpanningTree <T extends Comparable<T>> {
    private final Graph graph;
    private Boolean visited[];
    private Integer distance[];
    private Integer predecessor[];

    //for Prim
    private Set<GraphEdge> minSpanningEdges;

    public PrimMinSpanningTree(Graph graph){
        this.graph = graph;
        visited = new Boolean[graph.getNumV()];
        distance = new Integer[graph.getNumV()];
        predecessor = new Integer[graph.getNumV()];

        minSpanningEdges = new LinkedHashSet<>();
        for (int i=0; i < graph.getNumV(); ++i){
            visited[i] = false;
        }
    }

    // optimized with PQ
    public void executePQ(GraphNode currentNode) {
        //BinaryHeap<GraphNode> minHeap = new BinaryHeap<>(graph.getNumV(), BinaryHeap.HeapType.MIN_HEAP);

        //minHeap.enqueue(currentNode);

        //for (GraphNode node:graph.getNodes()){
        //    node.distance = IntegerAlgs.MAX_VALUE;
        //}

        // execute enqueue of all unvisited nodes
        Set<GraphNode> unvisited = new HashSet<>();
        unvisited.addAll(graph.getNodes());

        //currentNode.distance = 0;
        //minSpanningNodes.enqueue(currentNode);
        //currentNode.visited = true;

        // take out the current node
        unvisited.remove(currentNode);
        currentNode.visited = true;

        // calculate shortest distance to each node from source
        while(unvisited.size() > 0) {
            // for each currenty visited node find cheapest edge using PQ and enqueue to span
            BinaryHeap<GraphEdge> minEdgeHeap = new BinaryHeap<>(graph.getNumV(), BinaryHeap.HeapType.MIN_HEAP);
            List<GraphNode> neighborNodes = this.graph.getNeighbors(currentNode);
            for (GraphNode neighborNode : neighborNodes) {
                minEdgeHeap.add(graph.getEdge(currentNode, neighborNode));
            }

            // find min unvisited edge
            GraphEdge minEdge = minEdgeHeap.pop();
            while (minEdge != null && !unvisited.contains(minEdge.dstNode))
                minEdge = minEdgeHeap.pop();

            if (minEdge != null) {
                // Adding unvisited min edge to MST
                minSpanningEdges.add(minEdge);

                currentNode = minEdge.dstNode;
                unvisited.remove(currentNode);
                 currentNode.visited = true;
            } else {
                //ok no more min edges unvisited along current route. Find another unvisited node
                Iterator<GraphNode> iterator = unvisited.iterator();
                GraphNode unvisitedNode = iterator.next();
                List<GraphNode> neighbors = graph.getNeighbors(unvisitedNode);
                for (GraphNode neighbor:neighbors)
                    if (!unvisited.contains(neighbor))
                        currentNode = neighbor;
            }
        }
    }

    public void printPath() {

        System.out.println("MST: ");
        for (GraphEdge edge : minSpanningEdges){
            System.out.println(edge);
        }

        System.out.println();
    }

    // how about use PQ instead !
    private int getMinDistanceNodeIndex(Boolean[] visited, Integer[] distance) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceIndex = -1; // -1 if not found.
        for (int i = 0; i < distance.length; ++i) {
            if (!visited[i] && distance[i] < minDistance){
                minDistanceIndex = i;
                minDistance = distance[i];
            }
        }

        return minDistanceIndex;
    }
}
