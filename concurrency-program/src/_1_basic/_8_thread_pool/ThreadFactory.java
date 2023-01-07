package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 15:56
 */
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
