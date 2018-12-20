package org.bsheehan.data_structure.graph.algorithm;


import org.bsheehan.data_structure.graph.Graph;
import org.bsheehan.data_structure.stack.ListStack;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static void visit(Graph g, Integer nodeId){
        Graph.Node node = g.getNode(nodeId);
        node.visited = true;
        /////////////////////////////
        doSomething(node);
        /////////////////////////////
        for(Graph.Node neighbor: g.getNeighbors(node)){
            if (!neighbor.visited) {
                neighbor.parent = nodeId;
                visit(g, neighbor.id);
            }
        }
    }

    public static void visit(Graph g, Integer nodeId, int componentId){
        Graph.Node node = g.getNode(nodeId);
        node.componentId = componentId;
        visit(g, nodeId);
    }

    public static void mst(Graph g, Integer nodeId, Graph mst)
    {
        Graph.Node node = g.getNode(nodeId);
        node.visited = true;
        /////////////////////////////
        doSomething(node);
        /////////////////////////////
        for(Graph.Node neighbor: g.getNeighbors(node)){
            if (!neighbor.visited) {

                neighbor.parent = nodeId;

                // add edge to mst
                mst.addNode(node.id, node.value);
                mst.addNode(neighbor.id, neighbor.value);
                mst.addUndirectedEdge(node.id, neighbor.id, 1);

                mst(g, neighbor.id, mst);
            }
        }
    }

    private static void doSomething(Graph.Node neighbor) {
        System.out.println("visited: " + neighbor.toString());
    }

    public static boolean inPath(Graph g, Integer nodeId) {
        return g.getNode(nodeId).visited;
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
