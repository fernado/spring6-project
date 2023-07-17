package pr.iceworld.fernando.spring6.aop.simple;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.spring6.aop.simple.config.ComponentScanConfig;
import pr.iceworld.fernando.spring6.aop.simple.dto.User;
import pr.iceworld.fernando.spring6.aop.simple.service.UserService;

public class Spring6AopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User("Kevin");
        userService.add(user);
        userService.list().forEach(System.out::println);

    }
}
