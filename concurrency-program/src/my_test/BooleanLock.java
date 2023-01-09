package my_test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author zy
 * @date 2023/1/9 19:15
 */

// 中断和超时后的内存溢出
// 提供了显示锁同步工具,应在同步方法中使用
public class BooleanLock implements Lock {
    private Thread currentThread;
    private List<Thread> blockedList = new LinkedList<>();
    private boolean isLocked = false;


    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while(isLocked) {
                if(!blockedList.contains(Thread.currentThread())){
                    blockedList.add(Thread.currentThread());
                    this.wait();
                }
            }
            this.currentThread = Thread.currentThread();
            this.isLocked = true;
            blockedList.remove(this.currentThread);
            System.out.println(this.currentThread.getName() + " get the lock.");
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        if(mills < 0) {
            throw new IllegalArgumentException("lock mills must >= 0");
        }else if(mills == 0) {
            lock();
        }else{
            synchronized (this) {
                long remain = mills;
                long endTime = System.currentTimeMillis() + remain;
                while(isLocked) {
                    if(remain < 0) {
                        throw new TimeoutException("during the" + mills + "ms, can not get the lock");
                    }
                    if(!blockedList.contains(Thread.currentThread())) {
                        this.blockedList.add(Thread.currentThread());
                    }
                    this.wait(mills);
                    remain = endTime - System.currentTimeMillis();
                }
                this.currentThread = Thread.currentThread();
                this.blockedList.remove(Thread.currentThread());
                this.isLocked = true;
                System.out.println(this.currentThread.getName() + " get the lock.");
            }
        }

    }

    @Override
    public void unlock() {
        synchronized (this) {
            if(isLocked) {
                this.currentThread = null;
                this.isLocked = false;
                System.out.println(Thread.currentThread().getName() + " release the lock");
                this.notifyAll();
            }
        }

    }

    @Override
    public int getBlockSize() {
        return this.blockedList.size();
    }
}
