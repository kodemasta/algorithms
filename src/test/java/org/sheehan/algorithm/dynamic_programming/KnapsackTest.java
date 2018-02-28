package org.sheehan.algorithm.dynamic_programming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KnapsackTest {

    @Test
    public void testKnapsack(){

        List<Knapsack.Item> items1 = new ArrayList<Knapsack.Item>();
        Knapsack.Item item = new Knapsack.Item(3,4);
        items1.add(item);
        item = new Knapsack.Item(2,3);
        items1.add(item);
        item = new Knapsack.Item(4,2);
        items1.add(item);
        item = new Knapsack.Item(4,3);
        items1.add(item);

        Knapsack.Item[] items = (Knapsack.Item[])items1.toArray(new Knapsack.Item[0]);

        Knapsack knapsack = new Knapsack(items, 8);
        System.out.println("Solution optimal knapsack value: " + knapsack.solve());
        System.out.println();
        System.out.println("Sub Solutions Table");
        knapsack.printSolutions();

        Set<Knapsack.Item> addedItems = knapsack.getSolutionItems();
        Iterator<Knapsack.Item> iterator = addedItems.iterator();
        System.out.println();
        System.out.println("Solution optimal items in knapsack : ");
        while(iterator.hasNext() ){
            Knapsack.Item next = iterator.next();
            System.out.println(next.toString());
        }
        System.out.println();
    }
}