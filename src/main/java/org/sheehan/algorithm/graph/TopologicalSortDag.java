package org.sheehan.algorithm.graph;

import org.sheehan.algorithm.data_structures.List;
import org.sheehan.algorithm.data_structures.ListImpl;
import org.sheehan.algorithm.data_structures.graph.Graph;
import org.sheehan.algorithm.data_structures.graph.GraphEdge;
import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by bob on 9/2/14.
 */
public class TopologicalSortDag<T extends Comparable<T>> {

    // Kahn's algorithm
    public List<GraphNode> topologicalSort(Graph graph) {
        List<GraphNode> sortedList = new ListImpl<>();

        // these have outgoing edges only !
        Set<GraphNode> noIncomingEdgeNodes = new HashSet<>(graph.getNumV());

        for (GraphNode node : graph.getNodes()) {
            System.out.println("check incoming: " + node);
            if (!graph.hasIncomingEdges(node))
                noIncomingEdgeNodes.add(node);
        }

        while (!noIncomingEdgeNodes.isEmpty()) {
            // remove node from noIncomingEdgeNodes
            // add to tail of sortedList
            Iterator<GraphNode> iterator = noIncomingEdgeNodes.iterator();
            GraphNode noIncomingEdgesNode = iterator.next();
            iterator.remove();
            sortedList.appendBack(noIncomingEdgesNode);
            for (GraphNode neighbor : graph.getNeighbors(noIncomingEdgesNode)) {
                GraphEdge edge = graph.getEdge(noIncomingEdgesNode, neighbor);
                graph.removeEdge(edge);

                // only add to set if the node has had all incoming edges removed
                if (!graph.hasIncomingEdges(neighbor)) {
                    noIncomingEdgeNodes.add(neighbor);
                }
            }
        }

        return sortedList;
    }
}
