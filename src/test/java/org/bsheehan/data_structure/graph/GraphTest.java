package org.bsheehan.data_structure.graph;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.algorithm.DFS;
import org.junit.Test;

public class GraphTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        Graph g = new Graph();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(2, 0, 1);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(1, 1, 1);
        g.addUndirectedEdge(2, 0, 1);

        g.print();

        DFS.visit(g, 0);
    }

    @Test
    public void testGrid() {
        Graph grid = Graph.createGrid(4);
        grid.print();

    }

    @Test
    public void testEulerian() {
        Graph grid = Graph.createGrid(4);
        grid.print();
        int res = grid.isEulerianUndirected();
        if (res == 0)
            System.out.println("adjList is not Eulerian");
        else if (res == 1)
            System.out.println("adjList has a Euler path");
        else
            System.out.println("adjList has a Euler cycle");

    }

    @Test
    public void testEulerian2() {
        Graph g = new Graph();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addNode(3, "d");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(0, 3, 1);
        g.addUndirectedEdge(0, 4, 1);
        g.addUndirectedEdge(0, 4, 1);

        g.print();
        int res = g.isEulerianUndirected();
        if (res == 0)
            System.out.println("adjList is not Eulerian");
        else if (res == 1)
            System.out.println("adjList has a Euler path");
        else
            System.out.println("adjList has a Euler cycle");

    }


}