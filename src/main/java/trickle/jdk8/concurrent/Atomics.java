package trickle.jdk8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Atomics
 * <p>
 * Description: Atomic enhancements demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Atomics {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 通过getAndUpdate/updateAndGet/getAndAccumulate/accumulateAndGet等方法简化乐观锁的实现代码
     */
    private static void getAndUpdate() {
        // 这里的例子是演示一个多线程并发扣库存的例子，一种使用jdk8之前的API实现，一种使用getAndUpdate实现

        // the count value may read from elsewhere.
        long count = 3;

        // implement 1: by compareAndSet and do...while
        AtomicLong balance = new AtomicLong(100);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                long current = 0;
                long newValue = 0;

                do {
                    // get current value
                    current = balance.get();
                    // compute new value
                    newValue = current - count;
                } while (!balance.compareAndSet(current, newValue));// if balance changed, compute again
            }
        };
        r1.run();
        GLOBAL.info(() -> "balance 1: " + balance.get());

        // implement 2: by getAndUpdate
        balance.set(100);
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                // Atomic operation
                balance.getAndUpdate(x -> x - count);
            }
        };
        r2.run();
        GLOBAL.info(() -> "balance 2: " + balance.get());
    }

    /**
     * key.2. 使用LongAccumulator/LongAdder等原子类提高原子变量的并发性能
     * <p>
     * LongAccumulator/LongAdder在内部将变量分成多个部分，在高并发写时分离热点（锁粒度更小）
     */
    private static void longAccumulator() {
        // 定义一个LongAccumulator，二元操作是求和，变量初始值是0
        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            final long j = i;
            pool.execute(() -> accumulator.accumulate(j));
        }
        try {
            Thread.sleep(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();

        GLOBAL.info(() -> "accumulated : " + accumulator.get());
    }

    public static void main(String[] args) {
        getAndUpdate();
        longAccumulator();
    }

}
