package _1_basic._8_thread_pool;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2023/1/7 17:32
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for(int i = 0; i < 1; i++) {
            threadPool.execute( ()-> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "is running and done.");

            });
        }

       /* for( ; ;) {
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSiez:" + threadPool.getMaxSize());
            System.out.println("workSize:" + ((BasicThreadPool) threadPool).getThreadQueueSize());
            System.out.println("=====================");
            TimeUnit.SECONDS.sleep(5);
        }*/

       TimeUnit.SECONDS.sleep(12);
       threadPool.shutdown();

    }
}
