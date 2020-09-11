import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array; // original array
    private int size;
    private int elementAmount;

    // construct an empty randomized queue
    public RandomizedQueue() {
        elementAmount = 0; // for counting
        size = 1; // default for meh
        array = (Item[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return elementAmount == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return elementAmount;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("You can not add null value!");

        // array is full
        if (elementAmount == size) {
            Item[] newArray = (Item[]) new Object[size * 2];

            for (int i = 0; i < size * 2; i++) {
                if (i < size)
                    newArray[i] = array[i];
                else
                    newArray[i] = null;
            }
            array = newArray;
            size = size * 2;
        }

        int tempIndex = 0;
        while (true) {
            if (array[tempIndex] == null) {
                array[tempIndex] = item;
                elementAmount++;
                break;
            }
            tempIndex++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty!");

        // split the size to the half
        if (elementAmount < size / 4) {
            int newSize = size / 2;
            Item[] newArray = (Item[]) new Object[newSize];

            int tempIndex = 0;
            for (int i = 0; i < size; i++) {
                if (array[i] != null)
                    newArray[tempIndex++] = array[i];
            }
            array = newArray;
            size = newSize;
        }

        int localSize = size();
        int[] indexes = new int[localSize];

        for (int i = 0; i < localSize; i++) {
            indexes[i] = -1;
        }

        int localIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                indexes[localIndex++] = i;
            }
        }

        int n = StdRandom.uniform(localIndex);
        Item returnValue = array[indexes[n]];
        array[indexes[n]] = null;
        elementAmount--;

        return returnValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty!");

        int localSize = size();
        int[] indexes = new int[localSize];

        for (int i = 0; i < localSize; i++) {
            indexes[i] = -1;
        }

        int localIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                indexes[localIndex++] = i;
            }
        }
        int n = StdRandom.uniform(localIndex);

        return array[indexes[n]];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int tempAmount = elementAmount;
            int tempIndex = 0;

            @Override
            public boolean hasNext() {
                return tempAmount != 0;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException("No more item left in the Randomized Queue!");
                while (true) {
                    if (array[tempIndex] != null) {
                        tempAmount--;
                        return array[tempIndex++];
                    } else
                        tempIndex++;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove functions is not supported!");
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        System.out.println("Unit test started!");

        for (int i = 0; i < 10; i++) {
            int n = StdRandom.uniform(Integer.MAX_VALUE);
            randomizedQueue.enqueue(n);
        }

        System.out.println("Iteration started!");
        for (Integer s : randomizedQueue)
            System.out.println(s);

        System.out.println("Sample!");
        System.out.println(randomizedQueue.sample());

        System.out.println("Next iterator!");
        for (int i = 0; i < 10; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

        System.out.println("Next iterator!");
        for (Integer s : randomizedQueue)
            System.out.println(s);
    }
}
