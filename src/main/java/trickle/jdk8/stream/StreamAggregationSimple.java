package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * <p>
 * Title: StreamAggregationSimple
 * <p>
 * Description: Stream count/max/min/findFirst/findAny/anyMatch/allMatch/noneMatch methods demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamAggregationSimple {

    private static final Logger GLOBAL = Logger.getGlobal();

    private static void simpleAggregation() {
        List<String> list = Arrays.asList("a", "bc", "abc", "xyz");

        // key.1. count, 返回元素数量
        long count = list.stream().count();
        GLOBAL.info(String.join(":", "count", String.valueOf(count)));

        // key.2. max, 返回stream中最大值
        Optional<String> max = list.stream().max(String::compareTo);
        max.ifPresent(s -> {
            GLOBAL.info(String.join(":", "max", s));
        });

        // key.3. min, 返回stream中最小值
        Optional<String> min = list.stream().min(String::compareTo);
        min.ifPresent(s -> {
            GLOBAL.info(String.join(":", "min", s));
        });

        // key.4. findFirst, 返回stream第一个元素
        Optional<String> first = list.stream().findFirst();
        first.ifPresent(s -> {
            GLOBAL.info(String.join(":", "first", s));
        });

        // key.5. findAny, 返回stream任意一个元素
        Optional<String> any = list.stream().findAny();
        any.ifPresent(s -> {
            GLOBAL.info(String.join(":", "any", s));
        });

        // key.6. anyMatch, 是否stream中存在一个元素满足predicate
        boolean anyMatch = list.stream().anyMatch(s -> s.startsWith("a"));
        GLOBAL.info(String.join(":", "anyMatch", String.valueOf(anyMatch)));

        // key.7. allMatch, 是否stream中所有元素都满足predicate
        boolean allMatch = list.stream().allMatch(s -> !s.isEmpty());
        GLOBAL.info(String.join(":", "allMatch", String.valueOf(allMatch)));

        // key.8. noneMatch, 是否stream中所有元素都不满足predicate
        boolean noneMatch = list.stream().noneMatch(s -> s.matches("\\d+"));
        GLOBAL.info(String.join(":", "noneMatch", String.valueOf(noneMatch)));

    }

    public static void main(String[] args) {
        simpleAggregation();
    }

}
