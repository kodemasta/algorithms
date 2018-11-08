package org.sheehan.algorithm.data_structures.queue;

import java.util.LinkedList;

class QueueFromStacks {
    
    LinkedList<Integer> stack1 = new LinkedList<Integer>();
    LinkedList<Integer> stack2 = new LinkedList<Integer>();

    // Push element x to the back of stack.
    // push onto stack1
    public void push(int x) {
        stack1.push(x);
    }

    // Removes the element from in front of stack.
    // pop from queue. if queue empty first move all stack1 => queue
    public void pop() {
        refreshStack2();
        stack2.pop();
    }

    // Get the front element.
    // peek from queue. if queue empty first move all stack1 => queue
    public int peek() {
        refreshStack2();
        return stack2.peek();
    }

    // Return whether the stack is empty.
    // pop from queue. if queue empty first move all stack1 => queue
    public boolean empty() {
        refreshStack2();
        return stack2.size()==0;
    }

    // if queue empty first move all stack1 => queue
    private void refreshStack2() {
        if (stack2.size() == 0)
            while(stack1.size() != 0)
                stack2.push(stack1.pop());
    }
}