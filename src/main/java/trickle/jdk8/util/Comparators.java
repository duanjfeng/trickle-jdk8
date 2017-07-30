package trickle.jdk8.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>
 * Title: Comparators
 * <p>
 * Description: Comparator demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Comparators {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Comparator的静态函数，用以快速构造Comparator
     */
    private static void comparator() {
        // Comparator.comparing
        List<String> list = Arrays.asList("abc", "de", "fgh", "ijk", "axy");
        String sorted = list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.joining(","));
        GLOBAL.info(() -> "sorted: " + sorted.toString());

        // Comparator.reversed
        String sorted2 = list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.joining(","));
        GLOBAL.info(() -> "sorted 2: " + sorted2.toString());

        // Comparator.thenComparing
        String sorted3 = list.stream().sorted(Comparator.comparing(String::length).thenComparing(x -> {
            return x.charAt(0);
        })).collect(Collectors.joining(","));
        GLOBAL.info(() -> "sorted 3: " + sorted3.toString());

    }

    public static void main(String[] args) {
        comparator();
    }

}
