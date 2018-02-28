package org.sheehan.algorithm.data_structures.graph;

import org.sheehan.algorithm.data_structures.List;

import java.util.Set;

/**
 * Created by bob on 8/12/14.
 */
public interface Graph<T extends Comparable<T>> {

    // enqueue directed edge weighted
    GraphEdge<T>  addDirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight);

    java.util.List<GraphEdge<T>> addUndirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight);

    boolean isEdge(GraphNode<T> node1, GraphNode<T> node2);

    void printGraph();

    Set<GraphNode<T>> getNodes();

    int getNumV();

    List<GraphNode<T>> getNeighbors(GraphNode<T> node);

    Integer getEdgeWeight(GraphNode<T> node1, GraphNode<T> node2);

    GraphEdge<T> getEdge(GraphNode<T> node1, GraphNode<T> node2);

   // T getNode(int i);

   // int getNodeIndex(T node);

    GraphNode<T> addNode(GraphNode<T> node);

    boolean hasIncomingEdges(GraphNode<T> node);

    boolean removeEdge(GraphEdge<T> edge);

}