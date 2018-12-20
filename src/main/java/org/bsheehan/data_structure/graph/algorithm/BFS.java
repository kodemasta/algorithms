package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.data_structure.graph.Graph;
import org.bsheehan.data_structure.queue.ListQueue;
import org.bsheehan.data_structure.stack.ListStack;

import java.util.ArrayList;
import java.util.List;

// In an unweighted graph, the shortest path is the path with least number of edges.
// With Breadth First, we always reach a vertex from given source using the minimum number of edges.
 public class BFS {

    public static void visit(Graph g, Integer nodeId){
        ListQueue<Graph.Node> q = new ListQueue<>();
        Graph.Node node = g.getNode(nodeId);
        q.enqueue(node);
        node.visited = true;
        while((node = q.dequeue()) != null) {
            /////////////////////////////
            doSomething(node);
            /////////////////////////////
            for (Graph.Node neighbor : g.getNeighbors(node)) {
                if (!neighbor.visited ) {
                    q.enqueue(neighbor);
                    neighbor.visited = true;
                    neighbor.parent = node.id;
                }
            }
        }
    }

    public static void mst(Graph g, Integer nodeId, Graph mst){
        ListQueue<Graph.Node> q = new ListQueue<>();
        Graph.Node node = g.getNode(nodeId);
        q.enqueue(node);
        node.visited = true;
        while((node = q.dequeue()) != null) {
            /////////////////////////////
            doSomething(node);
            /////////////////////////////
            for (Graph.Node neighbor : g.getNeighbors(node)) {
                if (!neighbor.visited ) {
                    q.enqueue(neighbor);
                    neighbor.visited = true;
                    neighbor.parent = node.id;

                    mst.addNode(node.id, node.value);
                    mst.addNode(neighbor.id, neighbor.value);
                    mst.addDirectedEdge(node.id, neighbor.id, 1);


                }
            }
        }
    }

    private static void doSomething(Graph.Node neighbor) {
        System.out.println("visited: " + neighbor.toString());
    }

    public static List<Integer> getPath(Graph g, Integer nodeId) {
        ListStack<Integer> s = new ListStack<Integer>();
        Graph.Node node = g.getNode(nodeId);
        s.push(nodeId);
        while (node.parent != -1){
            s.push(node.parent);
            node = g.getNode(node.parent);
        }

        List<Integer> path = new ArrayList<Integer>();
        Integer id;
        while((id = s.pop()) != null){
            path.add(id);
        }
        return path;
    }

}
