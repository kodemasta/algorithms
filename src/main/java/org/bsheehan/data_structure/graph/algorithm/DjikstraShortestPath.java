package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.data_structure.array.Heap;
import org.bsheehan.data_structure.graph.Graph;
import org.bsheehan.data_structure.queue.ListQueue;
import org.bsheehan.data_structure.stack.ListStack;
import org.bsheehan.data_structure.tree.Tree;

import java.util.ArrayList;
import java.util.List;

//  O(E + VLogV)
// Similar to Prim's algorithm for finding MST
// Fixes a single node as the "source" node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree.
// It can also be used for finding the shortest paths from a single node to a single destination node by stopping the algorithm once the shortest path to the destination node has been determined.
// For a general weighted graph (+- edge weights), we can calculate single source shortest distances in O(VE) time using Bellman–Ford Algorithm.
// For a DAG, topo sort then O(E + E) !
public class DjikstraShortestPath {

    // build up shortest path tree info
    // with PQ optimization no need to add all nodes to Q
    public static void findShortestPathTreeBasic(Graph g, Integer srcId){
        ListQueue<Graph.Node> q = new ListQueue();

        // add ALL Nodes (weights set to 'INFINITE'
        for (Graph.Node n: g.getNodes())
            q.enqueue(n);

        // init source
        Graph.Node sourceNode = g.getNode(srcId);
        sourceNode.distFromParent = 0;

        // iterate over ALL nodes
        while(q.size() != 0) {
            Graph.Node currNode = q.dequeue();
            // check if curr visited
            if (!currNode.visited) {
                currNode.visited = true; // completed now is part of shortest path
                // currNode can be marked for shortest path complete
                for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                    // check if neighbor visited
                    if (!neighbor.visited) {
                        int totalDist = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                        if (totalDist < neighbor.distFromParent) {
                            neighbor.distFromParent = totalDist;
                            neighbor.parent = currNode.id;
                            q.enqueue(neighbor);
                        }
                    }
                }
            }
        }
    }

    public static List<Integer> findShortestPathBasic(Graph g, Integer srcId, Integer dstId){
        ListQueue<Graph.Node> q = new ListQueue();

        // add ALL Nodes (weights set to 'INFINITE'
        for (Graph.Node n: g.getNodes())
            q.enqueue(n);

        // init source
        Graph.Node sourceNode = g.getNode(srcId);
        sourceNode.distFromParent = 0;

        // iterate over ALL nodes
        while(q.size() != 0) {
            Graph.Node currNode = q.dequeue();
            // check if curr visited
            if (!currNode.visited) {
                currNode.visited = true; // completed now is part of shortest path
                if (currNode.id == dstId)
                    break;
                // currNode can be marked for shortest path complete
                for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                    // check if neighbor visited
                    if (!neighbor.visited) {
                        int totalDist = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                        if (totalDist < neighbor.distFromParent) {
                            neighbor.distFromParent = totalDist;
                            neighbor.parent = currNode.id;
                            q.enqueue(neighbor);
                        }
                    }
                }
            }
        }

        return getShortestPath(g, dstId);
    }

    // build up shortest path tree info
    // with PQ optimization no need to add all nodes to Q
    public static void findShortestPathTreePQ(Graph g, Integer srcId){
        Heap<Graph.Node> heap = Heap.createHeap(g.getNodes().toArray(new Graph.Node[0]), Heap.HeapType.MIN_HEAP);

        // graph weights set to 'INFINITE'
        Graph.Node sourceNode = g.getNode(srcId);
        // add ONLY source node
        heap.add(sourceNode);
        sourceNode.distFromParent = 0;

        Graph.Node currNode;
        while((currNode = heap.remove()) != null) {
            if (!currNode.visited) {
                currNode.visited = true; // pulling from queue ==> visited or optimal path found already

                // currNode can be marked for shortest path complete
                for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                    int totalDist = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                    if (totalDist < neighbor.distFromParent) {
                        neighbor.distFromParent = totalDist;
                        neighbor.parent = currNode.id;
                        heap.add(neighbor);
                    }
                }
            } else {
                System.out.println("visited:" + currNode.id);
            }
        }
    }

    // must first run findShortestPathTree !
    public static List<Integer> getShortestPath(Graph g, Integer dstId) {
        ListStack<Integer> stack = new ListStack<Integer>();
        Graph.Node node = g.getNode(dstId);
        stack.push(dstId);
        while (node.parent != -1){
            stack.push(node.parent);
            node = g.getNode(node.parent);
        }

        List<Integer> path = new ArrayList<Integer>();
        Integer id;
        while((id = stack.pop()) != null) {
            path.add(id);
        }
        return path;
    }

    // only care about a single shortest path
    // with PQ optimization no need to add all nodes to Q
    public static List<Integer> findShortestPathPQ(Graph g, Integer srcId, Integer dstId){
        Heap<Graph.Node> heap = Heap.createHeap(new Graph.Node[0], Heap.HeapType.MIN_HEAP);
        Graph.Node sourceNode = g.getNode(srcId);
        heap.add(sourceNode);
        sourceNode.distFromParent = 0;

        Graph.Node currNode;
        while((currNode = heap.remove()) != null) {
                currNode.visited = true; // completed now is part of shortest path

                for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                    int totalDist = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                    if (totalDist < neighbor.distFromParent) {
                        neighbor.distFromParent = totalDist;
                        neighbor.parent = currNode.id;
                        heap.add(neighbor);
                    }
                }

        }

        return getShortestPath(g, dstId);
    }

    public static List<Integer> findShortestPathAStarPQ(Graph g, Integer srcId, Integer dstId){
        Heap<Graph.Node> heap = Heap.createHeap(new Graph.Node[0], Heap.HeapType.MIN_HEAP);
        Graph.Node sourceNode = g.getNode(srcId);
        heap.add(sourceNode);
        sourceNode.distFromParent = g.getHeuristicEstimate(srcId, dstId);

        Graph.Node currNode;
        while((currNode = heap.remove()) != null) {
           currNode.visited = true; // completed now is part of shortest path
            if (currNode.id == dstId)
                break;
            for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                int totalDistFromSrc = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                int estimatedHeuristicToDst = g.getHeuristicEstimate(neighbor.id, dstId);
                if (totalDistFromSrc + estimatedHeuristicToDst < neighbor.distFromParent) {
                    neighbor.distFromParent = totalDistFromSrc + estimatedHeuristicToDst;
                    neighbor.parent = currNode.id;
                    heap.add(neighbor);
                }
            }
        }

        return getShortestPath(g, dstId);
    }

    // build up shortest path tree info
    // with PQ optimization no need to add all nodes to Q
    public static List<Integer> findShortestPathAStarBasic(Graph g, Integer srcId, Integer dstId){
        ListQueue<Graph.Node> q = new ListQueue();

        // add ALL Nodes (weights set to 'INFINITE'
        for (Graph.Node n: g.getNodes())
            q.enqueue(n);

        // init source
        Graph.Node sourceNode = g.getNode(srcId);
        sourceNode.distFromParent =  g.getHeuristicEstimate(srcId, dstId);

        // iterate over ALL nodes
        while(q.size() != 0) {
            Graph.Node currNode = q.dequeue();
            // check if curr visited
            if (!currNode.visited) {
                currNode.visited = true; // completed now is part of shortest path
                if (currNode.id == dstId)
                    break;
                // currNode can be marked for shortest path complete
                for (Graph.Node neighbor : g.getNeighbors(currNode)) {
                    // check if neighbor visited
                    if (!neighbor.visited) {
                        int totalDistFromSource = currNode.distFromParent + g.getEdge(currNode.id, neighbor.id).weight;
                        int estimatedHeuristicToDst = g.getHeuristicEstimate(neighbor.id, dstId);

                        if (totalDistFromSource + estimatedHeuristicToDst < neighbor.distFromParent) {
                            neighbor.distFromParent = totalDistFromSource + estimatedHeuristicToDst;
                            neighbor.parent = currNode.id;
                            q.enqueue(neighbor);
                        }
                    }
                }
            }
        }
        return getShortestPath(g, dstId);
    }

}
