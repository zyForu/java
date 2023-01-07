package _1_basic._5_thread_communication.booleanlock;

import javax.swing.plaf.BorderUIResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author zy
 * @date 2023/1/7 12:02
 */
public class BooleanLock implements Lock {
    private Thread currentThread;
    private Boolean locked = false;
    private List<Thread> blockedList = new ArrayList<>();


    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while(locked) {
                if(!blockedList.contains(Thread.currentThread())) {
                    blockedList.add(Thread.currentThread());
                }
                try {
                    this.wait();
                }catch (InterruptedException e) {
                    blockedList.remove(Thread.currentThread()); //避免内存溢出
                    throw e;
                }
            }
            blockedList.remove(Thread.currentThread());
            currentThread = Thread.currentThread();
            locked = true;
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if(mills < 0) {
                this.lock();
            }else {
                long remainMills = mills;
                long endMills = System.currentTimeMillis() + remainMills;
                while (locked) {
                    if(remainMills <= 0) {
                        blockedList.remove(Thread.currentThread());
                        throw new TimeoutException("can not get the lock during " + mills + "ms");
                    }
                    if(!blockedList.contains(Thread.currentThread())) {
                        blockedList.add(Thread.currentThread());
                    }
                    try {
                        this.wait(remainMills);
                    }catch (InterruptedException e) {
                        blockedList.remove(Thread.currentThread());
                        throw e;
                    }
                    remainMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                currentThread = Thread.currentThread();
                locked = true;
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if(currentThread == Thread.currentThread()){
                this.locked = false;
                this.currentThread = null;
                Optional.of(Thread.currentThread().getName() + " release the lock.").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
