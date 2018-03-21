package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.AdjacencyListGraph;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphEdge;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by bob on 8/15/14.
 */

// TODO implement with UNION-FIND to optimise and replace DFS cycle check
public class KruskalMinSpanningTree <T extends Comparable<T>> {

    private PriorityQueue<GraphEdge> queue = new PriorityQueue<>();
    Graph mstGraph = new AdjacencyListGraph();


    public KruskalMinSpanningTree(Graph graph) {
        // build up minheap of all edges
        Set<GraphEdge> usedEdges = new HashSet<>();
        for (GraphNode node : graph.getNodes()) {
            List<GraphNode> neighbors = graph.getNeighbors(node);
            for (GraphNode neighbor: neighbors) {
                GraphEdge edge = graph.getEdge(node, neighbor);
                if (!usedEdges.contains(edge)) {
                    usedEdges.add(edge);
                    queue.add(edge);
                }
            }
        }
    }

    // uses DFS searches to find cycles - not as optimal as union-find
    public void executeDfs(){

        while (queue.peek() != null){
            GraphEdge edge = queue.remove();
            //check for cycles of dst node
            if (!checkCyclesDfs(edge, mstGraph)) {
                mstGraph.addUndirectedEdge(edge.srcNode, edge.dstNode, edge.weight);
                //mstEdges.enqueue(edge);
            }


            //System.out.println("edge:" + edge.srcNode + " " + edge.dstNode + "(" + edge.weight + ")");
        }
    }

    // use BFS searches to search if cycle.
    // search for both nodes of edge and see if the other edge end node is found
    private boolean checkCyclesDfs(GraphEdge edge, Graph mstGraph) {
        BFS bfs = new BFS(mstGraph);
        bfs.visitIterative(edge.srcNode);
        Set<GraphNode> connected = bfs.getConnected();

        if (connected.contains(edge.dstNode))
            return true;

        return false;
    }

    public void printPath() {
        Set<GraphEdge> usedEdges = new HashSet<>();
        for (GraphNode node : mstGraph.getNodes()) {
            List<GraphNode> neighbors = mstGraph.getNeighbors(node);
            for (GraphNode neighbor: neighbors) {
                GraphEdge edge = mstGraph.getEdge(node, neighbor);
                if (!usedEdges.contains(edge)) {
                    usedEdges.add(edge);
                    queue.add(edge);
                }
            }
        }

        while (queue.size() > 0)
            System.out.println(queue.remove());
    }


}
