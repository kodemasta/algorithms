package org.sheehan.algorithm.hackerrank;


import org.sheehan.algorithm.data_structures.tree.BinaryHeap;

import java.util.*;

public class TestKingdomGrowth {
    static private int idCnt = 0;
    public class GraphNode<T extends Comparable<T>> implements Comparable<GraphNode<T>>  {
        public int id;
        public T payload;
        public boolean visited;
        public int distance; // djikstra requires this

        public GraphNode(T payload)
        {
            this.payload = payload;
            this.visited = false;
            this.id = idCnt++;
        }

        @Override
        public boolean equals(Object obj){
            GraphNode<T> node = (GraphNode<T>) obj;
            return id == node.id;
        }

        @Override
        public int hashCode(){
            return id;
        }

        @Override
        public int compareTo(GraphNode<T> node) {
            return this.payload.compareTo(node.payload);
        }

        @Override
        public String toString(){
            return "(HashNode:" + payload + " " + visited +")";
        }
    }

    public class GraphEdge<T extends Comparable<T>> implements Comparable<GraphEdge<T>> {
        public int id;
        public GraphNode<T> dstNode;
        public GraphNode<T> srcNode; //optional - not needed for adjacency list impl below
        public Integer weight = 1;
        public boolean visited; // used for topo sortBucket

        public GraphEdge(GraphNode<T> node1, GraphNode<T> node2, Integer weight)
        {
            this.srcNode = node1;
            this.dstNode = node2;
            this.weight = weight;
            this.id = idCnt++;
        }

        @Override
        public int compareTo(GraphEdge<T> edge) {
            return this.weight.compareTo(edge.weight);
        }

        @Override
        public boolean equals(Object obj){
            GraphEdge<T> edge = (GraphEdge<T>) obj;
            return id == edge.id;
        }

        @Override
        public int hashCode(){
            return id;
        }

        @Override
        public String toString(){
            return "[" + srcNode.toString()+"-"+dstNode.toString() + " edge:" + weight + " visited:" + visited + "]";
        }
    }

    public interface Graph<T extends Comparable<T>> {

        // enqueue directed edge weighted
        GraphEdge<T> addDirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight);

