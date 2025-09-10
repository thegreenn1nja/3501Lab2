public class QuickSortWithInsertion {

    private final int t; // threshold subarray size to leave for insertion sort

    public QuickSortWithInsertion(int t) {
        this.t = t;
    }

    public void quickSort(TestInteger[] a) {
        quickSort(a, 0, a.length - 1);
        // finish with insertion sort on entire array
        SortUtils.insertionSort(a);
    }

    private void quickSort(TestInteger[] a, int p, int r) {
        if (p < r) {
            if (r - p + 1 <= t) {
                // leave this small piece to insertion sort later
                return;
            }
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private int partition(TestInteger[] a, int p, int r) {
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
