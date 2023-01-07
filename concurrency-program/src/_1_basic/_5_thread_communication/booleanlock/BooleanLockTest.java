package _1_basic._5_thread_communication.booleanlock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author zy
 * @date 2023/1/7 12:58
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable(long mills) {
        try {
            lock.lock(mills);
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        BooleanLockTest booleanLockTest = new BooleanLockTest();
        IntStream.range(0,5).mapToObj( i -> new Thread(booleanLockTest::syncMethod)).forEach(Thread::start);
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(booleanLockTest.lock.getBlockThreads());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest booleanLockTest = new BooleanLockTest();
        new Thread(booleanLockTest::syncMethod).start();
        IntStream.range(0,3).mapToObj(i -> new Thread(() -> {
            booleanLockTest.syncMethodTimeoutable(1000);
        })).forEach(Thread::start);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(booleanLockTest.lock.getBlockThreads());
    }
}
