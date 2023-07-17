package pr.iceworld.fernando.spring6.aop.simple.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("pr.iceworld.fernando.spring6.aop.simple")
@EnableAspectJAutoProxy
@Configuration
public class ComponentScanConfig {
}
