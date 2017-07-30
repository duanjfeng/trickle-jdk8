package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamParallel
 * <p>
 * Description: Stream parallel demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamParallel {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * Parallel stream will run more fast.
     */
    private static void parallel() {

        // key.1. parallel method
        String parallel = Stream.iterate(1, i -> i = i + 1).parallel().limit(10).map(String::valueOf).collect(Collectors.joining(","));
        GLOBAL.info(() -> "parallel:" + parallel);

        // key.2. parallelStream method
        List<String> list = Arrays.asList("a", "bcd", "ab", "gh", "ab");
        String parallelStream = list.parallelStream().collect(Collectors.joining(","));
        GLOBAL.info(() -> "parallelStream:" + parallelStream);

    }

    public static void main(String[] args) {
        parallel();
    }

}
