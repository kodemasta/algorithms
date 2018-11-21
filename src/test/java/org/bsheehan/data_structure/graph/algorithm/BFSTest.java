package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.junit.Test;

import java.util.List;

public class BFSTest extends BaseTest{
    @Test
    public void test() {
        super.test();
        Graph g = new Graph();
        g.addNode(0,"a");
        g.addNode(1,"b");
        g.addNode(2,"c");
        g.addNode(3,"d");
        g.addNode(4,"e");
        g.addNode(5,"f");
        g.addEdge(0,1,1, true);
        g.addEdge(1,2,1, true);
        g.addEdge(2,0,1, true);
        g.addEdge(0,2,1, true);
        g.addEdge(1,1,1, true);
        g.addEdge(1,4,1, true);
        g.addEdge(4,5,1, true);
        g.print();
        BFS.visit(g, 0);

        List<Integer> path = DFS.getPath(g, 2);
        System.out.println("path to 2:" + path.toString());

        List<Integer> path2 = DFS.getPath(g, 5);
        System.out.println("path to 5:" + path2.toString());
    }

    @Test
    public void testMst() {
        super.test();
        Graph g = new Graph();
        g.addNode(0,"a");
        g.addNode(1,"b");
        g.addNode(2,"c");
        g.addNode(3,"d");
        g.addNode(4,"e");
        g.addNode(5,"f");
        g.addEdge(0,1,1, true);
        g.addEdge(1,2,1, true);
        g.addEdge(2,0,1, true);
        g.addEdge(0,2,1, true);
        g.addEdge(1,1,1, true);
        g.addEdge(1,4,1, true);
        g.addEdge(4,5,1, true);
        g.addEdge(5,2,1, true);
        g.addEdge(5,3,1, true);
        g.print();

        Graph mst = new Graph();

        BFS.mst(g, 0, mst);

        mst.print();
    }
}