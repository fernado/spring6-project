package pr.iceworld.fernando.spring6.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.spring6.aop.annotation.CheckAnnotationTest;
import pr.iceworld.fernando.spring6.aop.annotation.Test;
import pr.iceworld.fernando.spring6.aop.aspect.AspectService;
import pr.iceworld.fernando.spring6.aop.aspect.Monkey;
import pr.iceworld.fernando.spring6.aop.config.ComponentScanConfig;

public class Spring6AopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        AspectService aspectService = context.getBean(AspectService.class);
        aspectService.doAction();
        System.out.println("-----------------");
        System.out.println("Hello World!");

        Monkey monkey = context.getBean("monkey", Monkey.class);
        monkey.stealPeaches("孙大圣的大徒弟");


        Test test = CheckAnnotationTest.class.getDeclaredAnnotation(Test.class);
        System.out.println("Test -- " + test.value());


        CheckAnnotationTest checkAnnotationTest = context.getBean("checkAnnotationTest", CheckAnnotationTest.class);
        System.out.println("555555555555555555555");
        checkAnnotationTest.doAction("5", "8");

    }
}
