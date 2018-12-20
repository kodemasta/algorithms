package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.junit.Test;

public class ConnectedComponentsTest extends BaseTest {

    @Test
    public void test() {
        Graph g = new Graph();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addNode(3, "c");
        g.addNode(4, "d");
        g.addNode(5, "e");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(2, 0, 1);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(1, 1, 1);
        g.addUndirectedEdge(2, 0, 1);
        g.addUndirectedEdge(1, 4, 1);
        g.addUndirectedEdge(4, 5, 1);
        g.print();
        ConnectedComponents.find(g);

        for (Graph.Node n: g.getNodes()){
            System.out.println(n.id + ":" + n.componentId);
        }
    }


    @Test
    public void test2() {
        Graph g = new Graph();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addNode(3, "c");
        g.addNode(4, "d");
        g.addNode(5, "e");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(2, 0, 1);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(1, 1, 1);
        g.addUndirectedEdge(2, 0, 1);
        g.addUndirectedEdge(4, 5, 1);
        g.print();
        System.out.println("# components: " + ConnectedComponents.find(g));

        for (Graph.Node n: g.getNodes()){
            System.out.println(n.id + ":" + n.componentId);
        }
    }
}