package org.sheehan.algorithm.data_structures.stack;

import java.util.LinkedList;
import java.util.Queue;

class StackFromQueue {
     //one ListStack solution
    private Queue<Integer> q = new LinkedList<Integer>();
    
    // Push element x onto queue.
    public void push(int x) {
        q.add(x);
        for(int i = 1; i < q.size(); i ++) { //rotate the stack to make the tail be the head
            q.add(q.poll());
        }
    }
    
    // Removes the element on top of the queue.
    public void pop() {
        q.poll();
    }
    
    // Get the top element.
    public int top() {
        return q.peek();        
    }
    
    // Return whether the queue is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}