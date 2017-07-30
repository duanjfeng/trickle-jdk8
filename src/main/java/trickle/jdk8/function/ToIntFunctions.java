package trickle.jdk8.function;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * <p>
 * Title: ToIntFunctions
 * <p>
 * Description: ToIntFunction demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class ToIntFunctions {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. ToIntFunction，接受1个参数，返回1个int值
     */
    private static void toIntFunction() {

        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");

        ToIntFunction<String> mapper = new ToIntFunction<String>() {
            @Override
            public int applyAsInt(String value) {
                return value.length();
            }
        };

        IntStream intStream = list.stream().mapToInt(mapper);
        IntSummaryStatistics summaryStatistics = intStream.summaryStatistics();
        GLOBAL.info(() -> "summaryStatistics: " + summaryStatistics.toString());
    }

    public static void main(String[] args) {
        toIntFunction();
    }

}
