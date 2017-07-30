package trickle.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamMap
 * <p>
 * Description: Stream map/flatMap methods demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamMap {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. map, 映射
     * <p>
     * 将stream中每个元素按照map中的方法映射为新的元素，形成一个新的stream
     */
    private static void map() {
        List<String> list = Arrays.asList("s t r e a m", "l i s t", "a r r a y s");
        list.stream().map(String::length).distinct().map(String::valueOf).reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    /**
     * 
     * key.2. flatMap, 展开式映射
     * <p>
     * 将stream中每个元素按照flatMap中的方法映射为stream，然后将这些stream展开合并为一个新的stream
     */
    private static void flatMap() {
        List<String> list = Arrays.asList("s t r e a m", "l i s t", "a r r a y s");
        Stream<String> stream = list.stream().flatMap((String s) -> {
            // map parameter to a stream
            return Stream.of(s.split(" "));
        });
        stream.reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    public static void main(String[] args) {
        map();
        flatMap();
    }

}
