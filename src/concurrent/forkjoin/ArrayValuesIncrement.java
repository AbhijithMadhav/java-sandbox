package concurrent.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class ArrayValuesIncrement {

    public static final int SIZE = 100000000;
    private final static int THRESHOLD = 10000;

    public static void main(String s[]) {
        ForkJoinPool pool = new ForkJoinPool();
        int [] a = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            a[i] = random.nextInt();
        }
        RecursiveActionExample task = new RecursiveActionExample(a, 0, a.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("Time taken(Parallel) = " + (end - start));
        start = System.currentTimeMillis();
        sequentiallyIncrement(a, 0, a.length - 1);
        end = System.currentTimeMillis();
        System.out.println("Time taken(Sequential) = " + (end - start));
    }

    private static void sequentiallyIncrement(int[] a, int lo, int hi) {
        IntStream.rangeClosed(lo, hi).forEach(i -> a[i]++);
    }

    public static class RecursiveActionExample extends RecursiveAction {

        private int[] a;
        private int lo;
        private int hi;

        public RecursiveActionExample(int[] a, int lo, int hi) {
            this.a = a;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected void compute() {
            if (hi - lo + 1 <= THRESHOLD)
                sequentiallyIncrement(a, lo, hi);
            else {
                int mid = lo + hi >>> 1;
                invokeAll(new RecursiveActionExample(a, lo, mid), new RecursiveActionExample(a, mid + 1, hi));
            }
        }
    }
}
