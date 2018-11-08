package org.bsheehan.data_structure.graph;

import java.util.*;

public class Graph {

    ///////////////////////////////////////////////////////////////
    public class Node  {
        public Integer id;
        public String value;
        public boolean visited;
        public int parent;

        public Node(Integer k, String v){
            this.id = k;
            this.value = v;
            visited = false;
            parent = -1;
        }

        public void clear(){
            this.id = -1;
            this.value = "";
            visited = false;
            parent = -1;
        }

        public String toString() {
            return id.toString() + ":" + value.toString();
        }
    }

    ///////////////////////////////////////////////////////////////
    public class Edge {
        Integer dstId;
        int weight;

        public Edge(Integer dstId, int weight){
            this.dstId = dstId;
            this.weight = weight;
        }

        public String toString() {
            return dstId + "(" + weight + ")";
        }
    }

    ///////////////////////////////////////////////////////////////
    private List<Node> nodes;
    private Map<Integer, List<Edge>> graph;

    public Graph() {
        this.nodes = new ArrayList<Node>();
        this.graph = new HashMap<Integer,List<Edge>>();
    }

    public void addNode(Integer id, String value) {
        for (Node n: this.nodes){
            if (n.id == id)
                return; //duplicate
        }
        Node node = new Node(id, value);
        this.nodes.add(node);
    }

    public void addEdge(Integer srcId, Integer dstId, Integer weight) {
        List<Edge> edges = this.graph.get(srcId);
        if (edges != null) {
            for (Edge e : edges) {
                if (e.dstId.equals(dstId))
                    return; // duplicate
            }
            edges.add(new Edge(dstId, weight));
        } else {
            edges = new ArrayList<Edge>();
            edges.add(new Edge(dstId, weight));
            this.graph.put(srcId, edges);
        }
    }

    public List<Node> getNeighbors(Node src) {
        List<Node> neighbors = new ArrayList<Node>();
        List<Edge> edges = this.graph.get(src.id);
        if (edges != null) {
            for (Edge e : edges) {
                neighbors.add(this.nodes.get(e.dstId));
            }
        }
        return neighbors;
    }

    public Node getNode(Integer id) {
        return this.nodes.get(id);
    }

    public void clear() {
        this.nodes.clear();
        this.graph.clear();
    }

    public void print() {
        for (Node node: this.nodes) {
            System.out.print(node.id + "->");
            List<Edge> edges = this.graph.get(node.id);

            if (edges != null) {
                for (Edge e : edges) {
                    System.out.print(e.toString() + " ");
                }
             }
            System.out.println();
        }
    }
}
