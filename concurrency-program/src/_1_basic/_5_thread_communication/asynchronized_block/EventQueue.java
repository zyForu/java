package _1_basic._5_thread_communication.asynchronized_block;

import java.util.LinkedList;

/**
 * @author zy
 * @date 2023/1/6 20:21
 */
public class EventQueue {

    static class Event{

    }
    private final int max;
    private final LinkedList<Event> queue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (queue) {
            if(queue.size() >= max) {
                System.out.println("queue is full.");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(event);
            queue.notify();
            System.out.println( event + " is submitted");

            /*if(queue.size() < max) {
                queue.addLast(event);
                queue.notify();
            }else{
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

        }
    }

    public void take() {
        synchronized (queue) {
            if(queue.isEmpty()) {
                System.out.println("queue is empty");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = queue.getFirst();
            queue.notify();
            System.out.println(event + "is handled");
        }

    }
}
