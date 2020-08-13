
public class FixedCapacityStackInt<Integer> extends FixedCapacityStack<Integer> {
    private int maxValue = java.lang.Integer.MIN_VALUE;
    public FixedCapacityStackInt(int cap) {
        super(cap);
    }

    public void push(Integer integer) {
        super.push(integer);
        if ((int)integer > maxValue)
            maxValue = (int)integer;
    }

    public int returnMax() {
        return maxValue;
    }
}
