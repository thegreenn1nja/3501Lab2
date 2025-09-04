import java.util.Random;

public class RandomArray {
    public static TestInteger[] generateRandomArray(int size, int bound) {
        Random rand = new Random();
        TestInteger[] arr = new TestInteger[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new TestInteger(rand.nextInt(bound)); 
        }
        return arr;
    }
}
