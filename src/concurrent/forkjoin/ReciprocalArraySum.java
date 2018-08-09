package concurrent.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ReciprocalArraySum {

    public static final int SIZE = 100000000;

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
        System.out.println("Parallel Sum = " + task.compute() + ", Time taken = " + (end - start));
        start = System.currentTimeMillis();
        double sum = seqSum(a,0, a.length - 1);
        end = System.currentTimeMillis();
        System.out.println("Sequential Sum = " + sum + ", Time taken = " + (end - start));
    }

    public static double seqSum(int[] a, int lo, int hi) {
        double sum = 0;
        for (int i = lo; i <= hi; i++) {
            sum += 1 / (double)a[i];
        }
        return sum;
    }

    private static  class RecursiveActionExample extends RecursiveTask<Double> {

        private final int[] a;
        private final int hi, lo;

        private final static int THRESHOLD = 10000;
        public RecursiveActionExample(int[] a, int lo, int hi) {
            this.a = a;
            this.hi = hi;
            this.lo = lo;
        }


        @Override
        protected Double compute() {
            if (hi - lo + 1 <= THRESHOLD)
                return seqSum(a, lo, hi);
            int mid = lo + hi >>> 1;
            RecursiveActionExample f1 = new RecursiveActionExample(a, lo, mid);
            f1.fork();
            RecursiveActionExample f2 = new RecursiveActionExample(a, mid + 1, hi);
            f2.fork();
            return f2.join() + f1.join();
        }
    }
}
