package concurrent.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static final int SIZE = 100000000;
    private static final int TIME_STEPS = 10;
    private static final int NO_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int CHUNK_SIZE = SIZE/NO_THREADS;



    public static void main(String s[]) throws BrokenBarrierException, InterruptedException {
        int[] read = new int[SIZE];
        int[] write = new int[SIZE];
        int[] tmp;
        for (int i1 = 0; i1 < SIZE; i1++) {
            read[i1] = i1;
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(NO_THREADS);
        long start = System.currentTimeMillis();
        for (int t = 1 ; t <= TIME_STEPS; t++) {
            for (int i = 0; i < SIZE; i += CHUNK_SIZE) {
                executor.submit(new NeighbourSum(i, i + CHUNK_SIZE, read, write, countDownLatch));
            }
            tmp = read;
            read = write;
            write = tmp;
        }
        long end = System.currentTimeMillis();
        executor.shutdown();
        System.out.println("Time(Parallel) = " + (end - start));
        System.out.println("Still waiting for threads : " + countDownLatch.getCount());
    }

    private static class NeighbourSum implements Runnable {

        private int left;
        private int right;
        private int[] read;
        private int[] write;
        private CountDownLatch countDownLatch;

        public NeighbourSum(int left, int right, int[] read, int[] write, CountDownLatch countDownLatch) {
            this.left = left;
            this.right = right;
            this.read = read;
            this.write = write;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                int left = (i - 1) < 0 ? 0 : read[i - 1];
                int right = (i + 1) >= SIZE ? (SIZE + 1) : read[i + 1];
                write[i] = (left + right) / 2;
            }
            try {
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
