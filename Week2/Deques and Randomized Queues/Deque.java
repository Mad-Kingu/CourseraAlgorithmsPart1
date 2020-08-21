import java.util.Iterator;
import java.util.Random;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;
    private int frontPointer;
    private int backPointer;
    private int size;

    // construct an empty deque
    public Deque() {
        this.size = 1;
        array = (Item[]) new Object[size];
        frontPointer = 0;
        backPointer = size - 1;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return frontPointer == 0 && backPointer == size - 1;
    }

    // return the number of items on the deque
    public int size() {
        return size - (backPointer - frontPointer + 1);
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not push null argument into deque!");
        }
        if (size() == size) {
            int sizeDifferences = size - backPointer;
            int newSize = size * 2;
            Item[] tempArray = (Item[]) new Object[newSize];
            for (int i = 0; i < frontPointer; i++) {
                tempArray[i] = array[i];
            }
            for (int i = newSize - 1; i > size - backPointer; i--) {
                tempArray[i] = array[i];
            }
            size = newSize;
            backPointer = newSize - sizeDifferences;
            array = tempArray;
        }

        if (frontPointer > backPointer) {
            throw new IndexOutOfBoundsException("Deque is full!");
        } else {
            array[frontPointer++] = item;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You can not push null argument into deque!");
        }

        if (frontPointer > backPointer) {
            throw new IndexOutOfBoundsException("Deque is full!");
        } else {
            array[backPointer--] = item;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (frontPointer == 0) {
            throw new java.util.NoSuchElementException("Front of the Deque is empty!");
        }
        return array[--frontPointer];
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (backPointer == size - 1) {
            throw new java.util.NoSuchElementException("Back of the Deque is empty!");
        }
        return array[++backPointer];
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int pointer = 0;

            public boolean hasNext() {
                return pointer != size;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No more item left in the Deque!");
                } else {
                    return array[pointer++];
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
            System.out.println(deque.removeFirst());
        }

        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            deque.addLast(n);
        }

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

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                System.out.println(deque.removeFirst());
            else
                System.out.println(deque.removeLast());
        }
    }
}
