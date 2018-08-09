package concurrent.thread_local;

public class ThreadContext {

    private int userId;

    public ThreadContext(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
