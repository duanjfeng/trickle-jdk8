package trickle.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * Title: MethodReferences
 * <p>
 * Description: Method references demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class MethodReferences {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 调用对象的实例方法
     * <p>
     * The given method of an object will run on each element, which is the argument.
     */
    private static void invokeInstanceMethod() {
        List<String> list = Arrays.asList("a", "b", "ab");
        // GLOBAL实例的info方法
        list.stream().reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    /**
     * key.2. 调用类的静态方法
     * <p>
     * The given static method of a Class will run on each element, which is the argument.
     */
    private static void invokeStaticMethod() {
        List<Integer> list = Arrays.asList(1, 5, 15, 20, 30);
        // String类的valueOf静态方法
        list.parallelStream().map(String::valueOf).reduce((x, y) -> String.join(",", x, y)).ifPresent(GLOBAL::info);
    }

    /**
     * key.3. 调用类的实例方法
     * <p>
     * The given instance method of a Class will run on each pair element, the first is the caller.
     */
    private static void invokeClassInstanceMethod() {
        List<String> list = Arrays.asList("x", "y", "z");
        // String的实例方法concat
        list.stream().reduce(String::concat).ifPresent(GLOBAL::info);
    }

    /**
     * key.4. 调用构造函数
     * <p>
     * The given constructor will run on each element, which is the argument.
     */
    private static void invokeConstructor() {
        List<String> list = Arrays.asList("1", "5", "15", "20", "30");
        // Integer的构造函数
        list.parallelStream().map(Integer::new).max(Integer::compareTo).ifPresent(i -> {
            GLOBAL.info(String.valueOf(i));
        });
    }

    public static void main(String[] args) {
        invokeInstanceMethod();
        invokeStaticMethod();
        invokeClassInstanceMethod();
        invokeConstructor();
    }

}
