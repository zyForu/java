package _1_basic._5_thread_communication.booleanlock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author zy
 * @date 2023/1/7 12:00
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;
    void unlock();
    List<Thread> getBlockThreads();

}
