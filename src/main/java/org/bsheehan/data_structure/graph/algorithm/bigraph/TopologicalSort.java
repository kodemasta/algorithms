package org.bsheehan.data_structure.graph.algorithm.bigraph;

import org.bsheehan.data_structure.graph.Graph;
import org.bsheehan.data_structure.stack.ListStack;

// NO CYCLES ALLOWED !
// DFS based + stack for precedence of nodes
public class TopologicalSort {

    public static ListStack sort(Graph graph) {

        ListStack stack = new ListStack();

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (Graph.Node node: graph.getNodes())
            if (node.visited == false)
                topologicalSortDFS(graph, node, stack);

        return stack;
    }

    private static void topologicalSortDFS(Graph graph, Graph.Node node, ListStack stack) {
        node.visited = true;
        for (Graph.Node n: graph.getNeighbors(node)) {
            if (node.visited == false){
                topologicalSortDFS(graph, n, stack);
            }
        }
        stack.push(node);
    }

}
