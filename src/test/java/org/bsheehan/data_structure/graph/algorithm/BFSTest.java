package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.data_structure.graph.Graph;
import org.junit.Test;

import java.util.List;

public class BFSTest {
    @Test
    public void test() {
        Graph g = new Graph();
        g.addNode(0,"a");
        g.addNode(1,"b");
        g.addNode(2,"c");
        g.addNode(3,"d");
        g.addNode(4,"e");
        g.addNode(5,"f");
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,0,1);
        g.addEdge(0,2,1);
        g.addEdge(1,1,1);
        g.addEdge(1,4,1);
        g.addEdge(4,5,1);
        g.print();
        BFS.visit(g, 0);

        List<Integer> path = DFS.getPath(g, 2);
        System.out.println(path.toString());

        List<Integer> path2 = DFS.getPath(g, 5);
        System.out.println(path2.toString());
    }

    @Test
    public void testMst() {
        Graph g = new Graph();
        g.addNode(0,"a");
        g.addNode(1,"b");
        g.addNode(2,"c");
        g.addNode(3,"d");
        g.addNode(4,"e");
        g.addNode(5,"f");
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,0,1);
        g.addEdge(0,2,1);
        g.addEdge(1,1,1);
        g.addEdge(1,4,1);
        g.addEdge(4,5,1);
        g.addEdge(5,2,1);
        g.addEdge(5,3,1);
        g.print();

        Graph mst = new Graph();

        BFS.mst(g, 0, mst);

        mst.print();
    }
}