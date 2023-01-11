package my_test;

/**
 * @author zy
 * @date 2023/1/9 20:35
 */
public class InnerTask implements Runnable {

    // !!!此处工作线程都是对统一任务队列操作,不同对象wait后再也不会被唤醒
    //private Queue runnableQueue = new RunnableQueue();

    // 内部线程必须在停止标识running,因为移除线程时保证线程当前任务执行结束
    private boolean running  = true;
    private final Queue runnableQueue;

    public InnerTask(Queue queue) {
        this.runnableQueue = queue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
