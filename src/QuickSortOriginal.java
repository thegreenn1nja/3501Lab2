

public class QuickSortOriginal {

    public static void quickSort(TestInteger[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(TestInteger[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private static int partition(TestInteger[] a, int p, int r) {
        TestInteger x = a[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (a[j].compareTo(x) <= 0) {
                i++;
                SortUtils.swap(a, i, j);
            }
        }
        SortUtils.swap(a, i + 1, r);
        return i + 1;
    }
}
