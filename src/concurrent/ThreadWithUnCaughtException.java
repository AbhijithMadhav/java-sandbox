package concurrent;

import static concurrent.ThreadUsage.threadContextPrint;
import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ThreadWithUnCaughtException {

    public static void main(String s[]) {
        new Thread(new ExampleThread()).start();
    }

    public static class ExampleThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.setDefaultUncaughtExceptionHandler(new Handler());
                sleep(SECONDS.toMillis(5));
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Handler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            threadContextPrint("Exception caught is " + e);
        }
    }
}
