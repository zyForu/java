package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 15:54
 */
public interface RunnableQueue {
    void offer(Runnable runnable);
    Runnable take() throws InterruptedException;
    int size();
}
