package pr.iceworld.fernando.spring6.lifecycle.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyBeanInstantiationPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("lifeCycleBean".equals(beanName)) {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^MyBeanInstantiationPostProcessor postProcessBeforeInstantiation， [{}]", beanName);
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("lifeCycleBean".equals(beanName)) {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^MyBeanInstantiationPostProcessor postProcessAfterInstantiation， [{}]", bean);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("lifeCycleBean".equals(beanName)) {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^MyBeanInstantiationPostProcessor postProcessProperties， pvs: [{}], bean: [{}]", pvs, bean);
        }
        return pvs;
    }
}