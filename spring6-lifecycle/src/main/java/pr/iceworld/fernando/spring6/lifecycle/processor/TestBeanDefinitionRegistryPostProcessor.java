package pr.iceworld.fernando.spring6.lifecycle.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import pr.iceworld.fernando.spring6.lifecycle.model.User;

@Slf4j
@Component
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("******************** TestBeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry ****************");
        log.info("******************** bean的数量：[{}] ****************", beanDefinitionRegistry.getBeanDefinitionCount());

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition();
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("username", "alvin");
        propertyValues.addPropertyValue("age", 12);

        // 注册bean信息
        beanDefinitionRegistry.registerBeanDefinition("beanDefineDemo", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("******************** TestBeanDefinitionRegistryPostProcessor#postProcessBeanFactory ****************");
        log.info("******************** bean的数量：[{}] ****************", configurableListableBeanFactory.getBeanDefinitionCount());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}