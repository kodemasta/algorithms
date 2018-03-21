package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.AdjacencyListGraph;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;

public class DFSTest {

    @Test
    public void testVisitRecurse() throws Exception {
        Graph graph = AdjacencyListGraph.create(5,7,false);
        graph.printGraph();

        DFS bfs = new DFS(graph);
        System.out.println("source:" +graph.getNodes().iterator().next());
        bfs.visitRecursion(graph.getNodes().iterator().next());
        bfs.printConnected();
    }

    @Test
    public void testVisitIter() throws Exception {
        Graph graph = AdjacencyListGraph.create(5,7,false);
        graph.printGraph();

        DFS bfs = new DFS(graph);
        System.out.println("source:" +graph.getNodes().iterator().next());
        bfs.visitIterative(graph.getNodes().iterator().next());
        bfs.printConnected();
    }

}
