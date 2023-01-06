public class Test {
    public static void main(String[] args) {
        Thread hello = new Thread() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        Thread thread = new Thread(hello::run);
        thread.start();

    }
}
