package trickle.jdk8.concurrent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Title: HashMaps
 * <p>
 * Description: ConcurrentHashMap enhancements demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class HashMaps {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. compute方法以原子操作的形式更新当前key对应的value
     * <p>
     * 第1个参数为某个key；
     * <p>
     * 第2个参数为remappingFunction，该function参数为前面的key和对应的value，函数计算结果为key的新value
     */
    private static void compute() {
        ConcurrentHashMap<String, Integer> m = newMap();
        // 以原子操作的形式进行，避免读、修改、写3个操作依次执行不是原子操作的问题

        // 如果key为a的元素存在，则value值翻倍；否则保存一个新的entry，key为a，取值为0
        Integer compute = m.compute("a", (key, value) -> value == null ? 0 : value * 2);
        GLOBAL.info(() -> "compute: " + String.valueOf(compute));
    }

    /**
     * key.2. merge方法以原子操作的形式更新当前key对应的value
     * <p>
     * 第1个参数为某个key；
     * <p>
     * 如果key不存在，第2个参数为初始值；否则，作为后面remappingFunction的第2个参数值；
     * <p>
     * 第3个参数为remappingFunction，参数分别取原始值和merge方法第二个参数，函数计算结果为key的新value
     */
    private static void merge() {
        ConcurrentHashMap<String, Integer> m = newMap();
        // 如果key为xyz的值不存在，则取值为1；否则，当前值减去1
        Integer v1 = m.merge("xyz", 1, (existingValue, newValue) -> existingValue - newValue);
        GLOBAL.info(() -> "merge 1: " + String.valueOf(v1));
        // 如果key为def的值不存在，则取值为1；否则，当前值减去1
        Integer v2 = m.merge("def", 1, (existingValue, newValue) -> existingValue - newValue);
        GLOBAL.info(() -> "merge 2: " + String.valueOf(v2));
    }

    /**
     * key.3. search操作以并发形式对map进行搜索，按searchFunction返回
     * <p>
     * 第1个参数为并发阈值，只有map的元素数量超过该参数值才会并发执行；
     * <p>
     * 第2个参数为searchFunction，参数为所有key/value，返回函数计算的结果
     */
    private static void search() {
        ConcurrentHashMap<String, Integer> m = newMap();
        // 搜索长度为1的元素的key
        String search = m.search(1, (k, v) -> v == 1 ? k : null);
        GLOBAL.info(() -> "search: " + String.valueOf(search));
    }

    /**
     * key.4. reduce操作以并发形式对map进行聚合
     * <p>
     * 第1个参数为并发阈值，只有map的元素数量超过该参数值才会并发执行；
     * <p>
     * 第2个参数为transformer函数，实现一个entry的转换操作，入参为key和value，计算结果替换value；
     * <p>
     * 第3个参数为reducer函数，实现所有value的聚合，入参为两个value值；
     */
    private static void reduce() {
        ConcurrentHashMap<String, Integer> m = newMap();
        // 计算长度为奇数的元素的数量
        Integer reduce = m.reduce(1, (k, v) -> v % 2, (x, y) -> x + y);
        GLOBAL.info(() -> "reduce: " + String.valueOf(reduce));
    }

    /**
     * key.5. forEach操作遍历整个map
     */
    private static void forEach() {
        ConcurrentHashMap<String, Integer> m = newMap();
        final StringBuffer result = new StringBuffer();
        m.forEach((k, v) -> result.append(k).append("->").append(v).append(","));
        GLOBAL.info(() -> "forEach: " + String.valueOf(result.toString()));
    }

    /**
     * key.6. keySet操作返回一个线程安全的set对象
     */
    private static void keySet() {
        ConcurrentHashMap<String, Integer> m = newMap();
        KeySetView<String, Integer> keySet = m.keySet();
        Set<String> set = keySet;
        GLOBAL.info(() -> "keySet: " + String.valueOf(set.toString()));
    }

    private static ConcurrentHashMap<String, Integer> newMap() {
        ConcurrentHashMap<String, Integer> m = (ConcurrentHashMap<String, Integer>) Stream.of("a", "bc", "def")
            .collect(Collectors.toConcurrentMap(String::toString, String::length));
        return m;
    }

    public static void main(String[] args) {
        compute();
        merge();
        search();
        reduce();
        forEach();
        keySet();
    }

}
