package concurrent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static concurrent.Utils.threadContextPrint;

public class ThreadInterrupter {

    public static void main(String s[]) throws InterruptedException {
        Thread t1 = new ExampleThread();
        threadContextPrint("Is t1 thread daemon : " + t1.isDaemon());
        t1.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        threadContextPrint("");
        t1.interrupt();
    }

    public static class ExampleThread extends Thread {

        @Override
        public void run() {
            threadContextPrint("Starting now");

            while(true) {
                try (FileWriter fileWriter = new FileWriter(new File("/tmp/out1"))) {
                    fileWriter.append(LocalDateTime.now().toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
