package trickle.jdk8.lang;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

/**
 * <p>
 * Title: ReflectParameters
 * <p>
 * Description: java.lang.reflect.Parameter demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class ReflectParameters {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 方法参数类java.lang.reflect.Parameter
     * <p>
     * 作用之一是可以获取方法参数的名称。这需要在javac编译时添加-parameters参数。
     * 在eclipse中修改Compiler，打开store information about method parameters。
     * <p>
     * 类似mybatis-3这类框架可以通过这个机制简化mapper方法。在jdk8之前无法获取参数名称，因此他们要求
     * If you wish to change the name of the parameters (multiple only), then you
     * can use the @Param("paramName") annotation on the parameter.
     */
    private static void reflectParameter() {
        try {
            Method declaredMethod = ReflectParameters.class.getDeclaredMethod("login", String.class, String.class);
            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter p : parameters) {
                String name = p.getName();
                Class<?> type = p.getType();
                GLOBAL.info(() -> "name: " + name + "; type: " + type.toString());
            }
        }
        catch (ReflectiveOperationException e) {
            GLOBAL.severe(e.getMessage());
        }
    }

    // 辅助函数，无实际意义
    @SuppressWarnings("unused")
    private String login(String name, String psw) {
        return "";
    }

    public static void main(String[] args) {
        reflectParameter();
    }

}
