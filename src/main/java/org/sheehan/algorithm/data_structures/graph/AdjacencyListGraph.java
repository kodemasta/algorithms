package org.sheehan.algorithm.data_structures.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.Node;

import java.util.*;

/**
 * Created by bob on 7/8/14.
 *
 */
public class AdjacencyListGraph implements Graph {

    private Map<GraphNode, List<GraphEdge>> graphAdjacencyList;
    private java.util.Set<GraphNode> graphNodes = new HashSet<>();

    public static Graph create(int numV, int numE, boolean directed) {
        Node.idCnt = 0;
        GraphEdge.idCnt = 0;
        if (directed && numE > numV*(numV-1))
            return null;
        if (!directed && numE > numV*(numV-1)/2)
            return null;

        Graph graph = new AdjacencyListGraph();
        for (int v = 0; v < numV; ++v)
            graph.addNode(new GraphNode());

        Set<GraphNode> nodes = graph.getNodes();
        for (int e = 0; e < numE; ++e){
            if (directed && graph.getNumE() == numV*(numV-1))
                return graph;
            else if (!directed && graph.getNumE() == numV*(numV-1))
                return graph;
            boolean unique = false;
            while(!unique){
                GraphNode node1 = (GraphNode)nodes.toArray()[new Random().nextInt(nodes.size())];
                GraphNode node2 = (GraphNode)nodes.toArray()[new Random().nextInt(nodes.size())];
                if (node1 != node2){
                    if (!directed) {
                        if (graph.addUndirectedEdge(node1, node2, new Random().nextInt(10)) != null)
                            unique = true;
                    }
                    else {
                        if (graph.addDirectedEdge(node1, node2, new Random().nextInt(10)) != null)
                            unique = true;
                    }
                }
            }
        }

        return graph;
    }

    public AdjacencyListGraph() {
        graphAdjacencyList = new HashMap<>();
    }

    @Override
    public GraphNode addNode(GraphNode node) {
        graphNodes.add(node);
        graphAdjacencyList.put(node, new ListImpl<>());

        return node;
    }

    @Override
    // used for toposort
    public boolean hasIncomingEdges(GraphNode targetNode) {
        for (GraphNode node : this.getNodes()) {
            if (!node.equals(targetNode)){
                List<GraphNode> neighbors = this.getNeighbors(node);
                for (GraphNode neighbor : neighbors) {
                    // if unvisited incoming edge found from node to candidate
                    //GraphEdge edge = getEdge(node, candidate);
                    if (neighbor.equals(targetNode) /*&& (edge == null || !edge.visited)*/)
                        return true;
                }
            }
        }
        return false;
    }


    public boolean removeEdge(GraphEdge edge){
//        List<GraphEdge> graphEdges = this.graphAdjacencyList.get(edge.dstNode);
//        graphEdges.delete(edge);
//        graphEdges = this.graphAdjacencyList.get(edge.srcNode);
//        graphEdges.delete(edge);

        return true; //todo
    }


    // enqueue directed edge weighted
    @Override
    public GraphEdge  addDirectedEdge( GraphNode node1,  GraphNode node2, int weight) {
        if (!graphNodes.contains(node1)) {
            System.err.println("no node added for: " + node1);
            return null;
        }
        if (!graphNodes.contains(node2)) {
            System.err.println("no node added for: " + node2);
            return null;
        }
        if (isEdge(node1, node2)) {
            System.err.println("already edge for:" + node1 + " " + node2);
            return null;
        }
        GraphEdge edge1 = new GraphEdge(node1, node2, weight);

        graphAdjacencyList.get(node1).appendBack(edge1);

        return edge1;

    }


    @Override
    public java.util.List<GraphEdge> addUndirectedEdge(GraphNode node1, GraphNode node2, int weight) {
        GraphEdge edge1 = addDirectedEdge(node1, node2, weight);
        GraphEdge edge2 = addDirectedEdge(node2, node1, weight);
        if (edge1==null || edge2==null)
            return null;
        edge1.id = edge2.id;
        java.util.List<GraphEdge> edges = new ArrayList<>();
        edges.add(edge1);
        edges.add(edge2);

        return edges;
    }

    public Set<GraphNode> getNodes(){
        return graphNodes;
    }


    @Override
    public boolean isEdge(GraphNode node1, GraphNode node2) {
        List<GraphEdge> edges = graphAdjacencyList.get(node1);
        for (GraphEdge edge : edges){
            if (edge.dstNode.equals(node2))
                return true;
        }

        return false;
    }


    public GraphEdge getEdge(GraphNode node1, GraphNode node2) {
        List<GraphEdge> edges = graphAdjacencyList.get(node1);
        for (GraphEdge edge : edges){
            if (edge.dstNode.equals(node2))
                return edge;
        }

        return null;
    }

    @Override
    public void printGraph() {
        System.out.println("vertices: " + this.graphNodes.size() );
        for (GraphNode node : this.graphNodes){
            List<GraphEdge> graphEdges = graphAdjacencyList.get(node);
            System.out.print(node.id+ " -> ");
            for (GraphEdge edge : graphEdges) {
                System.out.print(edge + " ");
            }
            System.out.println();

        }
    }

    @Override
    public int getNumV() {
        return this.graphNodes.size();
    }

    @Override
    public int getNumE() {
        int num = 0;
        for (GraphNode node:this.graphNodes) {
            List<GraphEdge> edges = graphAdjacencyList.get(node);
            num += edges.size();
        }
        return num;
    }

    @Override
    public List<GraphNode> getNeighbors(GraphNode node) {
        List<GraphNode> neighbors = new ListImpl<>();
        List<GraphEdge> edges = graphAdjacencyList.get(node);
        if (edges != null) {
            for (GraphEdge edge : edges)
                neighbors.appendBack(edge.dstNode);
        }
        return neighbors;

    }

    @Override
    public Integer getEdgeWeight(GraphNode node1, GraphNode node2) {
        if (isEdge(node1, node2)){
            return getEdge(node1, node2).weight;
        }
        return Integer.MAX_VALUE; // no edge
    }

}
