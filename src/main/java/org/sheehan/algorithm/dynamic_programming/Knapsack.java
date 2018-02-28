package org.sheehan.algorithm.dynamic_programming;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bob on 8/2/14.
 *
 * Given items each of value and weight, and a container with capacity W
 * Find optimal enqueue of items to maximize total value for W
 */
public class Knapsack {

    public static class Item {
        private Integer value;
        private Integer weight;

        public Item(Integer value, Integer weight){
            this.value= value;
            this.weight = weight;
        }

        public Integer getValue(){
            return this.value;
        }

        public Integer getWeight(){
            return this.weight;
        }

        public String toString() {
            return "value:" + getValue() + ", weight:" + getWeight();
        }
    }

    private Item items[];
    private Integer maxCapacity;

    // i is residual knapsack capacity (capacity) (rows)
    // j is number of items (columns)
    private Integer soln[][];

    public Knapsack(Item [] items, Integer maxCapacity) {

        this.items = items;
        this.maxCapacity = maxCapacity;
        this.soln = new Integer[maxCapacity+1][items.length+1];


        for (int i = 0; i < items.length; ++i){
            System.out.println("Item " + (i + 1) + " - " + items[i].toString());
        }
        System.out.println();
    }

    public void printSolutions() {

        System.out.print("item:      ");
        for (int itemConsidered = 0; itemConsidered <= items.length; ++itemConsidered) {
            String value = String.format("%5d", itemConsidered);
            System.out.print(value);

        }

        System.out.println();

        for (int residualCapacity = 0; residualCapacity <= maxCapacity; ++residualCapacity) {
            System.out.print("capacity: " + residualCapacity);
            for (int itemConsidered = 0; itemConsidered <= items.length; ++itemConsidered) {
                // rows are for a given residual capacity
                String value = String.format("%5d", this.soln[residualCapacity][itemConsidered]);
                System.out.print(value);
            }

            System.out.println();
        }

        System.out.println();
    }

    // todo - consider not caching sub solutions in array and reduce memory footprint
    public Integer solve() {

        // init for no items
        for (int capacityIndex = 0; capacityIndex <= maxCapacity; ++capacityIndex)
            this.soln[capacityIndex][0] = 0;

        for (int itemIndexCol=1; itemIndexCol<= items.length; ++itemIndexCol){
            for (int capacityIndexRow = 0; capacityIndexRow<= maxCapacity; ++capacityIndexRow) {

                int currentItemWeight = this.items[itemIndexCol-1].getWeight();
                int currentItemValue = this.items[itemIndexCol-1].getValue();

                int previousMaxValueSolution = this.soln[capacityIndexRow][itemIndexCol-1];


                // if this item weight is bigger than residual capacity then use previous
                if (currentItemWeight > capacityIndexRow)
                {
                    this.soln[capacityIndexRow][itemIndexCol] = this.soln[capacityIndexRow][itemIndexCol-1];
                    continue;
                }


                // last solution that with weight offset by current weight ...added to current value
                int currentSolution = this.soln[capacityIndexRow-currentItemWeight][itemIndexCol-1] + currentItemValue;
                this.soln[capacityIndexRow][itemIndexCol] = Math.max(previousMaxValueSolution, currentSolution);
            }
        }

        return this.soln[maxCapacity][items.length];
    }

    public Set<Item> getSolutionItems()
    {
        Set<Item> items = new HashSet<>();

        int capacityIndex = this.maxCapacity;

        Item addItem = null;
        for (int itemIndex = this.items.length; itemIndex > 0; itemIndex--) {
            if (soln[capacityIndex][itemIndex] == soln[capacityIndex][itemIndex-1]) {
                continue;
            }else {
                items.add(this.items[itemIndex-1]);
                capacityIndex = capacityIndex - this.items[itemIndex-1].weight;
            }
        }
        return items;
    }
}
