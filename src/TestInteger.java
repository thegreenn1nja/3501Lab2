// Group Jakob Linscheid, John Gulon

public class TestInteger implements Comparable<TestInteger> {
    
    private int value;
    private static long counter = 0;
    
    public TestInteger(int value) {
        this.value = value;
     }

     public int compareTo(TestInteger other) {
     counter++;
     return Integer.compare(this.value, other.value);
    }

    public static long getCounter() {
        return counter;
    }
    
    public static void resetCounter() {
        counter = 0;
    }

   
    public String toString() {
        return Integer.toString(value);
    }
}