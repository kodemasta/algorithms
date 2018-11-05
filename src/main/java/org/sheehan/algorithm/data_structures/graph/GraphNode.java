package org.sheehan.algorithm.data_structures.graph;


import org.sheehan.algorithm.data_structures.Node;

public class GraphNode extends Node implements Comparable<GraphNode>  {

    public boolean visited;
    public int distance = 0; // bfs shortest path, and djikstra requires this
    public GraphNode parent = null; // for tracking shortest path BFS

    public GraphNode()
    {
        super();
        this.visited = false;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof GraphNode))
                return false;

        GraphNode node = (GraphNode) obj;
        return this.id == node.id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }

    @Override
    public int compareTo(GraphNode node) {
        return this.id.compareTo(node.id);
    }

    @Override
    public String toString(){
        return "(HashNode:" + id + " distance:" + distance + " visited:" + visited +")";
    }
}
