package _1_basic._5_thread_communication.asynchronized_block;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2023/1/7 10:56
 */
public class MultiThreadEventQueue {
    private LinkedList<EventQueue.Event> queue = new LinkedList<>();
    private final int max ;
    private static final int DEFAULT_MAX = 10;

    public MultiThreadEventQueue() {
        this.max = DEFAULT_MAX;
    }

    public MultiThreadEventQueue(int max) {
        this.max = max;
    }

    public void offer(EventQueue.Event event) {
        synchronized (queue) {
            while(queue.size() >= max) {
                System.out.println(Thread.currentThread().getName() + ":" + "the queue is full.");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(event);
            queue.notifyAll();
            System.out.println(Thread.currentThread().getName() + ":" +event + "is submitted");
        }
    }

    public void take() {
        synchronized (queue) {
            while(queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ":" +"the queue is empty");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            EventQueue.Event event = queue.removeFirst();
            queue.notifyAll();
            System.out.println(Thread.currentThread().getName() + ":" +event + "is handled");

        }
    }


}
