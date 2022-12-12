package _1_basic._1_thread_state.ticketWindow;

import java.util.concurrent.TimeUnit;

public class ticketWindowRunnable implements Runnable {
    public static final int MAX = 50;
    public int index = 0;


    @Override
    public void run() {
        while(index < MAX) {
            System.out.println(Thread.currentThread().getName() + ",号牌: " + (++index) + "办理业务");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ticketWindowRunnable ticketWindowRunnable = new ticketWindowRunnable();
        new Thread(ticketWindowRunnable, "一号窗口").start();
        new Thread(ticketWindowRunnable, "二号窗口").start();
        new Thread(ticketWindowRunnable, "三号窗口").start();
        new Thread(ticketWindowRunnable, "四号窗口").start();
    }
}
