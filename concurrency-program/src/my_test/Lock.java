package my_test;

import java.util.concurrent.TimeoutException;

/**
 * @author zy
 * @date 2023/1/9 19:12
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;
    void unlock();
    int getBlockSize();
}
