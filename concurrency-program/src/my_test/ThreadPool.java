package my_test;

/**
 * @author zy
 * @date 2023/1/9 19:49
 */
public interface ThreadPool {
    void execute(Runnable runnable);
    void getInitSize();
    void getMaxSize();
    void getCoreSize();
    int getQueueSize();
}
