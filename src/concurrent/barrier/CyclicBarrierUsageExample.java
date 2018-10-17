package concurrent.barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static concurrent.Utils.threadContextPrint;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.IntStream.range;

public class CyclicBarrierUsageExample {

    private static final int NTHREADS = 5;
    private static final Random RANDOM = new Random();
    private static final int MAX_THREAD_SLEEP_SECS = 10;

    public static void main(String s[]) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NTHREADS);
        threadContextPrint(format("%d parties required to trip cyclic barrier", cyclicBarrier.getParties()));

        illustrate(cyclicBarrier);


        // To illustrate that cyclic barriers can be reused
        illustrate(cyclicBarrier);
    }


    private static void illustrate(CyclicBarrier cyclicBarrier) {
        ExecutorService executorService = Executors.newFixedThreadPool(NTHREADS);
        range(0, NTHREADS).forEach(i -> executorService.submit(new SleeperThread(cyclicBarrier)));

        threadContextPrint(format("Finished spawning %d sleeper threads", NTHREADS));

        executorService.shutdown(); // no more submissions possible
        threadContextPrint("Initiated executor shutdown. Waiting for any in-progress execution amongst spawned threads");
        try {
            boolean isShutDown = executorService.awaitTermination(MAX_THREAD_SLEEP_SECS * 2, SECONDS);
            if (isShutDown) {
                threadContextPrint("Executor service shutdown successful");
            } else {
                threadContextPrint("Executor service shutdown interrupted by timeout. Consider increasing timeout");
            }
        } catch (InterruptedException e) {
            threadContextPrint("Executor wait interrupted");
            e.printStackTrace();
        }
    }

    private static class SleeperThread implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public SleeperThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                int sleepInt = RANDOM.nextInt(MAX_THREAD_SLEEP_SECS);
                threadContextPrint(format("Mock execution(pre-barrier) for %ds...", sleepInt));
                sleep(SECONDS.toMillis(sleepInt)); // mocking pre-barrier exection
                if (cyclicBarrier.isBroken()) {
                    threadContextPrint("Not able to wait. Barrier broken");
                } else {
                    threadContextPrint(format("Finished sleeping. Waiting along with %d other thread/s at the barrier", cyclicBarrier.getNumberWaiting()));
                    int arrivalIndex = cyclicBarrier.await(MAX_THREAD_SLEEP_SECS * 2, TimeUnit.SECONDS);
                    threadContextPrint(format("Arrival index is %d. Mock execution(post-barrier) for %ds...", arrivalIndex, sleepInt));
                    sleep(SECONDS.toMillis(sleepInt)); // mocking some execution after barrier wait
                }
            } catch (InterruptedException e) {
                threadContextPrint("Interrupted Barrier");
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                threadContextPrint("Broken Barrier");
                e.printStackTrace();
            } catch (TimeoutException e) {
                threadContextPrint("Time out");
                e.printStackTrace();
            }
            threadContextPrint("Done");
        }
    }
}
