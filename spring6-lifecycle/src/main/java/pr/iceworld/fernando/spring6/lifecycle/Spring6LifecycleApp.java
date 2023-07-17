package pr.iceworld.fernando.spring6.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.spring6.lifecycle.bean.PrototypeBean;
import pr.iceworld.fernando.spring6.lifecycle.config.ComponentScanConfig;
import pr.iceworld.fernando.spring6.lifecycle.model.User;

@Slf4j
public class Spring6LifecycleApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        //context.add.addInitializers(new TestApplicationContextInitializer());
        User user = context.getBean(User.class);
        log.info("************************username: [{}]", user.getUsername());

        PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
        log.info("************************prototypeBean: [{}]", prototypeBean);

    }
}