        java.util.List<GraphEdge<T>> addUndirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight);

        boolean isEdge(GraphNode<T> node1, GraphNode<T> node2);

        void printGraph();

        Set<GraphNode<T>> getNodes();

        int getNumV();

        List<GraphNode<T>> getNeighbors(GraphNode<T> node);

        Integer getEdgeWeight(GraphNode<T> node1, GraphNode<T> node2);

        GraphEdge<T> getEdge(GraphNode<T> node1,GraphNode<T> node2);

        GraphNode<T> addNode(GraphNode<T> node);

        boolean hasIncomingEdges(GraphNode<T> node);
    }

    public class GraphList<T extends Comparable<T>> implements Graph<T> {
        private Map<GraphNode<T>, GraphNode<T>> predecessorMap;

        private Map<GraphNode<T>, List<GraphEdge<T>>> graphAdjacencyList;
        private java.util.Set<GraphNode<T>> graphNodes = new HashSet<>();


        public GraphList() {
            graphAdjacencyList = new HashMap<GraphNode<T>, List<GraphEdge<T>>>();
        }

        @Override
        public GraphNode<T> addNode( GraphNode<T> node) {
            graphNodes.add(node);
            graphAdjacencyList.put(node, new ArrayList<GraphEdge<T>>());

            return node;
        }

        @Override
        public boolean hasIncomingEdges(GraphNode<T> candidate) {
            for (GraphNode<T> node : this.getNodes()) {
                if (!node.equals(candidate)){
                    List<GraphNode<T>> neighbors = this.getNeighbors(node);
                    for (GraphNode<T> neighbor : neighbors) {
                        // if unvisited incoming edge found from node to candidate
                        GraphEdge<T> edge = getEdge(node, candidate);
                        if (neighbor.equals(candidate) && (edge == null || !edge.visited))
                            return true;
                    }
                }
            }
            return false;
        }

        // enqueue directed edge weighted
        @Override
        public GraphEdge<T> addDirectedEdge( GraphNode<T> node1,  GraphNode<T> node2, int weight) {
            if (!graphNodes.contains(node1))
                addNode(node1);
            if (!graphNodes.contains(node2))
                addNode(node2);
            GraphEdge<T> edge = new GraphEdge<>(node1, node2, weight);

            if (!isEdge(edge.srcNode,edge.dstNode)) {
                graphAdjacencyList.get(node1).add(edge);
            }
            else {
                System.err.println("already an edge: " + node1 + " " + node2 );
            }

            return edge;
        }

        @Override
        public java.util.List<GraphEdge<T>> addUndirectedEdge(GraphNode<T> node1, GraphNode<T> node2, int weight) {
            if (!graphNodes.contains(node1))
                addNode(node1);
            if (!graphNodes.contains(node2))
                addNode(node2);
            GraphEdge<T> edge1 = new GraphEdge<>(node1, node2, weight);
            GraphEdge<T> edge2 = new GraphEdge<>(node2, node1, weight);
            edge2.id = edge1.id; //undirected edge (2 parallel edges)
            if (!isEdge(edge1.srcNode,edge1.dstNode)) {
                graphAdjacencyList.get(node1).add(edge1);
            }
            else {
                System.err.println("already an edge: " + node1 + " " + node2 );
            }
            graphAdjacencyList.get(node2).add(edge2);

            java.util.List<GraphEdge<T>> edges = new ArrayList<GraphEdge<T>>();
            edges.add(edge1);
            edges.add(edge2);

            return edges;
        }

        public Set<GraphNode<T>> getNodes(){
            return graphNodes;
        }

        @Override
        public boolean isEdge(GraphNode<T> node1, GraphNode<T> node2) {
            List<GraphEdge<T>> edges = graphAdjacencyList.get(node1);
            for (GraphEdge edge : edges){
                if (edge.dstNode.equals(node2))
                    return true;
            }
            return false;
        }

        public GraphEdge<T> getEdge(GraphNode<T> node1, GraphNode<T> node2) {
            List<GraphEdge<T>> edges = graphAdjacencyList.get(node1);
            for (GraphEdge edge : edges){
                if (edge.dstNode.equals(node2))
                    return edge;
            }

            return null;
        }

        @Override
        public void printGraph() {
            System.out.println("vertices: " + this.graphNodes.size() );
            for (GraphNode<T> node : this.graphNodes){
                List<GraphEdge<T>> graphEdges = graphAdjacencyList.get(node);
                if (graphEdges.size() > 0 ) {
                    System.out.print(node.toString() + " -> ");
                    for (GraphEdge edge : graphEdges) {
                        System.out.print(edge + " ");
                    }
                    System.out.println();
                }
            }
        }

        @Override
        public int getNumV() {
            return this.graphNodes.size();
        }

        @Override
        public List<GraphNode<T>> getNeighbors(GraphNode<T> node) {
            List<GraphNode<T>> neighbors = new ArrayList<>();
            List<GraphEdge<T>> edges = graphAdjacencyList.get(node);
            if (edges != null) {
                for (GraphEdge<T> edge : edges)
                    neighbors.add(edge.dstNode);
            }
            return neighbors;

        }

        @Override
        public Integer getEdgeWeight(GraphNode<T> node1, GraphNode<T> node2) {
            if (isEdge(node1, node2)){
                return getEdge(node1, node2).weight;
            }
            return Integer.MAX_VALUE; // no edge
        }

        // djikstr's shortest path alg ptimized with PQ
        public void djikstraPriorityQueue(GraphNode<T> sourceNode) {
            BinaryHeap<GraphNode<T>> minHeap = new BinaryHeap<>(getNumV(), BinaryHeap.HeapType.MIN_HEAP);

            minHeap.add(sourceNode);

            for (GraphNode<T> node:getNodes()){
                node.distance = Integer.MAX_VALUE;
            }
            sourceNode.distance = 0;

            // calculate shortest distance to each node from source
            while(!minHeap.isEmpty()) {
                // of all unvisited nodes which one has the minimal distance
                GraphNode<T> minDistanceNode = minHeap.pop();
                minDistanceNode.visited = true;
                // starting at this node look at all neighbors and update distance cost and predecessor
                // if improved.
               List<GraphNode<T>> neighborNodes = this.getNeighbors(minDistanceNode);
                for (GraphNode<T> neighborNode : neighborNodes) {
                    // if whatever the neighbor had as a distance is improved by connecting from this new node and edge
                    // then update the neighbor of this new node with better distance
                    int newEdgeDistance = this.getEdgeWeight(minDistanceNode, neighborNode);
                    int newTotalDistanceFromSource = minDistanceNode.distance + newEdgeDistance;
                    if (neighborNode.distance > newTotalDistanceFromSource){
                        neighborNode.distance = newTotalDistanceFromSource;
                        predecessorMap.put(neighborNode,minDistanceNode);
                        minHeap.add(neighborNode);
                    }
                }
            }
        }

        // how about use PQ instead !
        // call djikstraPriorityQueue first
        private GraphNode<T> getMinDistanceNode() {
            int minDistance = Integer.MAX_VALUE;
            GraphNode<T> minDistanceNode = null; // -1 if not found.
            for (GraphNode<T> node : getNodes()) {
                if (!node.visited && node.distance < minDistance){
                    minDistanceNode = node;
                    minDistance = minDistanceNode.distance;
                }
            }

            return minDistanceNode;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

    }
}