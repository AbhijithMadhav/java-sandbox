package concurrent.thread_local;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import static concurrent.thread_local.Main.THREAD_LOCAL;

public class Servlet {

    private int userId;

    private void _log(String msg) {
        System.out.println(new Date() + " : " + msg);
    }

    public void execute(int userId) {

        new Thread(() -> {
            this.userId = userId;
            THREAD_LOCAL.set(new ThreadContext(userId));
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _log("Instance user id : " + this.userId + ", Actual userId stored in thread local : " + THREAD_LOCAL.get().getUserId());
        }).start();
    }
}
