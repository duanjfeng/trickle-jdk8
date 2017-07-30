package trickle.jdk8.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * <p>
 * Title: RepeatableAnnotations
 * <p>
 * Description: Repeatable annotation demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class RepeatableAnnotations {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 可重复注解
     * <p>
     * 通过@Repeatable指定其容器类
     */
    @Repeatable(ServiceFilters.class)
    @interface ServiceFilter {
        String name();
    }

    /**
     * key.2. 可重复注解的容器类
     * <p>
     * 必须有一个类型为被包装的注解类型数组的value属性
     */
    @Retention(RetentionPolicy.RUNTIME) // 非必须，但只有设置为runtime才能被反射动态获取
    @interface ServiceFilters {
        ServiceFilter[] value();
    }

    // 使用可重复注解的方法
    @ServiceFilter(name = "codecFilter")
    @ServiceFilter(name = "stateFilter")
    public void service() {
        GLOBAL.info("service");
    }

    /**
     * key.3. 在类型上使用的注解
     */
    @Target(value = { ElementType.TYPE_PARAMETER, ElementType.TYPE_USE })
    @interface MoreAnnotated {
    }

    // 验证方法
    // 注意区分getAnnotation和getAnnotationsByType
    private static void repeatableAnnotation() {
        try {
            Method method = RepeatableAnnotations.class.getMethod("service");

            ServiceFilters annotationFilters = method.getAnnotation(ServiceFilters.class);
            ServiceFilter annotationFilter = method.getAnnotation(ServiceFilter.class);
            ServiceFilter[] annotationsByType = method.getAnnotationsByType(ServiceFilter.class);

            GLOBAL.info(() -> "annotationFilters value length: " + annotationFilters.value().length);
            GLOBAL.info(() -> "annotationFilter: " + annotationFilter);
            GLOBAL.info(() -> "annotationsByType length: " + annotationsByType.length);

        }
        /**
         * key.4. jdk8中提供了反射异常的公共父类ReflectiveOperationException
         */
        catch (ReflectiveOperationException e) {
            GLOBAL.severe(e.getMessage());
        }
    }

    public static void main(String[] args) {
        repeatableAnnotation();
    }

}
