package concurrent.thread_local;

import java.util.stream.IntStream;

public class Main {

    public static final ThreadLocal<ThreadContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String args[]) {

        Servlet servlet = new Servlet();
        IntStream.range(0, 10).forEach(userId -> servlet.execute(userId));
    }
}
