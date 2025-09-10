import java.util.Arrays;

public class SortUtils {

    public static <T> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static boolean isSorted(TestInteger[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) return false;
        }
        return true;
    }

    // insertion sort over the entire array; uses TestInteger.compareTo (counts)
    public static void insertionSort(TestInteger[] a) {
        int n = a.length;
        for (int j = 1; j < n; j++) {
            TestInteger key = a[j];
            int i = j - 1;
            while (i >= 0 && a[i].compareTo(key) > 0) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
    }

    // insertion sort on subarray [l..r] inclusive
    public static void insertionSort(TestInteger[] a, int l, int r) {
        for (int j = l + 1; j <= r; j++) {
            TestInteger key = a[j];
            int i = j - 1;
            while (i >= l && a[i].compareTo(key) > 0) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
    }

    // Wrapper "merge sort" using Arrays.sort (as requested in lab)
    public static void librarySort(TestInteger[] a) {
        Arrays.sort(a); // Uses compareTo, so counts comparisons
    }
}

