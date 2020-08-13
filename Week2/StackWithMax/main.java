
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        FixedCapacityStackInt<Integer> intValue = new FixedCapacityStackInt<>(100);
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            intValue.push(n);
        }

        System.out.println(intValue.returnMax());
    }
}
