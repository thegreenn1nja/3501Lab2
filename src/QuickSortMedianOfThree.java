import java.util.concurrent.ThreadLocalRandom;

public class QuickSortMedianOfThree {

    // threshold k: if subarray size < k then use standard pivot (last element)
    private final int k;

    public QuickSortMedianOfThree(int k) {
        this.k = k;
    }

    public void quickSort(TestInteger[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(TestInteger[] a, int p, int r) {
        if (p < r) {
            if (r - p + 1 >= k) {
                // choose three random indices
                int i1 = ThreadLocalRandom.current().nextInt(p, r + 1);
                int i2 = ThreadLocalRandom.current().nextInt(p, r + 1);
                int i3 = ThreadLocalRandom.current().nextInt(p, r + 1);

                // find median index among i1,i2,i3 using compareTo (counts)
                int medianIndex = medianIndex(a, i1, i2, i3);

                SortUtils.swap(a, medianIndex, r); // put median at r
            }
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private int medianIndex(TestInteger[] a, int i1, int i2, int i3) {
        // We must use compareTo so comparisons are counted.
        TestInteger A = a[i1], B = a[i2], C = a[i3];

        // Compare A and B
        if (A.compareTo(B) <= 0) {
            // A <= B
            if (B.compareTo(C) <= 0) {
                // A <= B <= C => median B
                return i2;
            } else {
                // A <= B and B > C -> A <= ? and C < B
                if (A.compareTo(C) <= 0) {
                    // A <= C < B => median C
                    return i3;
                } else {
                    // C < A <= B => median A
                    return i1;
                }
            }
        } else {
            // A > B
            if (A.compareTo(C) <= 0) {
                // B < A <= C => median A
                return i1;
            } else {
                // A > B and A > C
                if (B.compareTo(C) <= 0) {
                    // B <= C < A => median C
                    return i3;
                } else {
                    // C < B < A => median B
                    return i2;
                }
            }
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
