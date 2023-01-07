package _1_basic._6_thread_group;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2023/1/7 14:13
 */
public class ThreadGroupTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group");
        Thread thread = new Thread(group, () -> {
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println("activeCount=" + mainGroup.activeCount());
        System.out.println("activeGroupCount=" + mainGroup.activeGroupCount());
        System.out.println("getMaxPriority=" + mainGroup.getMaxPriority());
        System.out.println("getName=" + mainGroup.getName());
        System.out.println("getParent=" + mainGroup.getParent());
        mainGroup.list();
        System.out.println("=====================");
        System.out.println("parentOf=" + mainGroup.parentOf(group));
        System.out.println("parentOf=" + mainGroup.parentOf(mainGroup));
    }
}
