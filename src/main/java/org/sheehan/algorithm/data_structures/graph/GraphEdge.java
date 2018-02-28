package org.sheehan.algorithm.data_structures.graph;


public class GraphEdge<T extends Comparable<T>> implements Comparable<GraphEdge<T>> {
    static public int idCnt = 0;
    public int id;
    public GraphNode<T> dstNode;
    public GraphNode<T> srcNode; //optional - not needed for adjacency list impl below
    public Integer weight = 1;
    public boolean visited; // used for topo sort

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