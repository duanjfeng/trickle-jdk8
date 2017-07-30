package trickle.jdk8.stream;

import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamSelector
 * <p>
 * Description: Stream limit/skip/peek/concat methods demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamSelector {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. limit, 取前n个元素构成新的stream
     */
    private static void limit() {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(5).map(String::valueOf).reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    /**
     * key.2. skip, 跳过前n个元素构成新的stream
     */
    private static void skip() {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.skip(100).limit(5).map(String::valueOf).reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    /**
     * key.3. peek, 获取元素时执行参数指定的操作
     * <p>
     * 可以用于调试
     */
    private static void peek() {

        // 下面3个例子用以对比stream的延迟执行效果
        // 调用1会获取1到15所有元素，并打印输出
        // 调用2和3只会获取11到15，并打印输出
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.peek(System.out::print).skip(10).limit(5).map(String::valueOf).reduce((x, y) -> String.join(",", x, y))
            .ifPresent(GLOBAL::info);

        stream = Stream.iterate(1, x -> x + 1);
        stream.skip(10).peek(System.out::print).limit(5).map(String::valueOf).reduce((x, y) -> String.join(",", x, y))
            .ifPresent(GLOBAL::info);

        stream = Stream.iterate(1, x -> x + 1);
        stream.skip(10).limit(5).peek(System.out::print).map(String::valueOf).reduce((x, y) -> String.join(",", x, y))
            .ifPresent(GLOBAL::info);
    }

    /**
     * key.4. concat, 拼接stream为新的stream
     */
    private static void concat() {
        Stream<String> stream1 = Stream.iterate("a", x -> x.concat(x));
        Stream<String> stream2 = Stream.iterate("b", x -> x.concat(x));
        Stream<String> concated = Stream.concat(stream1.limit(5), stream2.limit(5));
        GLOBAL.info(String.valueOf(concated.count()));
    }

    public static void main(String[] args) {
        limit();
        skip();
        peek();
        concat();
    }

}
