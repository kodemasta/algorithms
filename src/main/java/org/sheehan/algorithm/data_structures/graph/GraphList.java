package org.sheehan.algorithm.data_structures.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;

import java.util.*;

/**
 * Created by bob on 7/8/14.
 *
 */
public class GraphList<T extends Comparable<T>> implements Graph<T> {

    private Map<GraphNode<T>, List<GraphEdge<T>>> graphAdjacencyList;
    private java.util.Set<GraphNode<T>> graphNodes = new HashSet<>();

    public GraphList() {
        graphAdjacencyList = new HashMap<GraphNode<T>, List<GraphEdge<T>>>();
    }

    @Override
    public GraphNode<T> addNode( GraphNode<T> node) {
        graphNodes.add(node);
        graphAdjacencyList.put(node, new ListImpl<GraphEdge<T>>());

        return node;
    }

    @Override
    // used for toposort
    public boolean hasIncomingEdges(GraphNode<T> candidate) {
        for (GraphNode<T> node : this.getNodes()) {
            if (!node.equals(candidate)){
                List<GraphNode<T>> neighbors = this.getNeighbors(node);
                for (GraphNode<T> neighbor : neighbors) {
                    // if unvisited incoming edge found from node to candidate
                    GraphEdge<T> edge = getEdge(node, candidate);
                    if (neighbor.equals(candidate) /*&& (edge == null || !edge.visited)*/)
                        return true;
                }
            }
        }
        return false;
    }


    public boolean removeEdge(GraphEdge<T> edge){
//        List<GraphEdge<T>> graphEdges = this.graphAdjacencyList.get(edge.dstNode);
//        graphEdges.delete(edge);
//        graphEdges = this.graphAdjacencyList.get(edge.srcNode);
//        graphEdges.delete(edge);

        return true; //todo
    }


    // enqueue directed edge weighted
    @Override
    public GraphEdge<T>  addDirectedEdge( GraphNode<T> node1,  GraphNode<T>node2, int weight) {
        if (!graphNodes.contains(node1))
            addNode(node1);
        if (!graphNodes.contains(node2))
            addNode(node2);
        GraphEdge<T> edge = new GraphEdge<>(node1, node2, weight);

        if (!isEdge(edge.srcNode,edge.dstNode)) {
            graphAdjacencyList.get(node1).appendBack(edge);
        }
        else {
            System.err.println("already an edge: " + node1 + " " + node2 );
        }

        return edge;
    }


    @Override
    public java.util.List<GraphEdge<T>> addUndirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight) {
        if (!graphNodes.contains(node1))
            addNode(node1);
        if (!graphNodes.contains(node2))
            addNode(node2);
        GraphEdge<T> edge1 = new GraphEdge<>(node1, node2, weight);
        GraphEdge<T> edge2 = new GraphEdge<>(node2, node1, weight);
        edge2.id = edge1.id; //undirected edge (2 parallel edges)
        if (!isEdge(edge1.srcNode,edge1.dstNode)) {
            graphAdjacencyList.get(node1).appendBack(edge1);
            }
            else {
                System.err.println("already an edge: " + node1 + " " + node2 );
            }
        graphAdjacencyList.get(node2).appendBack(edge2);

        java.util.List<GraphEdge<T>> edges = new ArrayList<GraphEdge<T>>();
        edges.add(edge1);
        edges.add(edge2);

        return edges;
    }

    public Set<GraphNode<T>> getNodes(){
        return graphNodes;
    }


    @Override
    public boolean isEdge(GraphNode<T> node1, GraphNode<T> node2) {
        List<GraphEdge<T>> edges = graphAdjacencyList.get(node1);
        for (GraphEdge edge : edges){
            if (edge.dstNode.equals(node2))
                return true;
        }

        return false;
    }


    public GraphEdge<T> getEdge(GraphNode<T> node1, GraphNode<T> node2) {
        List<GraphEdge<T>> edges = graphAdjacencyList.get(node1);
        for (GraphEdge edge : edges){
            if (edge.dstNode.equals(node2))
                return edge;
        }

        return null;
    }

    @Override
    public void printGraph() {
        System.out.println("vertices: " + this.graphNodes.size() );
        for (GraphNode<T> node : this.graphNodes){
            List<GraphEdge<T>> graphEdges = graphAdjacencyList.get(node);
            if (graphEdges.size() > 0 ) {
                System.out.print(node.toString() + " -> ");
                for (GraphEdge edge : graphEdges) {
                    System.out.print(edge + " ");
                }
                System.out.println();
            }
        }
    }

    @Override
    public int getNumV() {
        return this.graphNodes.size();
    }

    @Override
    public List<GraphNode<T>> getNeighbors(GraphNode<T> node) {
        List<GraphNode<T>> neighbors = new ListImpl<>();
        List<GraphEdge<T>> edges = graphAdjacencyList.get(node);
        if (edges != null) {
            for (GraphEdge<T> edge : edges)
                neighbors.appendBack(edge.dstNode);
        }
        return neighbors;

    }

    @Override
    public Integer getEdgeWeight(GraphNode<T> node1, GraphNode<T> node2) {
        if (isEdge(node1, node2)){
            return getEdge(node1, node2).weight;
        }
        return Integer.MAX_VALUE; // no edge
    }

}
