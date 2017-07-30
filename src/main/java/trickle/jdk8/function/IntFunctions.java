package trickle.jdk8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.logging.Logger;

/**
 * <p>
 * Title: IntFunctions
 * <p>
 * Description: IntFunction demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class IntFunctions {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Function，1个入参1个出参，但是入参类型为int
     */
    private static void intFunction() {

        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");

        IntFunction<String[]> generator = new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        };

        // 这里toArray的参数为一个IntFunction，根据给定的类型和长度构造一个数组
        String[] array = list.stream().toArray(generator);

        GLOBAL.info(() -> "array length: " + array.length);
        GLOBAL.info(() -> "array[0]: " + array[0].toString());
    }

    public static void main(String[] args) {
        intFunction();
    }

}
