package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphList;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;

public class DFSTest {

    @Test
    public void testVisitRecurse() throws Exception {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 8; i++){
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
        graph.addNode(nodes.get(7));

        DFS dfs = new DFS(graph);
        dfs.visitRecursion(nodes.get(0));
        dfs.printConnected();


    }

    @Test
    public void testVisitIter() throws Exception {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 8; i++){
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
        graph.addNode(nodes.get(7));

        DFS dfs = new DFS(graph);
        dfs.visitIterative(nodes.get(0));
        dfs.printConnected();

    }

}
