package my_test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zy
 * @date 2023/1/9 19:42
 */
public class LockTest {
    public static void main(String[] args) {
        BooleanLock booleanLock = new BooleanLock();
        IntStream.range(0,2).mapToObj(i -> new Thread(() -> {
            try {
                booleanLock.lock();
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                booleanLock.unlock();
            }
        })).forEach(Thread::start);

    }
}
