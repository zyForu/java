package my_test;

/**
 * @author zy
 * @date 2023/1/9 20:00
 */
public class RunnableDenyException extends RuntimeException{
    public RunnableDenyException(String msg) {
        super(msg);
    }
}
