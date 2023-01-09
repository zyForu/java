package my_test;

/**
 * @author zy
 * @date 2023/1/9 19:53
 */
public interface Queue {
    void offer(Runnable runnable);
    Runnable take() throws InterruptedException;
    int getQueueSize();
}
