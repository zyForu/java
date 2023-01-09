package my_test;

/**
 * @author zy
 * @date 2023/1/9 20:35
 */
public class InnerTask implements Runnable {
    private Queue runnableQueue = new RunnableQueue();

    @Override
    public void run() {
        try {
            Runnable task = runnableQueue.take();
            task.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
