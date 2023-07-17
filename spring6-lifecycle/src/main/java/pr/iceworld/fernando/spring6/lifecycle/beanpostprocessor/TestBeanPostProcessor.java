package pr.iceworld.fernando.spring6.lifecycle.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("beanLifeCycle".equals(beanName)) {
            log.info("################BeanPostProcessor before init: bean:[{}], beanName: [{}]", bean, beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("beanLifeCycle".equals(beanName)) {
            log.info("################BeanPostProcessor after init: bean:[{}], beanName: [{}]", bean, beanName);
        }
        return bean;
    }
}
