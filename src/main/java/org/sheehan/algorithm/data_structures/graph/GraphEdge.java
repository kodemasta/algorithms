package org.sheehan.algorithm.data_structures.graph;


public class GraphEdge implements Comparable<GraphEdge> {
    static public int idCnt = 0;
    public int id;
    public GraphNode dstNode;
    public GraphNode srcNode; //optional - not needed for adjacency list impl below
    public Integer weight = 1;
    public boolean visited; // used for topo sort

    public GraphEdge(GraphNode node1, GraphNode node2, Integer weight)
    {
        this.srcNode = node1;
        this.dstNode = node2;
        this.weight = weight;
        this.id = idCnt++;
    }

    @Override
    public int compareTo(GraphEdge edge) {
        return this.weight.compareTo(edge.weight);
    }

    @Override
    public boolean equals(Object obj){
        GraphEdge edge = (GraphEdge) obj;
        return (this.srcNode.id == edge.srcNode.id ) && (this.dstNode.id == edge.dstNode.id);
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "[" + +srcNode.id+"-"+dstNode.id + " (" + this.weight +")"+"]";
    }
}