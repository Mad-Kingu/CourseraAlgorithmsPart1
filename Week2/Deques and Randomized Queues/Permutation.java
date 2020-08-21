import edu.princeton.cs.algs4.StdIn;

import java.util.MissingFormatArgumentException;

public class Permutation {
    public static void main(String[] args) {
        if (args.length < 1)
            throw new MissingFormatArgumentException("1 integer argument value is missing!");

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>(k * 2);

        String temp = StdIn.readLine();
        String[] tempArray = temp.split(" ");

        for (String s : tempArray) {
            randomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
