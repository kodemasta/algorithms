package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.data_structure.graph.Graph;

public class ConnectedComponents {

    static int componentId = 0;

    public static int find(Graph g){
        for (Graph.Node n: g.getNodes()){
            if (!n.visited) {
                DFS.visit(g, n.id, componentId++);
            }
        }
        return componentId;
    }
}
