package _1_basic._5_thread_communication.asynchronized_block;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zy
 * @date 2023/1/6 20:46
 */
public class EventClient {
    public static void main1(String[] args) {
        EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for( ; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "producer").start();

        new Thread(() -> {
            for( ; ;) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

    public static void main(String[] args) {
        MultiThreadEventQueue queue = new MultiThreadEventQueue();
        IntStream.range(0,5).mapToObj( i -> new Thread(() -> {
            while(true) {
                queue.offer(new EventQueue.Event());
            }
        }, "producer" + i)).forEach(Thread::start);

        IntStream.range(0,2).mapToObj( i -> new Thread(() -> {
            while(true) {
                queue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer" + i)).forEach(Thread::start);
    }

}
