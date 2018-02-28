package org.sheehan.algorithm.data_structures.queue;

import java.util.LinkedList;

class QueueFromStacks {
    
    LinkedList<Integer> stack1 = new LinkedList<Integer>();
    LinkedList<Integer> stack2 = new LinkedList<Integer>();

    // Push element x to the back of queue.
    // push onto stack1
    public void push(int x) {
        stack1.push(x);
    }

    // Removes the element from in front of queue.
    // pop from stack2. if stack2 empty first move all stack1 => stack2
    public void pop() {
        refreshStack2();
        stack2.pop();
    }

    // Get the front element.
    // peek from stack2. if stack2 empty first move all stack1 => stack2
    public int peek() {
        refreshStack2();
        return stack2.peek();
    }

    // Return whether the queue is empty.
    // pop from stack2. if stack2 empty first move all stack1 => stack2
    public boolean empty() {
        refreshStack2();
        return stack2.size()==0;
    }

    // if stack2 empty first move all stack1 => stack2
    private void refreshStack2() {
        if (stack2.size() == 0)
            while(stack1.size() != 0)
                stack2.push(stack1.pop());
    }
}