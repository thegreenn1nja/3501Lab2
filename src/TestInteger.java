// Group Jakob Linscheid, John Gulon

public class TestInteger implements Comparable<TestInteger> {
    private final int value;
    public static long comparisons = 0;

    public TestInteger(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(TestInteger other) {
        comparisons++;
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public static void resetComparisons() {
        comparisons = 0;
    }

    public static long getComparisons() {
        return comparisons;
    }
}
