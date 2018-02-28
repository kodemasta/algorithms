package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphList;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;

public class PrimMinSpanningTreeTest {
    @Test
    public void testShortestPathPQ() {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();

        graph.addUndirectedEdge(nodes.get(0),nodes.get(1), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(2), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(3), 1);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(4), 10);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(1), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(3), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(4), 1);
        graph.addUndirectedEdge(nodes.get(1), nodes.get(5), 1);
        PrimMinSpanningTree alg = new PrimMinSpanningTree(graph);
        alg.executePQ(nodes.get(0));
        alg.printPath();
    }
}