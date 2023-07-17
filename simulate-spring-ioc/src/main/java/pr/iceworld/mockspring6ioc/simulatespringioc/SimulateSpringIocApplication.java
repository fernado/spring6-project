package pr.iceworld.mockspring6ioc.simulatespringioc;

import pr.iceworld.mockspring6ioc.simulatespringioc.config.ProjectConfig;
import pr.iceworld.mockspring6ioc.simulatespringioc.context.CustomAnnotationConfigApplicationContext;
import pr.iceworld.mockspring6ioc.simulatespringioc.context.CustomBeanFactory;
import pr.iceworld.mockspring6ioc.simulatespringioc.service.HelloService;

public class SimulateSpringIocApplication {

    public static void main(String[] args) {
        CustomBeanFactory customBeanFactory = new CustomAnnotationConfigApplicationContext(ProjectConfig.class);
        HelloService helloService = customBeanFactory.getBean(HelloService.class);
        System.out.println("contain Bean: HelloAlias is true => " + customBeanFactory.containsBean("HelloAlias"));
        System.out.println("contain Bean: helloAlias is false => " + customBeanFactory.containsBean("helloAlias"));
        System.out.println("contain Bean: helloComponent is true => " + customBeanFactory.containsBean("helloComponent"));
        helloService.sayHello("Fernando");
    }
}
