import java.util.Iterator;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array; // original array
    private final int size;

    // construct an empty randomized queue
    public RandomizedQueue(int size) {
        array = (Item[]) new Object[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (array[i] != null)
                return false;
        }
        return true;
    }

    // return the number of items on the randomized queue
    public int size() {
        int temp = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != null)
                temp++;
        }
        return temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("You can not add null value!");

        boolean inserted = false;
        int pointer = 0;
        while (pointer < size) {
            if (array[pointer] == null) {
                array[pointer++] = item;
                inserted = true;
                break;
            }
            pointer++;
        }

        if (!inserted && pointer == size) {
            throw new IndexOutOfBoundsException("Randomized Queue is full!");
        }
    }

    // remove and return a random item
    public Item dequeue() {
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

        Random rand = new Random();
        int n = rand.nextInt(localIndex);
        Item returnValue = array[indexes[n]];
        array[indexes[n]] = null;

        return returnValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque is empty!");

        int[] indexes = new int[size];
        int temp = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                indexes[i] = i;
                temp++;
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(temp);

        return array[indexes[n]];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        int sizeOfTempArray = size();
        Item[] tempArray = (Item[]) new Object[sizeOfTempArray];

        int tempIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != null)
                tempArray[tempIndex++] = array[i];
        }

        Random rand = new Random();
        for (int i = tempArray.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Simple swap
            Item a = tempArray[index];
            tempArray[index] = tempArray[i];
            tempArray[i] = a;
        }

        return new Iterator<Item>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer != sizeOfTempArray;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException("No more item left in the Randomized Queue!");
                return tempArray[pointer++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove functions is not supported!");
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>(10);
        System.out.println("Unit test started!");

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            randomizedQueue.enqueue(n);
        }

        for (Integer s : randomizedQueue)
            System.out.println(s);

        System.out.println("Next iterator!");
        for (Integer s : randomizedQueue)
            System.out.println(s);

        System.out.println("Next iterator!");
        for (int i = 0; i < 10; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}




