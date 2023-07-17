package pr.iceworld.mockspring6ioc.simulatespringioc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CustomService {
    String value() default  "";
}
