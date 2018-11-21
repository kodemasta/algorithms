package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.junit.Test;

import static org.junit.Assert.*;

public class AStarTest extends BaseTest

    {
        @Test
        public void test() {
            super.test();

            Graph.Node graph[][] = new Graph.Node[8][8];
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    graph[i][j] = new Graph.Node(j + i * graph[i].length, "");
                }
            }

            graph[1][3] = new Graph.Node(3 + 5 * 3, "X");
            graph[2][3] = new Graph.Node(3 + 5 * 3, "X");
            graph[3][3] = new Graph.Node(3 + 3 * 3, "X");
            graph[4][3] = new Graph.Node(3 + 4 * 3, "X");
            graph[5][3] = new Graph.Node(3 + 5 * 3, "X");
            graph[6][3] = new Graph.Node(3 + 5 * 3, "X");

            graph[3][0] = new Graph.Node(3 + 5 * 3, "S");
            graph[3][7] = new Graph.Node(3 + 5 * 3, "D");

            AStar.print(graph);
            AStar.find(graph, graph[3][0].id, graph[3][7].id);
        }
}