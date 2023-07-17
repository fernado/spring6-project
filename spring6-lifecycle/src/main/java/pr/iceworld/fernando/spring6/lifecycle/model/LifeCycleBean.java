package pr.iceworld.fernando.spring6.lifecycle.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
@ToString
public class LifeCycleBean implements InitializingBean {

    private String prop;

    public LifeCycleBean() {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean 实例化");
    }

    public LifeCycleBean(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return prop;
    }


    @PostConstruct
    private void postConstruct() {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean postConstruct");
    }

    @PreDestroy
    private void preDestroy() {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean preDestroy");
    }


    public void setProp(String prop) {
        this.prop = prop;
    }


    public void init() {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean 初始化");
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean init 给属性赋值");
        this.setProp("hello");
    }

    public void destroy() {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean destroy");
        this.setProp("hello");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("%%%%%%%%%%%%%%%%%%%% LifeCycleBean afterPropertiesSet");
    }
}