package org.bsheehan.data_structure.graph;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.algorithm.DFS;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest extends BaseTest {

    @Test
    public void test() {
        Graph g = new Graph();
        g.addNode(0,"a");
        g.addNode(1,"b");
        g.addNode(2,"c");
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,0,1);
        g.addEdge(0,2,1);
        g.addEdge(1,1,1);
        g.addEdge(2,0,1);

        g.print();

        DFS.visit(g,0);
    }

}