package trickle.jdk8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * Title: LambdaSyntaxs
 * <p>
 * Description: Lambda expression syntax demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class LambdaSyntaxs {

    private static final Logger GLOBAL = Logger.getGlobal();

    private static final List<String> STRINGS = Arrays.asList("Ab", "abcd", "aBcd", "C");

    /**
     * key.1. 不使用lambda表达式
     */
    private static void noLambda() {

        STRINGS.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        GLOBAL.info(String.join(":", "noLambda", STRINGS.toString()));
    }

    /**
     * key.2. 基本的lambda语法
     * <p>
     * parameters -> expressions
     */
    private static void baseSyntax() {
        STRINGS.sort((String s1, String s2) -> {
            return s1.compareToIgnoreCase(s2);
        });
        GLOBAL.info(String.join(":", "baseSyntax", STRINGS.toString()));
    }

    /**
     * key.3. 类型推导（省去参数类型定义）
     */
    private static void typeInference() {
        STRINGS.sort((s1, s2) -> {
            return s1.compareToIgnoreCase(s2);
        });
        GLOBAL.info(String.join(":", "baseSyntax", STRINGS.toString()));
    }

    /**
     * key.4. 方法引用
     */
    private static void methodReferences() {
        STRINGS.sort(String::compareToIgnoreCase);
        GLOBAL.info(String.join(":", "methodReferences", STRINGS.toString()));
    }

    public static void main(String[] args) {
        noLambda();
        baseSyntax();
        typeInference();
        methodReferences();
    }

}
