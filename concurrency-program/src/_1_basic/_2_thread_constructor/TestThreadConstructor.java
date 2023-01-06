package _1_basic._2_thread_constructor;

/**
 * @author zy
 * @date 2022/12/14 18:28
 */
public class TestThreadConstructor {
    public static void main(String[] args) {
        // 默认命令的构造函数
        Thread t1 = new Thread();
        Thread t2 = new Thread(() -> {
            System.out.println("over");
        });
    }
}
