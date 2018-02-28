package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphList;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;

public class TopologicalSortDagTest {

    @Test
    public void testTopologicalSort() throws Exception {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();
        graph.addDirectedEdge(nodes.get(0), nodes.get(1), 1);
        graph.addDirectedEdge(nodes.get(0), nodes.get(2), 1);
        graph.addDirectedEdge(nodes.get(1), nodes.get(3), 1);
        graph.addDirectedEdge(nodes.get(1), nodes.get(4), 1);
        graph.addDirectedEdge(nodes.get(3), nodes.get(5), 1);
        graph.addDirectedEdge(nodes.get(4), nodes.get(3), 1);
        graph.addDirectedEdge(nodes.get(4), nodes.get(6), 1);
        graph.addDirectedEdge(nodes.get(6), nodes.get(5), 1);
        graph.printGraph();

        TopologicalSortDag<Integer> algorithm = new TopologicalSortDag<>();
        List<GraphNode<Integer>> sortedList = algorithm.topologicalSort(graph);
        System.out.println();
        sortedList.print();
    }
}