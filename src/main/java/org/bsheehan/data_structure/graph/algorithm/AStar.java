package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.data_structure.graph.Graph;

public class AStar {
    public static void find(Graph.Node graph[][], int srcId, int dstId){


    }

    public static void print(Graph.Node graph[][]){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j].toString());
            }
            System.out.println();
        }
    }
}
