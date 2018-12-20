package org.bsheehan.data_structure.graph.algorithm;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DjikstraShortestPathGridTest extends BaseTest {

    private int dim = 7;
    private int src = 7;
    private int dst = 41;

    @Test
    public void testGridBasic() {
        super.test();
        Graph grid = Graph.createGrid(dim);
        //grid.print();

        DjikstraShortestPath.findShortestPathTreeBasic(grid, src);
        List<Integer> shortestPath = DjikstraShortestPath.getShortestPath(grid, dst);
        System.out.println("shortest path from " +  src + " to " + dst + " :" + shortestPath.toString());
        grid.printGrid(dim);
    }

    @Test
    public void testGridPQ() {
        super.test();
        Graph grid = Graph.createGrid(dim);
        //grid.print();

        List<Integer> shortestPath = DjikstraShortestPath.findShortestPathPQ(grid, src, dst);
        System.out.println("shortest path from " +  src + " to " + dst + " :" + shortestPath.toString());
        grid.printGrid(dim);
    }

    @Test
    public void testGridAStarBasic() {
        super.test();
        Graph grid = Graph.createGrid(dim);
        //grid.print();

        List<Integer> shortestPath = DjikstraShortestPath.findShortestPathAStarBasic(grid, src, dst);
        System.out.println("shortest path from " +  src + " to " + dst + " :" + shortestPath.toString());
        grid.printGrid(dim);
    }

    @Test
    public void testGridAStarPQ() {
        super.test();
        Graph grid = Graph.createGrid(dim);
        //grid.print();

        List<Integer> shortestPath = DjikstraShortestPath.findShortestPathAStarPQ(grid, src, dst);
        System.out.println("shortest path from " +  src + " to " + dst + " :" + shortestPath.toString());
        grid.printGrid(dim);
    }
}
