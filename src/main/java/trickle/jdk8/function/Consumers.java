package trickle.jdk8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Consumers
 * <p>
 * Description: Consumer demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Consumers {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Consumer，消费者，处理一个值
     */
    private static void consumer() {

        Consumer<? super String> action = x -> {
            if (x.startsWith("a")) {
                GLOBAL.info(x);
            }
            else {
                String y = "a" + x;
                GLOBAL.info(y);
            }
        };

        List<String> list = Arrays.asList("abc", "bcedf", "abcde", "zcdfx");
        list.stream().forEach(action);
    }

    public static void main(String[] args) {
        consumer();
    }

}
