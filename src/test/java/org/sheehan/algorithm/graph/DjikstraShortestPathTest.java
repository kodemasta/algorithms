package org.sheehan.algorithm.graph;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.AdjacencyListGraph;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.ArrayList;
import java.util.Iterator;


public class DjikstraShortestPathTest {

    @Test
    public void testShortestPathPQ() {
        Graph graph = AdjacencyListGraph.create(5,7,false);
        graph.printGraph();
        DjikstraShortestPath alg = new DjikstraShortestPath(graph);
        Iterator<GraphNode> iterator = graph.getNodes().iterator();
        GraphNode first =iterator.next();
        GraphNode last = iterator.next();
        while(iterator.hasNext())
            last = iterator.next();

        alg.executePQIterative(first);
        alg.printPath(first, last);

        alg.execute(first);
        alg.printPath(first, last);
    }


}