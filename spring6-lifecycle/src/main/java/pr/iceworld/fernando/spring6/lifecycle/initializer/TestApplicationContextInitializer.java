package pr.iceworld.fernando.spring6.lifecycle.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class TestApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("1. ApplicationContextInitializer *********************** this is TestApplicationContextInitializer *************************");
    }
}
