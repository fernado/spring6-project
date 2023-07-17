package pr.iceworld.fernando.spring6.lifecycle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.spring6.lifecycle.bean.BeanLifeCycle;

@Configuration
public class LifeCycleConfig {

    @Bean(name = "beanLifeCycle", initMethod = "init", destroyMethod = "destroy")
    public BeanLifeCycle createBeanLifeCycle() {
        BeanLifeCycle beanLifeCycle = new BeanLifeCycle();
        return beanLifeCycle;
    }
}