package trickle.jdk8.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * <p>
 * Title: BiConsumers
 * <p>
 * Description: BiConsumer demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class BiConsumers {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. BiConsumer，二元消费者，有2个入参，没有返回值
     */
    private static void biConsumer() {

        List<String> list = Arrays.asList("xxxx", "adab", "cllfd", "ddddfes");

        // Supplier提供collect的容器
        // 此处为一个Map
        Supplier<Map<String, Integer>> supplier = () -> {
            return new HashMap<String, Integer>();
        };

        // accumulator（BiConsumer）提供单个元素的聚集方法
        // 此处将单个元素信息（元素及其长度）添加到Map
        BiConsumer<Map<String, Integer>, String> accumulator = (m, x) -> {
            m.put(x, x.length());
        };

        // combiner（BiConsumer）提供容器的聚集方法
        // 此处将两个容器合并
        BiConsumer<Map<String, Integer>, Map<String, Integer>> combiner = (m1, m2) -> {
            m1.putAll(m2);
        };

        Map<String, Integer> result = list.stream().collect(supplier, accumulator, combiner);
        GLOBAL.info(() -> "result: " + result.toString());
    }

    public static void main(String[] args) {
        biConsumer();
    }

}
