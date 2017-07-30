package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamFilter
 * <p>
 * Description: Stream filter methods demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamFilter {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. filter, 过滤
     * <p>
     * 使用filter和java.util.function.Predicate来滤除stream中的元素
     */
    private static void filter() {
        List<String> list = Arrays.asList("stream", "list", "filter", "strange");

        Stream<String> filtered = list.stream().filter(s -> {
            return s.length() > 4;
        }).filter(s -> s.startsWith("s"));
        GLOBAL.info(String.valueOf(filtered.findAny().isPresent()));

        filtered = list.stream().filter(s -> {
            return s.length() > 4;
        }).filter(s -> s.startsWith("s"));
        // 任意匹配
        GLOBAL.info(String.valueOf(filtered.anyMatch(s -> s.endsWith("m"))));

        filtered = list.stream().filter(s -> {
            return s.length() > 4;
        }).filter(s -> s.startsWith("s"));
        // 全部匹配
        GLOBAL.info(String.valueOf(filtered.allMatch(s -> s.endsWith("m"))));

    }

    public static void main(String[] args) {
        filter();
    }

}
