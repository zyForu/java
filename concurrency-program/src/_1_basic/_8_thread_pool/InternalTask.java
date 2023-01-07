package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 16:05
 */
public class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            Runnable take = null;
            try {
                take = runnableQueue.take();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
            take.run();
        }
    }

    public void stop() {
        this.running = false;
    }
}
