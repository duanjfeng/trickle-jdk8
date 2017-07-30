package trickle.jdk8.concurrent;

import java.util.concurrent.locks.StampedLock;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Locks
 * <p>
 * Description: StampedLock demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Locks {

    private static final Logger GLOBAL = Logger.getGlobal();

    // 辅助类，定义一个容器
    private static class InnerContainer {
        private int size;
        private Object[] elements;
        private StampedLock lock = new StampedLock();

        public InnerContainer(Object[] elements) {
            super();
            this.elements = elements;
            this.size = elements.length;
        }

        public Object get(int index) {
            // 获取乐观锁
            long stamp = lock.tryOptimisticRead();
            int currentSize = size;
            Object[] currentElements = elements;
            // 检验乐观锁是否依然有效
            if (!lock.validate(stamp)) {
                // 乐观锁失效，申请读锁
                stamp = lock.readLock();
                currentSize = size;
                currentElements = elements;
                // 释放锁
                lock.unlock(stamp);
            }
            Object o = null;
            if (index < currentSize) {
                o = currentElements[index];
            }
            return o;
        }
    }

    private static void stampedLock() {
        Object[] elements = new Object[] { "a", "b", "c" };
        InnerContainer container = new InnerContainer(elements);
        GLOBAL.info(() -> "result 1: " + container.get(2));
        GLOBAL.info(() -> "result 2: " + container.get(4));
    }

    public static void main(String[] args) {
        stampedLock();
    }

}
