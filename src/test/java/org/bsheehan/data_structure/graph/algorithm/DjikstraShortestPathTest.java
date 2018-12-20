package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DjikstraShortestPathTest extends BaseTest {
    Graph g = new Graph();

    @Before
    public void setup() {
        super.test();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addNode(3, "d");
        g.addNode(4, "e");
        g.addNode(5, "f");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(2, 3, 6);
        g.addUndirectedEdge(3, 4, 1);
        g.addUndirectedEdge(4, 5, 1);
        g.addUndirectedEdge(0, 5, 5);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(2, 5, 1);
        g.print();
    }

    @Test
    public void testShortestPathTreeBasic() {

        DjikstraShortestPath.findShortestPathTreeBasic(g, 0);
        List<Integer> path2 = DjikstraShortestPath.getShortestPath(g, 5);
        System.out.println("shortest path from 0-5:" + path2.toString());
    }

    @Test
    public void testShortestPathBasic() {
        List<Integer> path2 = DjikstraShortestPath.findShortestPathBasic(g, 0, 5);
        System.out.println("shortest path from 0-5:" + path2.toString());
    }

    @Test
    public void testShortestPathTreePQ() {
        DjikstraShortestPath.findShortestPathTreePQ(g, 0);
        List<Integer> path2 = DjikstraShortestPath.getShortestPath(g, 5);
        System.out.println("shortest path from 0-5:" + path2.toString());
    }

    @Test
    public void testSingleShortestPathPQ() {
        List<Integer> shortestPath = DjikstraShortestPath.findShortestPathPQ(g, 0, 5);
        System.out.println("shortest path from 0-5:" + shortestPath.toString());

    }


}
