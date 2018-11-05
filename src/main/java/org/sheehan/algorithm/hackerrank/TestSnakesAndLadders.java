package org.sheehan.algorithm.hackerrank;

import java.util.*;

public class TestSnakesAndLadders {
    public class BinaryCompleteTree<T extends Comparable<T>> {

        T array[];
        int count;

        public BinaryCompleteTree(int size) {
            array = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, size);
        }

        public void add(T value){
            if (count == array.length)
                resize();
            array[count++] = value;
        }

        protected T[] resize() {
            return Arrays.copyOf(array, array.length * 2);
        }

        public boolean isEmpty(){
            return (count == 0) ? true : false;
        }

        public int parent(int i) {
            if (i == 0)
                return -1;
            return (i-1)/2;
        }

        public int leftChild(int i) {
            return 2*i + 1;
        }

        public int rightChild(int i) {
            return 2*i + 2;
        }

        public void print(int index) {
            if (index >= count)
                return;
            System.out.println(array[index]);
            print(leftChild(index));
            print(rightChild(index));
        }
    }


    /**
     * Created by bob on 7/13/14.
     *
     * A Heap is a specific data structure or implementation of an efficient priority queue, which is simply a type that allows
     * retrieval of elements by priority (min or max).
     *
     * This allows for specification of min or max. As a complete tree we use a fixed size array for heap implementation.
     *
     * implements a dequeue method which provides priority queue functionality
     */
    public enum HeapType {MIN_HEAP, MAX_HEAP};
    public class BinaryHeap <T extends Comparable<T>> extends BinaryCompleteTree<T> {

        final private HeapType heapType;

        public BinaryHeap(int size, HeapType heapType){
            super(size);
            this.heapType = heapType;
        }

        // start with last node and (bubbleUp) swap up recursively. Then iterate reverse back upt he tree.
        public void buildHeap(T array[])
        {
            this.array = array;
            count = array.length;
            for( int i = this.count-1 ; i >= 0; i-- )
                heapify(i);
        }

        //recursive from node index i up to root.
        //bubble up
        protected void heapify(int i)
        {
            int parent = parent(i);

            boolean swap = false;

            if( parent >= 0) {
                if (this.heapType==HeapType.MAX_HEAP && this.array[parent].compareTo(this.array[i]) < 0)
                    swap = true;
                else if (this.heapType==HeapType.MIN_HEAP && this.array[parent].compareTo(this.array[i]) > 0)
                    swap = true;
            }

            if( swap)
            {
                swap(parent, i);
                heapify(parent); //recurse upward toward root
            }
        }

        //iterative from node index i up to root.
        //bubble up
        protected void heapifyIterative(int i)
        {
            int parentIndex = parent(i);

            boolean swap = true;

            while(parentIndex >= 0 && swap) {
                swap = false;
                if (this.heapType==HeapType.MAX_HEAP && this.array[parentIndex].compareTo(this.array[i]) < 0)
                    swap = true;
                else if (this.heapType==HeapType.MIN_HEAP && this.array[parentIndex].compareTo(this.array[i]) > 0)
                    swap = true;

                if (swap) {
                    swap(parentIndex, i);
                    i = parentIndex;
                    parentIndex = parent(parentIndex);
                }
            }
        }

        // for after deleting and swapping top node - establish heap property
        protected void bubbleDown(int i)
        {
            int leftChildIndex = leftChild(i);
            int rightChildIndex = rightChild(i);

            int swap = i;
            if (leftChildIndex < count) {
                if (this.heapType==HeapType.MAX_HEAP && this.array[leftChildIndex].compareTo(this.array[i]) > 0)
                    swap = leftChildIndex;
                else if (this.heapType==HeapType.MIN_HEAP && this.array[leftChildIndex].compareTo(this.array[i]) < 0)
                    swap = leftChildIndex;

                if (this.heapType==HeapType.MAX_HEAP && rightChildIndex < count &&
                        this.array[rightChildIndex].compareTo(this.array[i]) > 0 &&
                        this.array[rightChildIndex].compareTo(this.array[leftChildIndex]) > 0)
                    swap = rightChildIndex;
                else if (this.heapType==HeapType.MIN_HEAP && rightChildIndex < count &&
                        this.array[rightChildIndex].compareTo(this.array[i]) < 0 &&
                        this.array[rightChildIndex].compareTo(this.array[leftChildIndex]) < 0)
                    swap = rightChildIndex;

                if (swap != i) {
                    swap(swap,i);
                    bubbleDown(swap);
                }
            }
        }

        private void swap(int index1, int index2) {
            T tmp = this.array[index1];
            this.array[index1] = this.array[ index2 ];
            this.array[ index2 ] = tmp;
        }

        @Override
        public void add(T value) {
            super.add(value);
            heapify(this.count - 1);
        }

        public T remove() {
            if (count == 0)
                return null;
            T value = this.array[0];
            swap(0, count - 1);
            --count;
            bubbleDown(0);
            return value;
        }
    }


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

        void djikstraPriorityQueue(GraphNode<T> sourceNode);

        Stack<GraphNode<T>> getPath(GraphNode<T> srcNode, GraphNode<T> dstNode);

        void printPath(GraphNode<T> srcNode, GraphNode<T> dstNode);

        void clearEdges(GraphNode<T> node);
    }

    public class GraphList<T extends Comparable<T>> implements Graph<T> {
        private Map<GraphNode<T>, GraphNode<T>> predecessorMap;

        private Map<GraphNode<T>, List<GraphEdge<T>>> graphAdjacencyList;
        private java.util.Set<GraphNode<T>> graphNodes = new HashSet<>();


        public GraphList() {
            graphAdjacencyList = new HashMap<GraphNode<T>, List<GraphEdge<T>>>();
            predecessorMap = new LinkedHashMap<>();
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
        @Override
        public void djikstraPriorityQueue(GraphNode<T> sourceNode) {
            BinaryHeap<GraphNode<T>> minHeap = new BinaryHeap<>(getNumV(), HeapType.MIN_HEAP);

            minHeap.add(sourceNode);

            for (GraphNode<T> node:getNodes()){
                node.distance = Integer.MAX_VALUE;
            }
            sourceNode.distance = 0;

            // calculate shortest distance to each node from source
            while(!minHeap.isEmpty()) {
                // of all unvisited nodes which one has the minimal distance
                GraphNode<T> minDistanceNode = minHeap.remove();
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

        @Override
        public Stack<GraphNode<T>> getPath(GraphNode<T> srcNode, GraphNode<T> dstNode) {
            Stack<GraphNode<T>> path = new Stack<>();
            path.setSize(predecessorMap.size());
            path.push(dstNode);

            while (dstNode != null){
                dstNode = predecessorMap.get(dstNode);
                if (dstNode != null)
                    path.push(dstNode);
            }

            return path;
        }

        public void printPath(GraphNode<T> srcNode, GraphNode<T> dstNode) {
            Stack<GraphNode<T>> path = new Stack<>();
            path.setSize(predecessorMap.size());
            path.push(dstNode);

            while (dstNode != null){
                dstNode = predecessorMap.get(dstNode);
                if (dstNode != null)
                    path.push(dstNode);
            }

            System.out.print("path: ");
            while(path.peek() != null){
                System.out.print(path.pop() + " ");
            }
            System.out.println();
        }

        @Override
        public void clearEdges(GraphNode<T> node) {
            this.graphAdjacencyList.get(node).clear();
        }
    }

    public static void main(String[] args) {

        TestSnakesAndLadders game = new TestSnakesAndLadders();
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();


        for (int i=0; i<t; ++i) {

            java.util.List<GraphNode<Integer>> nodes = new ArrayList<>();
            Graph<Integer> graph =  game.new GraphList<Integer>();
            for (int n = 0; n < 100; n++){
                nodes.add( game.new GraphNode<Integer>(n));
            }

            for (int n = 0; n < 100; n++){
                for (int j = 1; j <= 6; j++)
                    if (n+j < 100)
                        graph.addDirectedEdge(nodes.get(n),nodes.get(n+j), 1);
            }

            int numLadders = in.nextInt();
            for (int j = 0; j < numLadders; ++j) {
                int src = in.nextInt()-1;
                int dst = in.nextInt()-1;

                // dequeue existing edge
                graph.clearEdges(nodes.get(src));
                graph.addDirectedEdge(nodes.get(src), nodes.get(dst), 0);
            }
            int numSnakes = in.nextInt();
            for (int j = 0; j < numSnakes; ++j) {
                int src = in.nextInt()-1;
                int dst = in.nextInt()-1;
                // dequeue existing edge
                graph.clearEdges(nodes.get(src));
                graph.addDirectedEdge(nodes.get(src),nodes.get(dst), 0);
            }

            //graph.printGraph();
            graph.djikstraPriorityQueue(nodes.get(0));
            //graph.printPath(nodes.get(0), nodes.get(99));
            Stack<GraphNode<Integer>> path = graph.getPath(nodes.get(0), nodes.get(99));

            int current = 0;
            int rolls = 0;
            while(path.peek() != null){
                int square = path.pop().payload;
                if (square-current <= 6 && square-current > 0 )
                    rolls++;
                current = square;

            }
            if (rolls == 0)
                System.out.println(-1);
            else
                System.out.println(rolls);

        }


    }
}

//1
//1
//3 90
//7
//99 10
//97 20
//98 30
//96 40
//95 50
//94 60
//93 70

// output -1