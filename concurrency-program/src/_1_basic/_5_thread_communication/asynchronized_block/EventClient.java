package _1_basic._5_thread_communication.asynchronized_block;

/**
 * @author zy
 * @date 2023/1/6 20:46
 */
public class EventClient {
    public static void main(String[] args) {
        EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            eventQueue.offer(new EventQueue.Event());
        }, "producer").start();

        new Thread(() -> {
            eventQueue.take();
        }, "consumer");
    }
}
