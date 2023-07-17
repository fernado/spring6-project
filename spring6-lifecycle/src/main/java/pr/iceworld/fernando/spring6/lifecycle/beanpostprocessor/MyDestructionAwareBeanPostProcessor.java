package pr.iceworld.fernando.spring6.lifecycle.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import pr.iceworld.fernando.spring6.lifecycle.model.LifeCycleBean;

@Component
@Slf4j
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if ("lifeCycleBean".equals(beanName)) {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^MyDestructionAwareBeanPostProcessor postProcessBeforeDestruction， [{}]", bean);
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        if (bean instanceof LifeCycleBean) {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^MyDestructionAwareBeanPostProcessor requiresDestruction， [{}]", bean);
        }
        return true;
    }
}
