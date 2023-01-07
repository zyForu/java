package _1_basic._8_thread_pool;

/**
 * @author zy
 * @date 2023/1/7 15:57
 */
@FunctionalInterface
public interface DenyPolicy {
    void reject(Runnable runnable, ThreadPool threadPool);

    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("the runnable " + runnable + "will be abort.");
        }
    }

    class RunDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if(!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
