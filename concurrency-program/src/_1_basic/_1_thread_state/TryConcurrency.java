package _1_basic._1_thread_state;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) throws InterruptedException {

        new Thread(TryConcurrency::enjobMusic).start();
        browseNews();
        //enjobMusic();
    }

    public static void browseNews() throws InterruptedException {
        for( ; ; ) {
            System.out.println("aha,the good news .");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void enjobMusic() {
        for( ; ; ) {
            System.out.println("aha, the good music .");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
