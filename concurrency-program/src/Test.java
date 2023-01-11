import java.util.concurrent.TimeUnit;

public class Test {

    private Object mute = new Object();
    public static void main(String[] args) {
        Test test = new Test();
        new Thread( ()-> {
            synchronized (test.mute) {
                try {
                    test.mute.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread( ()-> {
            synchronized (test.mute) {
                try {
                    test.mute.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread( ()-> {
            synchronized (test.mute) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    test.mute.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
