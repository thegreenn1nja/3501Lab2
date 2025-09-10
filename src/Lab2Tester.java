/**
 * Lab2Tester.java
 * Authors: Your Names Here
 *
 * How to run:
 *  javac *.java
 *  java Lab2Tester
 *
 * Output: CSV-like lines for each run listing dataset, algorithm, runNumber, comparisons, isSorted
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Lab2Tester {

    private static final int N = 10_000;
    private static final int RUNS = 5;

    public static void main(String[] args) {
        // Prepare datasets
        TestInteger[] randomBase = generateRandomArray(N, 1, 1_000_000);
        TestInteger[] sortedBase = generateSortedArray(N, 1); // start at 1, incremental 1
        TestInteger[] sortedBlocks = generateKBlocks(N, 10, true); // 10 ascending blocks of 1000
        TestInteger[] reverseBlocks = generateKBlocks(N, 10, false); // 10 descending blocks

        List<Dataset> datasets = new ArrayList<>();
        datasets.add(new Dataset("random", randomBase));
        datasets.add(new Dataset("sorted", sortedBase));
        datasets.add(new Dataset("10sortedBlocks", sortedBlocks));
        datasets.add(new Dataset("10reverseBlocks", reverseBlocks));

        // Parameters to try for median-of-three and insertion-threshold
        int[] medianKchoices = new int[] {5, 20, 50, 100}; // you can experiment
        int[] insertionTchoices = new int[] {5, 10, 20, 50}; // experiment to find approx optimal

        for (Dataset ds : datasets) {
            for (int run = 1; run <= RUNS; run++) {
                // Original quicksort
                TestInteger[] a1 = copyArray(ds.base);
                TestInteger.resetComparisons();
                QuickSortOriginal.quickSort(a1);
                System.out.printf("%s,Original,%d,%d,%b%n", ds.name, run, TestInteger.getComparisons(), SortUtils.isSorted(a1));

                // Randomized quicksort
                TestInteger[] a2 = copyArray(ds.base);
                TestInteger.resetComparisons();
                QuickSortRandomized.quickSort(a2);
                System.out.printf("%s,Randomized,%d,%d,%b%n", ds.name, run, TestInteger.getComparisons(), SortUtils.isSorted(a2));

                // Median-of-three try different k (pick one per run or loop all)
                for (int k : medianKchoices) {
                    TestInteger[] a3 = copyArray(ds.base);
                    TestInteger.resetComparisons();
                    QuickSortMedianOfThree q3 = new QuickSortMedianOfThree(k);
                    q3.quickSort(a3);
                    System.out.printf("%s,Median3_k=%d,%d,%d,%b%n", ds.name, k, run, TestInteger.getComparisons(), SortUtils.isSorted(a3));
                }

                // Quicksort with insertion-switch try different t
                for (int t : insertionTchoices) {
                    TestInteger[] a4 = copyArray(ds.base);
                    TestInteger.resetComparisons();
                    QuickSortWithInsertion qins = new QuickSortWithInsertion(t);
                    qins.quickSort(a4);
                    System.out.printf("%s,Quick+Insertion_t=%d,%d,%d,%b%n", ds.name, t, run, TestInteger.getComparisons(), SortUtils.isSorted(a4));
                }

                // Library "merge" (Arrays.sort)
                TestInteger[] a5 = copyArray(ds.base);
                TestInteger.resetComparisons();
                MergeSortLib.sort(a5);
                System.out.printf("%s,Arrays.sort,%d,%d,%b%n", ds.name, run, TestInteger.getComparisons(), SortUtils.isSorted(a5));
            }
        }
    }

    private static TestInteger[] copyArray(TestInteger[] base) {
        TestInteger[] copy = new TestInteger[base.length];
        for (int i = 0; i < base.length; i++) {
            // copy values into new TestInteger objects so compare counter is counted on operations
            copy[i] = new TestInteger(Integer.parseInt(base[i].toString()));
        }
        return copy;
    }

    private static TestInteger[] generateRandomArray(int n, int min, int max) {
        TestInteger[] a = new TestInteger[n];
        for (int i = 0; i < n; i++) {
            int val = (int)(Math.random() * (max - min + 1)) + min;
            a[i] = new TestInteger(val);
        }
        return a;
    }

    private static TestInteger[] generateSortedArray(int n, int step) {
        TestInteger[] a = new TestInteger[n];
        int v = 1;
        for (int i = 0; i < n; i++) {
            a[i] = new TestInteger(v);
            v += step;
        }
        return a;
    }

    // Generate 10 blocks each of size n/10; if ascending==true each block is ascending starting at random start; else descending
    private static TestInteger[] generateKBlocks(int n, int blocks, boolean ascending) {
        TestInteger[] a = new TestInteger[n];
        int blockSize = n / blocks;
        int idx = 0;
        for (int b = 0; b < blocks; b++) {
            int start = ThreadLocalRandom.current().nextInt(1, 1_000_000);
            if (ascending) {
                for (int i = 0; i < blockSize; i++) {
                    a[idx++] = new TestInteger(start + i);
                }
            } else {
                for (int i = 0; i < blockSize; i++) {
                    a[idx++] = new TestInteger(start - i);
                }
            }
        }
        // If n not divisible, fill remaining with randoms
        while (idx < n) {
            a[idx++] = new TestInteger(ThreadLocalRandom.current().nextInt(1, 1_000_000));
        }
        return a;
    }

    private static class Dataset {
        String name;
        TestInteger[] base;
        Dataset(String name, TestInteger[] base) {
            this.name = name;
            this.base = base;
        }
    }
}
