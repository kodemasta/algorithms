package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.AdjacencyListGraph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;

public class BFSTest {

    @Test
    public void testVisit() throws Exception {
        Graph graph = AdjacencyListGraph.create(5,7,false);
        graph.printGraph();

        BFS bfs = new BFS(graph);
        System.out.println("source:" +graph.getNodes().iterator().next());
        bfs.visitIterative(graph.getNodes().iterator().next());
        bfs.printConnected();
    }
}