package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamConstruct
 * <p>
 * Description: Construct stream demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamConstruct {

    private static final Logger GLOBAL = Logger.getGlobal();

    private static void construct() {

        // key.1.from collection
        // 通过集合的stream()方法
        List<String> list = Arrays.asList("a", "ab");
        list.stream().reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);

        // key.2.通过Stream的静态方法of
        Stream.of("e", "fg").reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);

        // key.3.通过Stream的静态方法generate
        // 产生一个值由方法参数产生的无限流
        Stream.generate(System::currentTimeMillis).limit(2).map(String::valueOf).reduce((x, y) -> String.join(",", x, y))
            .ifPresent(GLOBAL::info);

        // key.4.通过Stream的静态方法iterate
        // 产生一个值按照构造规则计算的无限流
        Stream.iterate(0, n -> n + 1).limit(10).filter(n -> n < 2).map(String::valueOf).reduce((x, y) -> String.join(",", x, y))
            .ifPresent(GLOBAL::info);

    }

    public static void main(String[] args) {
        construct();
    }

}
