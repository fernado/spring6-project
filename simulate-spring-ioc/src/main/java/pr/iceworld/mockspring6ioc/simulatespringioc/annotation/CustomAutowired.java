package pr.iceworld.mockspring6ioc.simulatespringioc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface CustomAutowired {
    boolean required() default true;
}
