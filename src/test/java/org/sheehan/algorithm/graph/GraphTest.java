package org.sheehan.algorithm.graph;

import junit.framework.TestCase;
import org.junit.Test;
import org.sheehan.algorithm.data_structures.graph.AdjacencyListGraph;

public class GraphTest extends TestCase {

    @Test
    public void testCreate() {
//        AdjacencyListGraph.create(5,5, false).printGraph();
//        AdjacencyListGraph.create(5,5, true).printGraph();
//        AdjacencyListGraph.create(5,10, false).printGraph();
//        AdjacencyListGraph.create(5,10, true).printGraph();
        AdjacencyListGraph.create(5,10, false).printGraph();
        AdjacencyListGraph.create(5,20, true).printGraph();
        AdjacencyListGraph.create(20,40, true).printGraph();

    }
}
