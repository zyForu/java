package _1_basic._7_hook_thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2023/1/7 14:47
 */
public class UncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1 / 0);
        });
        thread.setUncaughtExceptionHandler((t,e) -> {
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });

        thread.start();
    }
}
