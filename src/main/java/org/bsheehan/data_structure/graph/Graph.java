package org.bsheehan.data_structure.graph;

import org.sheehan.algorithm.data_structures.graph.GraphNode;

import java.util.*;

public class Graph {

    ///////////////////////////////////////////////////////////////
    static public class Node implements Comparable<Node> {
        public Integer id;
        public String value;
        public boolean visited;
        public int parent;

        // shortest path weight
        public Integer distFromParent = 1000; // bfs shortest path, and djikstra requires this

        public int[] heuristicData; // for A* algorithm


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
            if (!visited){
                return String.format("%4sX", id.toString());
            }
            //if (parent != -1) {
                return String.format("%4s (%s)", id.toString(), parent);
           // }

//            if (value != null)
//                return String.format("%4s%2s %s", "  ", id.toString(), value.toString());
//            if (parent != -1)
//                return String.format("%4s%2s (%s)", "  ", id.toString(), parent);
            //return String.format("%4s", id.toString());
        }

        @Override
        public int compareTo(Node n) {
            return distFromParent.compareTo(n.distFromParent);
        }
    }

    ///////////////////////////////////////////////////////////////
    // an edge is defined for a source node passed into API
    public class Edge {
        Integer dstId;
        public int weight;

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

    public void addEdge(Integer srcId, Integer dstId, Integer weight, boolean addReverseEdge) {
        if (this.getNode(srcId) == null) {
            this.addNode(srcId, null);
        }
        // forward
        List<Edge> edges = this.graph.get(srcId);
        boolean exists = false;
        if (edges != null) {
            for (Edge e : edges) {
                if (e.dstId.equals(dstId))
                    exists = true;
            }
            if (!exists)
                edges.add(new Edge(dstId, weight));
        } else {
            edges = new ArrayList<Edge>();
            edges.add(new Edge(dstId, weight));
            this.graph.put(srcId, edges);
        }

        if (addReverseEdge) {
            if (this.getNode(dstId) == null) {
                this.addNode(dstId, null);
            }
            edges = this.graph.get(dstId);
            exists = false;
            if (edges != null) {
                for (Edge e : edges) {
                    if (e.dstId.equals(srcId))
                        exists = true;
                }
                if (!exists)
                    edges.add(new Edge(srcId, weight));
            } else {
                edges = new ArrayList<Edge>();
                edges.add(new Edge(srcId, weight));
                this.graph.put(dstId, edges);
            }
        }
    }



    public Edge getEdge(Integer srcId, Integer dstId){
        List<Edge> edges = this.graph.get(srcId);
        if (edges != null) {
            for (Edge e : edges) {
                if (e.dstId.equals(dstId))
                    return e;
            }
        }
        return null;
    }

    public List<Node> getNeighbors(Node src) {
        List<Node> neighbors = new ArrayList<Node>();
        List<Edge> edges = this.graph.get(src.id);
        if (edges != null) {
            for (Edge e : edges) {
                neighbors.add(this.getNode(e.dstId));
            }
        }
        return neighbors;
    }

    public Node getNode(Integer id) {
        for (Node n: this.nodes){
            if (n.id == id)
                return n;
        }
        return null;
    }

    public List<Node> getNodes() {
        return this.nodes;
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

    public void printGrid(int dim){
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                System.out.print(this.getNode(col + row * dim).toString());
            }
            System.out.println();
        }
    }

    public static Graph createGrid(int dim) {
        Graph g = new Graph();
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                // right and left
                if (col > 0 && col < dim-1) {
                    g.addEdge(col + row * dim, col - 1 + (row * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col - 1 + (row * dim), col - 1, row);
                    g.addEdge(col + row * dim, col + 1 + (row * dim), 1, true);
                    g.setHeuristicData(col + 1 + (row * dim), col + 1, row);

                } else if (col == 0) {
                    g.addEdge(col + row * dim, col + 1 + (row * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + 1 + (row * dim), col + 1, row);
                } else if (col == dim-1) {
                    g.addEdge(col + row * dim, col - 1 +(row * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col - 1 + (row * dim), col - 1, row);
                }

                // above and below
                if (row > 0 && row < dim-1) {
                    g.addEdge(col + row * dim, col + ((row-1) * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + ((row-1) * dim), col, row - 1);
                    g.addEdge(col + row * dim, col + ((row+1) * dim), 1, true);
                    g.setHeuristicData(col + ((row+1) * dim), col, row + 1);
                } else if (row == 0) {
                    g.addEdge(col + row * dim, col + ((row+1) * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + ((row+1) * dim), col, row + 1);
                } else if (row == dim-1) {
                    g.addEdge(col + row * dim, col + ((row-1) * dim), 1, true);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + ((row-1) * dim), col, row - 1);
                }
            }
        }
        return g;
    }


    private void setHeuristicData(int nodeId, int ...args) {
        this.getNode(nodeId).heuristicData = args;
    }

    public int getHeuristicEstimate(Integer srcId, Integer dstId) {
        int  srcHeuristicData[] = this.getNode(srcId).heuristicData;
        int  dstHeuristicData[] = this.getNode(dstId).heuristicData;

        int deltaX = Math.abs(srcHeuristicData[0] - dstHeuristicData[0]);
        int deltaY = Math.abs(srcHeuristicData[1] - dstHeuristicData[1]);
        return deltaX + deltaY;

    }
}
