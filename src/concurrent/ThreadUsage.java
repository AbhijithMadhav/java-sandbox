package concurrent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static concurrent.Utils.threadContextPrint;

public class ThreadUsage {

    public static void main(String s[]) throws InterruptedException {
        threadContextPrint("Active Threads ");
        Thread[] t = new Thread[Thread.activeCount()];
        Thread.enumerate(t);
        Arrays.asList(t).forEach(thread -> System.out.println(thread));
        threadContextPrint("Is current thread daemon : " + Thread.currentThread().isDaemon());
        Thread t1 = new Thread(new SleepingThread());
        t1.setDaemon(true);
        t1.start();
        threadContextPrint("Is t1 thread daemon : " + t1.isDaemon());
    }

    public static class SleepingThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
