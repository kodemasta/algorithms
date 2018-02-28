package org.sheehan.algorithm.data_structures;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Created by bob on 5/31/14.
 */
public class ListImpl <T extends Comparable<T>> implements List<T> {

    public Node<T> head, tail;

    public ListImpl() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void appendFront(T value) {
        Node<T> node = new Node<>(value);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    @Override
    public void appendBack(T value) {
        Node<T> node = new Node<>(value);
        if (this.tail == null) {
            this.head = node;
            this.tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    @Override
    public Node<T> deleteFront() {
        if (head == null)
            return null;

        Node<T> front = head;
        head = head.next;

        if (head == null)
            tail = null;
        return front;
    }

    @Override
    public Node<T> deleteBack() {
        if (tail == null)
            return null;

        Node<T> curr = head;
        Node<T> prev = null;

        // lets point at one before last element
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }

        // first element
        if (prev == null){
            head = null;
            tail = null;
            return curr;
        } else {
            prev.next = curr.next;
            return curr;
        }
    }

    public void clear() {
        if (head == null)
            return;

        Node<T> curr = head;
        Node<T> prev = null;

        // lets point at one before last element
        while (curr != null) {
            prev = curr;
            curr = curr.next;
            prev.next = null;
        }

    }

    @Override
    public void insertBefore(T value, int pos) {
        if (head == null) {
            appendFront(value);
            return;
        }

        Node curr = head;
        Node prev = null;
        int cnt = 0;
        while(curr != null) {
            if (cnt++ == pos)
                break;

            prev = curr;
            curr=curr.next;
        }

        if (curr == null)
            return; //error

        if (prev == null) {
            appendFront(value);
            return;
        }

        Node<T> newNode = new Node<>(value);
        newNode.next=curr;
        prev.next=newNode;
    }

    @Override
    public void insertAfter(T value, int pos) {
        if (head == null) {
            appendFront(value);
            return;
        }

        Node curr = head;
        Node prev = null;
        int cnt = 0;
        while(curr != null) {
            if (cnt++ == pos)
                break;

            prev = curr;
            curr=curr.next;
        }

        if (curr == null)
            return; //error..pos may be out of range

        if (prev == null) {
            appendFront(value);
            return;
        }

        Node<T> newNode = new Node<>(value);
        newNode.next=curr.next;
        curr.next = newNode;
    }

    @Override
    public void deleteAt(int pos) {
        if (head == null) {
            return;
        }

        Node curr = head;
        Node prev = null;
        int cnt = 0;
        while(curr != null) {
            if (cnt++ == pos)
                break;

            prev = curr;
            curr=curr.next;
        }

        if (curr == null)
            return; //error

        if (prev == null) {
            deleteFront();
            return;
        }


        prev.next=curr.next;
    }

    // here we delete elements that match val
    // careful to only increment prev to non deleted nodes !!!
    public Node deleteElements(int val) {
        if (head == null)
            return null;

        Node prev = null;
        Node curr = head;
        while (curr != null) {

            if (curr.data.equals(val)){
                if (prev == null) {
                    head = curr.next; //delete at front
                } else {
                    prev.next = curr.next; //delete after front
                }
            } else
                prev = curr;  /// IMPORTANT ! Only update prev if not deleted or else prev points to deleted node !!!

            curr = curr.next;
        }

        return head;
    }

    //If only pointer to current node. Copy next and delete next !!!
    public void deleteCurrentNode(Node<T> node){
        // Copy next to target and delete next !!!
    }

    public void insertInOrder(T data) {
        if (head == null) {
            Node<T> newNode = new Node<>(data);
            head = newNode;
            return;
        }

        Node<T> curr = head;

        int cnt = 0;
        while (curr != null) {
            if (data.compareTo(curr.data) < 0) {
                insertBefore(data, cnt);
                return;
            }
            cnt++;
            curr = curr.next;
        }
        appendBack(data);
    }

    //brute force
    @Override
    public void reverseBrute() {

        // need the tail for later bro
        Node originalTail = this.tail;

        Node curr = this.head;

        while (curr != null) {
            curr = curr.next;

            // go to one before end
            Node prev2 = null;
            Node curr2 = this.head;
            while (curr2.next != null) {
                prev2 = curr2;
                curr2 = curr2.next;
            }

            //reverse end most link
            curr2.next = prev2;
            // shorten original list
            if (prev2 != null)
                prev2.next = null;
        }


        this.tail = head;
        head = originalTail;
    }

    //BB - ABC - A
    // scaffold approach O(n)view
    // 1. B -> A reverse
    // 2. move everything along A-> B,  B-> C, C-> C(next)
    // iterate
    @Override
    public void reverse() {
        Node A = null, B = head, C = head.next;

        while (B != null) {
            B.next = A; //reversal step

            // move the scaffold
            A = B;
            B = C;

            if (C != null)
                C = C.next;
        }

        this.tail = head;
        this.head = A; // don't forget to set the head !!!
    }

    public void reverseStack() {
        Stack<Node<T>> stack = new Stack<>();

        Node<T> curr = this.head;

        while(curr != null){
            stack.push(curr);
            curr = curr.next;
        }

        this.head = stack.pop(); //***

        curr = this.head;
        while(!stack.empty()) {
            curr.next = stack.pop();
            curr = curr.next;
        }
        curr.next = null;
    }

//    public void reverseRecurse() {
//        reverseRecurse(this.head);
//    }
//
//    //TODO NEEDS MORE INVESTIGATION !!!!!!!!!!!!!!!!!!!!!
//    public Node reverseRecurse(Node node) {
//        if (node == null) return null;
//        if (node.next == null) return node;
//
//        Node secondElem = node.next;
//        node.next = null;
//        Node reverseRest = reverseRecurse(secondElem);
//        secondElem.next = node;
//        return reverseRest;
//    }

    @Override
    public void print() {
        Node curr = this.head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public int size() {
        int count = 0;

        Node curr = this.head;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }

    @Override
    public void introduceCycleForTest() {

        // get to tail so tail can point to some random node
        Node tail = this.head;
        while (tail.next != null) {
            tail = tail.next;
        }

        Random random = new Random();
        int start = random.nextInt(size() - 1);

        Node startNode = this.head;
        for (int i = 0; i < start; ++i) {
            startNode = startNode.next;
        }

        int stop = random.nextInt(size() - 1);

        Node stopNode = this.head;
        for (int i = 0; i < stop; ++i) {
            stopNode = stopNode.next;
        }

        // make loop !
        startNode.next =stopNode;

        System.out.println("created cycle start: " + stopNode.data);
        System.out.println("created cycle end: " + startNode.data);
    }

    @Override
    public Node findCycle(){

        Node slow = this.head;
        Node fast = slow.next;

        while(fast != null){
            if (fast==slow){
                break;
            }
            slow=slow.next;
            fast=fast.next;
            if (fast !=null)
                fast=fast.next;
        }

        if (fast==null){
            return null; // no cycle
        }

        // get length of cycle
        int cycleLength = 1;
        slow=slow.next;
        while(slow!=fast){
            cycleLength++;
            slow=slow.next;
        }

        // set slow node to cycle length from head
        Node cycleNode = null;
        slow=this.head;
        while(cycleLength!=0){
            cycleLength--;
            slow=slow.next;
        }

        // set fast at head and cycle till meets slow ==> cycle start node
        fast=this.head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }

        return slow;
    }


    @Override
    public T set(int index, T value) {
        if (index > size())
            return null;
        Node curr = this.head;
        for (int i = 0; i < index; ++i)
            curr = curr.next;

        if (curr != null)
            curr.data = value;

        return value;
    }

    @Override
    public T get(int index) {
        if (index > size())
            return null;
        Node curr = this.head;
        for (int i = 0; i < index; ++i)
            curr = curr.next;

        if (curr != null)
            return (T) curr.data;

        return null;
    }

    @Override
    public void toArray(T[] array) {
        Node<T> curr = this.head;
        int cnt = 0;
        while (curr != null) {
            array[cnt++] = curr.data;
            curr = curr.next;
        }
    }

    // locate if elements of sublist are in larger list
    // assumptions - sublist smaller than large list
    public boolean orderedElementsFound(List<T> subList) {
        Node<T> outerCurr = this.head; //trick persist outer list iterator and not reset on inner loop
        //iterate over inner list

        Iterator<T> subListIter = subList.iterator();

        while (subListIter.hasNext()) {
            T subListElem = subListIter.next();
            boolean elemFound = false;

            //move along outer list (do not rest to beginning for O(n)
            for (; outerCurr != null; outerCurr = outerCurr.next) {
                if (subListElem.equals(outerCurr.data)) {
                    elemFound = true;
                    outerCurr = outerCurr.next;
                    break; // now restart on next sublist elem
                }
            }
            if (!elemFound) // all elemes insublist found
                return false;
        }
        return true;
    }



    /////////////////////////////////////////////////////////////////////////////////
    // INSERTION SORT LIST
    /////////////////////////////////////////////////////////////////////////////////
    // worst 0(n2)
    // avg O(n2)
    // best O(n) - if already sorted !
    // compares each new element against already sorted elements
    // This rejiggers the original list links to point in sorted order
    public static <T extends Comparable<T>> void insertionSort(ListImpl<T> list) {
        List.Node<T> sortedHead = null;
        List.Node<T> curr = list.head;

        while (curr != null) {

            // where to insert in sorted list ?
            List.Node<T> sortedCurr = sortedHead;
            List.Node<T> prevSorted = null;
            while (sortedCurr != null) {
                if (curr.data.compareTo(sortedCurr.data) < 0) {
                    break;
                }
                prevSorted = sortedCurr;
                sortedCurr = sortedCurr.next;
            }

            List.Node<T> next = curr.next; // SAVE THIS for iteration cause relinking nodes along the way !!

            // insert curr into sorted list

            // build up sorted list from beginning
            if (prevSorted == null) { //insert at head
                curr.next = sortedCurr;
                sortedHead = curr; // SET head of sorted list here !
            } else if (sortedCurr == null) {//insert at end
                prevSorted.next = curr;
                curr.next = null;
            } else { // insert in middle
                prevSorted.next = curr;
                curr.next = sortedCurr;
            }

            curr = next; // iterate down unsorted remaining list
        }
        list.head = sortedHead; // now set original list head to first sorted node !
    }

    // iterate over sorted array to insert minimum unsorted node
    public static <T extends Comparable<T>> void selectionSort(ListImpl<T> list) {
        List.Node<T> sortedPrev = null;
        List.Node<T> sortedCurr = list.head;

        while (sortedCurr != null) {

            boolean minFound = false;
            List.Node<T> min = sortedCurr;
            List.Node<T> minPrev = null;
            List.Node<T> unsortedCurr = sortedCurr.next;
            List.Node<T> unsortedPrev = sortedCurr;
            while (unsortedCurr != null) {
                if (unsortedCurr.data.compareTo(min.data) < 0) {
                    minPrev = unsortedPrev;
                    min = unsortedCurr;
                    minFound = true;
                }

                unsortedPrev = unsortedCurr;
                unsortedCurr = unsortedCurr.next;
            }

            if (minFound)
                swapNodes(list, sortedPrev, sortedCurr, minPrev, min);
            else
                break;

            sortedPrev = min; // min is now at the previous position
            sortedCurr = min.next; //.. so that makes curr min.next !!!
        }
    }

    private static <T extends Comparable<T>> void swapNodes(ListImpl<T> list, Node<T> prev1, Node<T> node1, Node<T> prev2, Node<T> node2) {
        if (node1 == null || node1.next == null)
            return;

        if (node1.next != node2 && node2.next != node1) { // separated non adjacent nodes
            Node<T> tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;

            if (prev1 != null)
                prev1.next = node2;
            else
                list.head = node2; //at front
            if (prev2 != null)
                prev2.next = node1;
            else
                list.head = node1; //at front
        } else if (node1.next == node2) { // adjacent nodes
            node1.next = node2.next;
            node2.next = node1;

            if (prev1 != null)
                prev1.next = node2;
            else
                list.head = node2; //at front
        } else if (node2.next == node1) { // adjacent nodes
            node2.next = node1.next;
            node1.next = node2;

            if (prev2 != null)
                prev2.next = node1;
            else
                list.head = node1; //at front
        }
    }

    public static <T extends Comparable<T>> void swapPairs(ListImpl<T> list) {

        List.Node<T> prev = null;
        List.Node<T> curr = list.head;

        while (curr != null) {
            swapNodes(list, prev, curr, curr, curr.next);

            prev = curr;
            curr = curr.next; // this should work because curr was swapped forward !

        }
    }

    public Node<Integer> mergeTwoLists(Node<Integer> l1, Node<Integer> l2) {

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Node<Integer> dummyMergedHead = new Node<Integer>(-1); // dummy node
        Node<Integer> currMerged = dummyMergedHead;

        Node<Integer> curr1 = l1;
        Node<Integer> curr2 = l2;

        while (curr1 != null && curr2 != null) {

            if (curr1.data > curr2.data){
                currMerged.next = curr2;
                currMerged = curr2;
                curr2 = curr2.next;
            } else {
                currMerged.next = curr1;
                currMerged = curr1;
                curr1 = curr1.next;
            }
        }

        while (curr1 != null) {
            currMerged.next = curr1;
            currMerged = curr1;
            curr1 = curr1.next;
        }

        while (curr2 != null) {
            currMerged.next = curr2;
            currMerged = curr2;
            curr2 = curr2.next;
        }

        return dummyMergedHead.next;
    }

    // move even nodes to end of list in same order
    public Node<T> oddEvenList(Node<T> head) {
        if (head == null)
            return null;

        Node<T> oddCurr = head;
        Node<T> oddTail = head;

        Node<T> evenCurr = head.next;
        Node<T> evenHead = evenCurr;    // this is fixed.

        while(oddCurr != null){
            if (oddCurr.next != null)
                oddCurr.next = oddCurr.next.next;
            if (evenCurr != null && evenCurr.next != null)
                evenCurr.next = evenCurr.next.next;

            // track end of odd elements
            oddTail = oddCurr;

            if (oddCurr != null)
                oddCurr = oddCurr.next;
            if (evenCurr != null)
                evenCurr = evenCurr.next;
        }

        // point last odd elem to even head
        oddTail.next = evenHead;

        return head;
    }

    public Node<T> removeNthFromEnd(Node<T> head, int n) {
        if (head == null)
            return null;

        // 1. iterate until nth node in position
        Node<T> nth = head;
        int cnt = 0;
        while(nth != null && cnt != n){
            nth = nth.next;
            cnt++;
        }

        // 2. iterate until nth == null, but start from beginning with curr.
        Node<T> curr = head;
        Node<T> prev = null;
        while(nth != null){
            nth = nth.next;
            prev = curr;
            curr = curr.next;
        }

        // 3a. delete at head if needed
        if (prev == null){
            head = head.next;
            return head;
        }

        // 3b. delete in list if needed
        if (curr != null)
            prev.next=curr.next;

        return head;
    }

    // =====================================================================================
    @Override
    public Iterator<T> iterator() {
        return new MyListIterator<T>(this);
    }


    public class MyListIterator<T extends Comparable<T>> implements Iterator<T> {
        Node<T> current;

        public MyListIterator(ListImpl<T> nodes) {
            current = nodes.head;

        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T value = this.current.data;
            this.current = this.current.next;
            return value;
        }

        @Override
        public void remove() {
            //TODO
        }
    }

}