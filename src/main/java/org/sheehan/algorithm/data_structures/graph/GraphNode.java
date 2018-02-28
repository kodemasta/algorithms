package org.sheehan.algorithm.data_structures.graph;


public class GraphNode<T extends Comparable<T>> implements Comparable<GraphNode<T>>  {
    static private int idCnt = 0;
    public int id;
    public T payload;
    public boolean visited;
    public int distance = 0; // bfs shortest path, and djikstra requires this

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
        return "(Node:" + payload + " " + distance + " " + visited +")";
    }
}
