package trickle.jdk8.function;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Functions
 * <p>
 * Description: Function demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Functions {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Function，1个入参1个出参
     */
    private static void function() {

        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");

        Function<String, Integer> mapper = new Function<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t.length();
            }
        };

        Optional<Integer> result = list.stream().map(mapper).max((x, y) -> x.compareTo(y));

        if (result.isPresent()) {
            GLOBAL.info(() -> "result: " + result.get());
        }
    }

    public static void main(String[] args) {
        function();
    }

}
