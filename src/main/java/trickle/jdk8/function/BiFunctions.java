package trickle.jdk8.function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: BiFunctions
 * <p>
 * Description: BiFunction demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class BiFunctions {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. BiFunction，2个入参1个出参的函数
     */
    private static void biFunction() {

        // reduce的3个参数
        // identity，是combiner函数的恒等式，对于任意的u，combiner(identity, u)=u，identity与combiner参数类型相同
        // accumulator，聚集函数，对于stream的任一元素，如果是首次执行则与identity聚集计算；否则与上一次的结果聚集计算
        // combiner，合并多个聚集函数的结果，与accumulator有相同的返回类型
        Set<String> identity = new HashSet<>();

        // 本例中入参类型分别为Set<String>、String，返回值类型为Set<String>
        BiFunction<Set<String>, String, Set<String>> accumulator = (set, s) -> {
            StringTokenizer tokenizer = new StringTokenizer(s);
            while (tokenizer.hasMoreTokens()) {
                set.add(tokenizer.nextToken());
            }
            return set;
        };

        BinaryOperator<Set<String>> combiner = (s1, s2) -> {
            s1.addAll(s2);
            return s1;
        };

        String filePath = "./src/main/java/trickle/jdk8/function/BiFunctions.java";

        // Files.lines
        // 读取文件流的行
        try (Stream<String> lines = Files.lines(Paths.get(filePath)).onClose(() -> GLOBAL.info("stream closing"))) {
            Set<String> set = lines.reduce(identity, accumulator, combiner);
            GLOBAL.info(() -> "distinct words size: " + set.size());
        }
        catch (IOException e) {
            GLOBAL.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        biFunction();
    }

}
