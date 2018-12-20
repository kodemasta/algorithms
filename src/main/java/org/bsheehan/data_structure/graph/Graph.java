package org.bsheehan.data_structure.graph;

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

        public int componentId = 0; // connected component id

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
    private Map<Integer, List<Edge>> adjList;

    public Graph() {
        this.nodes = new ArrayList<Node>();
        this.adjList = new HashMap<Integer,List<Edge>>();
    }

    public void addNode(Integer id, String value) {
        for (Node n: this.nodes){
            if (n.id == id)
                return; //duplicate
        }
        Node node = new Node(id, value);
        this.nodes.add(node);
    }

    public void addDirectedEdge(Integer srcId, Integer dstId, Integer weight) {
        if (this.getNode(srcId) == null) {
            this.addNode(srcId, null);
        }
        List<Edge> edges = this.adjList.get(srcId);
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
            this.adjList.put(srcId, edges);
        }
    }

    public void addUndirectedEdge(Integer srcId, Integer dstId, Integer weight) {
        addDirectedEdge(srcId, dstId, weight);
        addDirectedEdge(dstId, srcId, weight);
    }




    public Edge getEdge(Integer srcId, Integer dstId){
        List<Edge> edges = this.adjList.get(srcId);
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
        List<Edge> edges = this.adjList.get(src.id);
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
        this.adjList.clear();
    }

    public void print() {
        for (Node node: this.nodes) {
            System.out.print(node.id + "->");
            List<Edge> edges = this.adjList.get(node.id);

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
                    g.addUndirectedEdge(col + row * dim, col - 1 + (row * dim), 1);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col - 1 + (row * dim), col - 1, row);
                    g.addUndirectedEdge(col + row * dim, col + 1 + (row * dim), 1);
                    g.setHeuristicData(col + 1 + (row * dim), col + 1, row);

                } else if (col == 0) {
                    g.addUndirectedEdge(col + row * dim, col + 1 + (row * dim), 1);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + 1 + (row * dim), col + 1, row);
                } else if (col == dim-1) {
                    g.addUndirectedEdge(col + row * dim, col - 1 +(row * dim), 1);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col - 1 + (row * dim), col - 1, row);
                }

                // above and below
                if (row > 0 && row < dim-1) {
                    g.addUndirectedEdge(col + row * dim, col + ((row-1) * dim), 1);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + ((row-1) * dim), col, row - 1);
                    g.addUndirectedEdge(col + row * dim, col + ((row+1) * dim), 1);
                    g.setHeuristicData(col + ((row+1) * dim), col, row + 1);
                } else if (row == 0) {
                    g.addUndirectedEdge(col + row * dim, col + ((row+1) * dim), 1);
                    g.setHeuristicData(col + row * dim, col, row);
                    g.setHeuristicData(col + ((row+1) * dim), col, row + 1);
                } else if (row == dim-1) {
                    g.addUndirectedEdge(col + row * dim, col + ((row-1) * dim), 1);
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

    public boolean isConnected() {
        for (Node n: getNodes()) {
            List<Edge> edges = this.adjList.get(n.id);
            if (edges.size() > 0)
                return true;
        }

        return false;
    }

    /* The function returns one of the following values
     0 --> not Eulerian
     1 --> If adjList has an Euler path (Semi-Eulerian)
     2 --> If adjList has an Euler Circuit (Eulerian)  ( all connected nodes are even degree */
    public int isEulerianUndirected()
    {
        // Check if all non-zero degree vertices are connected
        if (isConnected() == false)
            return 0;

        // Count vertices with odd degree
        int odd = 0;
        for (Node n: this.nodes)
            if (this.adjList.get(n) != null && (this.adjList.get(n).size()%2) == 1)
                odd++;

        // If count is more than 2, then adjList is not Eulerian
        if (odd > 2)
            return 0;

        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected adjList
        return (odd==2)? 1 : 2;
    }
}
