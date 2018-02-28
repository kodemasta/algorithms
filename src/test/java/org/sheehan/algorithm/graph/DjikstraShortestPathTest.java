package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphList;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;


public class DjikstraShortestPathTest {

    @Test
    public void testShortestPathPQ() {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();

        graph.addUndirectedEdge(nodes.get(0),nodes.get(1), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(2), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(3), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(4), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(1), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(3), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(4), 1);
        graph.addUndirectedEdge(nodes.get(1), nodes.get(5), 1);
        DjikstraShortestPath alg = new DjikstraShortestPath(graph);
        alg.executePQIterative(nodes.get(0));
        alg.printPath(nodes.get(0), nodes.get(5));
    }

    @Test
    public void testShortestPath2() {
        java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            nodes.add(new GraphNode<Integer>(i));
        }

        Graph<Integer> graph = new GraphList<Integer>();

        graph.addUndirectedEdge(nodes.get(0),nodes.get(1), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(2), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(3), 10);
        graph.addUndirectedEdge(nodes.get(0), nodes.get(4), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(1), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(3), 1);
        graph.addUndirectedEdge(nodes.get(2), nodes.get(4), 1);
        graph.addUndirectedEdge(nodes.get(1), nodes.get(5), 1);
        DjikstraShortestPath alg = new DjikstraShortestPath(graph);
        alg.execute(nodes.get(0));
        alg.printPath(nodes.get(0), nodes.get(5));
    }
/*
    @TestGrow
    public void testShortestPathPQ2() {
        IntegerAlgs nodes[] = {0,1,2,3,4,9};
        Graph graph = new GraphMatrix(nodes);
        graph.addUndirectedEdge(nodes[0], nodes[1], 10);
        graph.addUndirectedEdge(nodes[0], nodes[2], 1);
        graph.addUndirectedEdge(nodes[0], nodes[3], 1);
        graph.addUndirectedEdge(nodes[0], nodes[4], 10);
        graph.addUndirectedEdge(nodes[2], nodes[1], 1);
        graph.addUndirectedEdge(nodes[2], nodes[3], 1);
        graph.addUndirectedEdge(nodes[2], nodes[4], 1);
        graph.addUndirectedEdge(nodes[1], nodes[5], 10);
        graph.addUndirectedEdge(nodes[4], nodes[5], 1);
        DjikstraShortestPath alg = new DjikstraShortestPath(graph);
        alg.execute2(0);
        alg.printPath(0, 5);
    }

*/
}