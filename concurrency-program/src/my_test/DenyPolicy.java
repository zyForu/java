package my_test;

/**
 * @author zy
 * @date 2023/1/9 19:54
 */
public interface DenyPolicy {
    void deny(Runnable runnable);

    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void deny(Runnable runnable) {

        }
    }

    class runDenyPolicy implements DenyPolicy{

        @Override
        public void deny(Runnable runnable) {
            runnable.run();
        }
    }

    class noticePolicy implements DenyPolicy{

        @Override
        public void deny(Runnable runnable) {
            throw new RunnableDenyException("the runnable queue is full");
        }
    }
}
