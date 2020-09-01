import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Random;

// doubly linked list for fast deque and enqueue 
public class Deque<Item> implements Iterable<Item> {
    Node tailNode;
    Node headNode;
    private int size = 0;

    private class Node {
        private Item data;
        private Node next;
        private Node previous;

        public Node(Item data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    // construct an empty deque
    public Deque() {
        tailNode = new Node(null, null, null); // tailnode is the tail pointer
        headNode = new Node(null, tailNode, null); // headnode is the head node with tail next
        tailNode.previous = headNode;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not push null argument into deque!");
        }

        if (headNode.data == null) {
            headNode.data = item;
        } else {
            Node newNode = new Node(item, headNode, null);
            headNode.previous = newNode;
            headNode = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not push null argument into deque!");
        }

        if (tailNode.data == null) {
            tailNode.data = item;
        } else {
            Node newNode = new Node(item, null, tailNode);
            tailNode.next = newNode;
            tailNode = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Front of the Deque is empty!");
        }

        Item tempData = null;
        if (size != 1) {
            if (headNode.data != null) {
                tempData = headNode.data;
                Node tempNode = headNode;
                headNode = headNode.next;
                headNode.previous = null;
                tempNode.next = null;
                tempNode.data = null;
            }
        } else {
            if (headNode.data != null) {
                tempData = headNode.data;
                headNode.data = null;
            } else {
                tempData = tailNode.data;
                tailNode.data = null;
            }
        }
        size--;
        return tempData;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Back of the Deque is empty!");
        }

        Item tempData = null;
        if (size != 1) {
            if (tailNode.data != null) {
                tempData = tailNode.data;
                Node tempNode = tailNode;
                tailNode = tailNode.previous;
                tailNode.next = null;
                tempNode.previous = null;
                tempNode.data = null;
            }
        } else {
            if (tailNode.data != null) {
                tempData = tailNode.data;
                tailNode.data = null;
            } else {
                tempData = headNode.data;
                headNode.data = null;
            }
        }
        size--;
        return tempData;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node tempNode = headNode;

            public boolean hasNext() {
                return tempNode != null;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No more item left in the Deque!");
                } else {
                    Item tempData = tempNode.data;
                    tempNode = tempNode.next;
                    return tempData;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove functions is not supported!");
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println("Unit test started!");

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            deque.addFirst(n);
        }

        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            deque.addLast(n);
        }

        System.out.println("Remove from first 10!");
        for (int i = 0; i < 10; i++) {
            System.out.println(deque.removeFirst());
        }

        System.out.println("Remove from last 10!");
        for (int i = 0; i < 10; i++) {
            System.out.println(deque.removeLast());
        }

        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            if (i % 2 == 0)
                deque.addFirst(n);
            else
                deque.addLast(n);
        }

        System.out.println("Iteration started!");
        for (Integer s : deque)
            StdOut.println(s);

        System.out.println("Removing the queue started!");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                System.out.println(deque.removeFirst());
            else
                System.out.println(deque.removeLast());
        }
    }
}
