package my_test;

/**
 * @author zy
 * @date 2023/1/9 19:49
 */
public interface ThreadPool {
    void execute(Runnable runnable);
    int getInitSize();
    int getMaxSize();
    int getCoreSize();
    int getQueueSize();
}
