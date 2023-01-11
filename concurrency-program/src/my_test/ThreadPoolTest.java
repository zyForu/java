package my_test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zy
 * @date 2023/1/11 18:38
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new BasicThreadPool(2, 4, 6, new DenyPolicy.AbortDenyPolicy());
        List<Thread> threads = IntStream.range(0, 20).mapToObj(i -> new Thread(() -> {
            try {

                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + " is running and done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).collect(Collectors.toList());

        threads.forEach(threadPool::execute);

        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("queue size : " +threadPool.getQueueSize());
            System.out.println("active count : " + threadPool.getActiveCount());
        }
    }
}
