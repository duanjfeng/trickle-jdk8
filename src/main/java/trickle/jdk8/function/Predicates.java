package trickle.jdk8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Predicates
 * <p>
 * Description: Predicate demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Predicates {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Predicate，接受1个参数，返回1个boolean值
     */
    private static void predicate() {
        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 10;
            }
        };
        boolean anyMatch = list.stream().anyMatch(predicate);
        GLOBAL.info(() -> "anyMatch: " + String.valueOf(anyMatch));
    }

    public static void main(String[] args) {
        predicate();
    }

}
