package my_test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zy
 * @date 2023/1/9 20:02
 */
public class RunnableQueue implements Queue {
    private LinkedList<Runnable> runnableList = new LinkedList<>();
    private final int maxSize;
    private final static int DEFAULT_MAX_SIZE = 1000;
    private final DenyPolicy denyPolicy;

    public RunnableQueue() {
        this(DEFAULT_MAX_SIZE, new DenyPolicy.AbortDenyPolicy());
    }

    public RunnableQueue(int maxSize, DenyPolicy denyPolicy) {
        this.maxSize = maxSize;
        this.denyPolicy = denyPolicy;
    }



    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if(runnableList.size() >= maxSize) {
                denyPolicy.deny(runnable);
            }else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }

        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (this.runnableList.isEmpty()) {
                runnableList.wait();
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int getQueueSize() {
        return this.runnableList.size();
    }
}
