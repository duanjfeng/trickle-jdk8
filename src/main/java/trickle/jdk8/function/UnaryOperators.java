package trickle.jdk8.function;

import java.util.IntSummaryStatistics;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 * Title: UnaryOperators
 * <p>
 * Description: UnaryOperator demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class UnaryOperators {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. UnaryOperator，1个入参1个出参，类型相同
     */
    private static void unaryOperator() {
        UnaryOperator<Integer> f = new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t + 2;
            }
        };
        ToIntFunction<Integer> mapper = new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value.intValue();
            }
        };
        // iterate产生一个stream，值依次为seed, f(seed), f(f(seed)), ...
        // 本例中产生一个包含100个奇数的序列
        IntStream intStream = Stream.iterate(1, f).limit(100).mapToInt(mapper);
        IntSummaryStatistics summaryStatistics = intStream.summaryStatistics();
        GLOBAL.info(() -> "summaryStatistics: " + summaryStatistics.toString());
    }

    public static void main(String[] args) {
        unaryOperator();
    }

}
