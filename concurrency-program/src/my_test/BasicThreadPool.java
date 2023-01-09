package my_test;

import java.util.ArrayDeque;

/**
 * @author zy
 * @date 2023/1/9 20:38
 */
public class BasicThreadPool implements ThreadPool{
    private final int maxSize;
    private final int coreSize;
    private final int initSize;
    private RunnableQueue runnableQueue;

    private final Thread maintainThread = new Thread(() -> {

    });

    public BasicThreadPool(int initSize, int coreSize, int maxSize, )





    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public void getInitSize() {

    }

    @Override
    public void getMaxSize() {

    }

    @Override
    public void getCoreSize() {

    }

    @Override
    public int getQueueSize() {
        return 0;
    }
}
