package trickle.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Title: StreamCollect
 * <p>
 * Description: Stream collect method demos. Collect stream to Collection/String/Map/SummaryStatistics.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class StreamCollect {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. collect Stream to Collection
     */
    private static void toCollection() {
        // key.1. toList
        List<Integer> list1 = Stream.iterate(0, i -> i = i + 1).limit(5).collect(Collectors.toList());
        GLOBAL.info(() -> "list: " + list1.toString());

        // key.2. toSet
        Set<Long> set = Stream.generate(System::currentTimeMillis).limit(10).map(i -> i % 3).collect(Collectors.toSet());
        GLOBAL.info(() -> "set: " + set.toString());

        // key.3. to special sub Collection
        Collection<Integer> list2 = Stream.iterate(0, i -> i = i + 1).limit(5).collect(Collectors.toCollection(ArrayList::new));
        GLOBAL.info(() -> "list type: " + list2.getClass().getSimpleName());
    }

    /**
     * key.2. collect Stream to String
     */
    private static void toStringValue() {
        String stringValue = Stream.iterate(0, i -> i = i + 1).limit(5).map(String::valueOf).collect(Collectors.joining(","));
        GLOBAL.info(() -> "stringValue: " + stringValue);
    }

    /**
     * key.3. collect Stream to Map
     */
    private static void toMap() {
        List<String> list = Arrays.asList("a", "bcd", "ab", "gh", "ab");
        Map<String, Integer> map =
                list.stream().collect(Collectors.toMap(String::toString, String::length, (existingValue, newValue) -> existingValue));
        GLOBAL.info(() -> "map: " + map);
    }

    /**
     * key.4. collect Stream to SummaryStatistics
     */
    private static void toSummary() {
        List<String> list = Arrays.asList("a", "bcd", "ab", "gh", "ab");
        IntSummaryStatistics summary = list.stream().collect(Collectors.summarizingInt(String::length));
        GLOBAL.info(() -> "summary: " + summary.toString());

    }

    public static void main(String[] args) {
        toCollection();
        toStringValue();
        toMap();
        toSummary();
    }

}
