package pr.iceworld.fernando.spring6.kafka.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.spring6.kafka.consumer.config.KafkaCommonConfig;

public class ConsumerMainApp {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(KafkaCommonConfig.class);
    }
}
