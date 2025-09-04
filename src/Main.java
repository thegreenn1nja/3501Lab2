// Group Jakob Linscheid, John Gulon

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
      TestInteger[] arr = RandomArray.generateRandomArray(10000, 1000000);
      TestInteger.resetCounter();
      Arrays.sort(arr);
      
    //   System.out.println("Sorted Array: " + Arrays.toString(arr));
      System.out.println("Comparisons: " + TestInteger.getCounter());
    }

}


