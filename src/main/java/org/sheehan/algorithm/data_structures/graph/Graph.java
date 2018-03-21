package org.sheehan.algorithm.data_structures.graph;

import org.sheehan.algorithm.data_structures.List;

import java.util.Set;

/**
 * Created by bob on 8/12/14.
 */
public interface Graph {

    // enqueue directed edge weighted
    GraphEdge  addDirectedEdge(GraphNode node1, GraphNode node2, int weight);

    java.util.List<GraphEdge> addUndirectedEdge(GraphNode node1, GraphNode node2, int weight);

    boolean isEdge(GraphNode node1, GraphNode node2);

    void printGraph();

    Set<GraphNode> getNodes();

    int getNumV();
    int getNumE();

    List<GraphNode> getNeighbors(GraphNode node);

    Integer getEdgeWeight(GraphNode node1, GraphNode node2);

    GraphEdge getEdge(GraphNode node1, GraphNode node2);

   // T getNode(int i);

   // int getNodeIndex(T node);

    GraphNode addNode(GraphNode node);

    boolean hasIncomingEdges(GraphNode node);

    boolean removeEdge(GraphEdge edge);

}