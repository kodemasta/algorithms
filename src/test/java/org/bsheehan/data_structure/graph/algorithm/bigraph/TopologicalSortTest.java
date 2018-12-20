package org.bsheehan.data_structure.graph.algorithm.bigraph;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.bsheehan.data_structure.stack.ListStack;
import org.junit.Test;

public class TopologicalSortTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        Graph g = new Graph();
        g.addNode(0, "a");
        g.addNode(1, "b");
        g.addNode(2, "c");
        g.addNode(3, "d");
        g.addNode(4, "e");
        g.addNode(5, "f");
        g.addUndirectedEdge(0, 1, 1);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(2, 3, 1);
        g.addUndirectedEdge(2, 4, 1);
        g.addUndirectedEdge(1, 5, 1);
        g.addUndirectedEdge(1, 4, 1);
        g.addUndirectedEdge(4, 5, 1);
        g.print();
        ListStack stack = TopologicalSort.sort(g);

        System.out.print("top sort:");
        while(stack.size() > 0)
            System.out.print(stack.pop() + " ");
        System.out.println();

    }

}