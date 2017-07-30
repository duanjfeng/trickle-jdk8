package trickle.jdk8.function;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;

/**
 * <p>
 * Title: BinaryOperators
 * <p>
 * Description: BinaryOperator demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class BinaryOperators {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. BinaryOperator，是BiFunction的子接口，与BiFunction相同，都是2个入参1个出参，差别是BinaryOperator的出入参数类型相同
     */
    private static void binaryOperator() {

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int len1 = s1.length();
                int len2 = s2.length();
                return len1 - len2;
            }
        };

        // 根据Comparator生成BinaryOperator
        // maxBy取2个参数中的最大值
        BinaryOperator<String> maxBy = BinaryOperator.maxBy(comparator);

        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");

        String reduce = list.stream().reduce("", maxBy);

        GLOBAL.info(() -> "reduce: " + reduce);

    }

    public static void main(String[] args) {
        binaryOperator();
    }

}
