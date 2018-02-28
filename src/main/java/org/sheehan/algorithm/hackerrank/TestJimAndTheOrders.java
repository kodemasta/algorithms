package org.sheehan.algorithm.hackerrank;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestJimAndTheOrders {

    static class Order implements Comparable<Order>{
        int i;
        int start;
        int length;

        @Override
        public int compareTo(Order o) {
            if (o.start+o.length > start+length)
                return -1;
            else if (o.start+o.length < start+length)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            Order o = new TestJimAndTheOrders.Order();
            o.i = i+1;
            o.start = in.nextInt();
            o.length = in.nextInt();
            orders.add(o);
        }
        process(orders);
    }

    static private void process(List<TestJimAndTheOrders.Order> orders) {
        Collections.sort(orders);
        for (Order o: orders)
            System.out.print(o.i + " ");
        System.out.println();
    }
}