package trickle.jdk8.function;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: Suppliers
 * <p>
 * Description: Supplier demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Suppliers {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. Supplier，生产者，提供一个值
     */
    private static void supplier() {
        Supplier<Integer> s = () -> {
            Random r = new Random();
            return r.nextInt();
        };

        Optional<String> generated = Stream.generate(s).map(String::valueOf).findFirst();
        if (generated.isPresent()) {
            GLOBAL.info(() -> "generated: " + generated.get());
        }
    }

    public static void main(String[] args) {
        supplier();
    }

}
