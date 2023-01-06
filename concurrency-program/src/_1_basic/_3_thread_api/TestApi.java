package _1_basic._3_thread_api;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zy
 * @date 2023/1/4 20:13
 */
public class TestApi {

    // 测试getId
    public static void main1(String[] args) {
        IntStream.range(0,2).mapToObj(TestApi::creadThread).forEach(Thread::start);
        System.out.println("main thread's id is " + Thread.currentThread().getId());
    }

    // 测试interrupt
    public static void main2(String[] args) throws InterruptedException {
        Thread t = new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+" start");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" is interrupted");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" over");
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
        System.out.println(Thread.currentThread().getName() + " over");

    }


    // 测试IsInterrupted,interrupted
    public static void main3(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while(true){
                /*if(Thread.currentThread().isInterrupted()) {
                    System.out.println("is interrupted true");
                }*/
                if(Thread.interrupted()) {
                    System.out.println("is interrupted true");
                }
            }

        });
        t.setDaemon(true);
        t.start();
        TimeUnit.NANOSECONDS.sleep(1);
        t.interrupt();
        String abac = new String("abac");
        abac.wait();
    }

    // 测试join
    public static void main(String[] args) {
        System.out.println("main begin");
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+ " start");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " over");
        });
        t.start();
        try {
            t.join(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }























    public static Thread creadThread(int i) {
        return new Thread(() -> {
            if (i == 0)
                Thread.yield();
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getId());
        }, "Thead"+ i);
    }

}
