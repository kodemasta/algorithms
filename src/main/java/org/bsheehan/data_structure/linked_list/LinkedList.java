package org.bsheehan.data_structure.linked_list;

public class LinkedList <K,V> {
    Node<K,V> head;
    Node<K,V> tail;

    class Node<K,V> {
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node (K key, V value){
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    public LinkedList(K key, V value) {
        this.head = new Node<K,V>(key, value);
        this.tail = this.head;
    }

    public void addFront(K key, V value){
        Node<K,V> node = new Node<K,V>(key, value);
        node.next = head;
        head.prev = node;
        this.head = node;
    }

    public void remove(K key){
        Node<K,V> curr = this.head;
        while (curr != null) {
            if (curr.key.equals(key))
                break;
            curr = curr.next;
        }
        if (curr != null) {
            if (curr == head){
                this.removeFront();
            } else if (curr == tail) {
                this.removeBack();
            } else {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
            }
        }
    }

    public void addBefore(K beforeKey, K key, V value){
        Node<K,V> node = new Node<K,V>(key, value);
        Node<K,V> curr = this.head;
        while (curr != null) {
            if (curr.key.equals(beforeKey))
                break;
            curr = curr.next;
        }

        if (curr != null){
            node.next = curr;
            node.prev = curr.prev;
            if (curr.prev != null)
                curr.prev.next = node;
            curr.prev = node;
        }

        if (node.prev == null)
            this.head = node;
    }

    public void addAfter(K afterKey, K key, V value){
        Node<K,V> node = new Node<K,V>(key, value);
        Node<K,V> curr = this.head;
        while (curr != null) {
            if (curr.key.equals(afterKey))
                break;
            curr = curr.next;
        }

        if (curr != null){
            node.next = curr.next;
            node.prev = curr;
            if (curr.next != null)
                curr.next.prev = node;
            curr.next = node;
        }

        if (node.next == null)
            this.tail = node;

    }

    public void addBack(K key, V value){
        Node<K,V> node = new Node<K,V>(key, value);
        node.prev = tail;
        tail.next = node;
        this.tail = node;
    }

    public V removeFront() {
        V removed = this.head.value;
        this.head = this.head.next;
        if (this.head != null)
            this.head.prev = null;
        else
            this.tail = null;
        return removed;
    }

    public V removeBack() {
        V removed = this.tail.value;
        this.tail = this.tail.prev;
        if (this.tail != null)
            this.tail.next = null;
        else
            this.head = null;
        return removed;
    }

    public V find(K key) {
        Node<K,V> curr = this.head;
        while (curr != null) {
            if (curr.key.equals(key))
                return curr.value;
            curr = curr.next;
        }
        return null;
    }

    public void swap(K key1, K key2) {
        // find the nodes to swap
        Node<K,V> curr = this.head;
        Node<K,V> prev = null;
        while (curr != null) {
            if (curr.key.equals(key1))
                break;
            prev = curr;
            curr = curr.next;
        }
        Node<K,V> node1 = curr;
        Node<K,V> node1Prev = prev;
        Node<K,V> node1Next = curr.next;
        if (curr.next != null)
            node1Next = curr.next;
        curr = this.head;
        prev = null;
        while (curr != null) {
            if (curr.key.equals(key2))
                break;
            prev = curr;
            curr = curr.next;
        }

        Node<K,V> node2 = curr;
        Node<K,V> node2Prev = prev;
        Node<K,V> node2Next = null;
        if (curr.next != null)
            node2Next = curr.next;

        // not adjacent nodes
        if (node1.next != node2 && node2.next != node1) {
            if (node2Prev != null)
                node2Prev.next = node1;
            node1.prev = node2Prev;
            node1.next = node2Next;
            if (node2Next != null)
                node2Next.prev = node1;

            if (node1Prev != null)
                node1Prev.next = node2;
            node2.prev = node1Prev;
            node2.next = node1Next;
            if (node1Next != null)
                node1Next.prev = node2;
        } else if (node1.next == node2) {
            //adjacent nodes
            if (node1Prev != null)
                node1Prev.next = node2;
            if (node2Next != null)
                node2Next.prev = node1;
            node2.prev = node1Prev;
            node2.next = node1;
            node1.prev = node2;
            node1.next = node2Next;
        } else if (node2.next == node1) {
            //adjacent nodes
            if (node2Prev != null)
                node2Prev.next = node1;
            if (node1Next != null)
                node1Next.prev = node2;
            node1.prev = node2Prev;
            node1.next = node2;
            node2.prev = node1;
            node2.next = node1Next;
        }

        // reset head and or tail if necessary
        if (this.head == node1) {
            this.head = node2;
        } else if (this.head == node2) {
            this.head = node1;
        }

        if (this.tail == node1) {
            this.tail = node2;
        } else if (this.tail == node2) {
            this.tail = node1;
        }
    }

    public int size() {
        int count = 0;
        for (Node<K, V> curr = this.head; curr != null; curr = curr.next)
            count++;

        return count;
    }

    public void clear() {
        // clear forward next refs
        Node<K,V> curr = this.head;
        Node<K,V> prev = null;
        while (curr != null) {
            prev = curr;
            curr = curr.next;
            prev.next = null;
        }
        this.head = null;

        // clear reverse prev refs
        curr = this.tail;
        Node<K,V> next  = null;
        while (curr != null) {
            next = curr;
            curr = curr.prev;
            next.prev = null;
        }
        this.tail = null;
    }

    public void print(boolean forward){
        System.out.print("size:" +  this.size());
        if (this.head != null)
            System.out.print(" head:" +  this.head.key);
        else
            System.out.print(" head:" +  this.head );
        if (this.tail != null)
            System.out.print(" tail:" +  this.tail.key + " ");
        else
            System.out.print(" tail:" +  this.tail  + " ");

        if (forward) {
            for (Node<K, V> curr = this.head; curr != null; curr = curr.next) {
                System.out.print(curr.key + ":" + curr.value);
                System.out.print(" ");
            }
        } else {
            System.out.print("reverse order:");
            for (Node<K, V> curr = this.tail; curr != null; curr = curr.prev) {
                System.out.print(curr.key + ":" + curr.value);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

}
