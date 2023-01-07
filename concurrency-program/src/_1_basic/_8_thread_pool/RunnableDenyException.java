package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 16:01
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String msg) {
        super(msg);
    }
}
