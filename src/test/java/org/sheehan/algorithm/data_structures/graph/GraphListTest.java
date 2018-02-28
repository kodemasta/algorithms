package org.sheehan.algorithm.data_structures.graph;

import org.junit.Test;

import java.util.ArrayList;

public class GraphListTest {

    @Test
    public void testAddEdge() throws Exception {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();
        graph.addUndirectedEdge(nodes.get(0), nodes.get(1), 5);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(2), 10);
        graph.addUndirectedEdge(nodes.get(1), nodes.get(3), 6);
        graph.addUndirectedEdge(nodes.get(1), nodes.get(4), 3);
        graph.addUndirectedEdge(nodes.get(3), nodes.get(5), 6);
        graph.addUndirectedEdge(nodes.get(4), nodes.get(3), 2);
        graph.addUndirectedEdge(nodes.get(4), nodes.get(6), 2);
        graph.addUndirectedEdge(nodes.get(6), nodes.get(5), 2);
        graph.printGraph();
    }

    @Test
    public void testAddDirectedWeightedEdge() throws Exception {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();
        graph.addDirectedEdge(nodes.get(0), nodes.get(1), 5);
        graph.addDirectedEdge(nodes.get(0), nodes.get(2), 10);
        graph.addDirectedEdge(nodes.get(1), nodes.get(3), 6);
        graph.addDirectedEdge(nodes.get(1), nodes.get(4), 3);
        graph.addDirectedEdge(nodes.get(3), nodes.get(5), 6);
        graph.addDirectedEdge(nodes.get(4), nodes.get(3), 2);
        graph.addDirectedEdge(nodes.get(4), nodes.get(6), 2);
        graph.addDirectedEdge(nodes.get(6), nodes.get(5), 2);
        graph.printGraph();
    }
}