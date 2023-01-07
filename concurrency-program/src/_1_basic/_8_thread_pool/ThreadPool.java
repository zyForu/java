package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 15:52
 */
public interface ThreadPool {
    void execute(Runnable runnable);
    void shutdown();
    int getInitSize();
    int getMaxSize();
    int getCoreSize();
    int getQueueSize();
    int getActiveCount();
    boolean isShutdown();
}
