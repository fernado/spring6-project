package pr.iceworld.fernando.spring6.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * custom method annotation
 */
@Target({
        ElementType.METHOD
})
public @interface TestMethod {
    String value() default "Test Method";
}
