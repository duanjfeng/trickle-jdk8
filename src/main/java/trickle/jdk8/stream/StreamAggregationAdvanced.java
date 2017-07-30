package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamAggregationAdvanced
 * <p>
 * Description: Stream reduce method demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamAggregationAdvanced {

    private static final Logger GLOBAL = Logger.getGlobal();

    private static void reduce() {
        // key.1. 最简单的形式，reduce的参数和返回值类型相同
        // 假设stream中元素为v0,v1...vn，那么结果为v0#v1#...#vn，其中#为操作
        Optional<Integer> reduce1 = Stream.iterate(1, x -> x + 1).limit(10).reduce((x, y) -> x + y);
        if (reduce1.isPresent()) {
            GLOBAL.info(String.join(":", "reduce1", String.valueOf(reduce1.get())));
        }

        // key.2. reduce的参数和返回值类型相同，增加了一个启动参数
        // 假设stream中元素为v0,v1...vn，启动参数为e，那么结果为e#v0#v1#...#vn，其中#为操作
        Integer reduce2 = Stream.iterate(1, x -> x + 1).limit(10).reduce(1000, (x, y) -> x + y);
        GLOBAL.info(String.join(":", "reduce1", String.valueOf(reduce2)));

        // key.3. reduce的参数和返回值类型不同
        // 例如，求字符串stream的长度之和
        List<String> list = Arrays.asList("a", "bc", "abc", "xyz");
        Integer reduce3 = list.stream().reduce(0, (total, s) -> total + s.length(), (total1, total2) -> total1 + total2);
        GLOBAL.info(String.join(":", "reduce3", String.valueOf(reduce3)));
        // 上述表达式过于复杂，一种简单的做法是使用map
        Integer reduce4 = list.stream().map(String::length).reduce(0, (x, y) -> x + y);
        GLOBAL.info(String.join(":", "reduce4", String.valueOf(reduce4)));
        // 更为简单的做法是使用特殊的stream类型，例如java.util.stream.IntStream
        int sum = list.stream().mapToInt(String::length).sum();
        GLOBAL.info(String.join(":", "sum", String.valueOf(sum)));
    }

    public static void main(String[] args) {
        reduce();
    }

}
