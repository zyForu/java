package my_test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zy
 * @date 2023/1/9 20:38
 */
public class BasicThreadPool implements ThreadPool{
    private final int maxSize;
    private final int coreSize;
    private final int initSize;
    private RunnableQueue runnableQueue;
    private Queue<Thread> workQueue = new ArrayDeque<>();
    private final DenyPolicy denyPolicy;
    private boolean isShutdown = false;
    private final long keepAlive;
    private int activeCount;


    private final Thread maintainThread = new Thread(() -> {

    });

    public BasicThreadPool(int initSize, int coreSize, int maxSize, DenyPolicy denyPolicy) {
        this.initSize = initSize;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.denyPolicy = denyPolicy;
        runnableQueue = new RunnableQueue(); // 默认任务队列
        this.keepAlive = 1000;
        this.init();
    }

    private void init() {
        this.maintainThread.run();
        for(int i = 0 ; i < initSize; i++) {
            newThread();
        }
    }


    private void newThread() {
        InnerTask innerTask = new InnerTask();
        Thread thread = new Thread(innerTask);
        workQueue.add(thread);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        Thread thread = workQueue.remove();
        thread.interrupt();
        this.activeCount--;
    }



    @Override
    public void execute(Runnable runnable) {
        if(this.isShutdown) {

        }else {
            runnableQueue.offer(runnable);
        }
    }

    @Override
    public int getInitSize() {
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        return this.runnableQueue.getQueueSize();
    }
}
