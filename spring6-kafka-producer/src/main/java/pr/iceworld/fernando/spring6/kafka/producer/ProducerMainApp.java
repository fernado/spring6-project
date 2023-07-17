package pr.iceworld.fernando.spring6.kafka.producer;

import org.apache.kafka.common.Uuid;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.spring6.kafka.producer.config.KafkaCommonConfig;
import pr.iceworld.fernando.spring6.kafka.producer.service.KafkaProducerService;

public class ProducerMainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(KafkaCommonConfig.class);

        KafkaProducerService kafkaProducerService = applicationContext.getBean(KafkaProducerService.class);
        for (int i = 0; i < 5; i++) {
            kafkaProducerService.sendMessageNormal(Uuid.randomUuid().toString(), "key1", "topic1");
            kafkaProducerService.sendMessageWithHeaders(Uuid.randomUuid().toString(), "key2", "topic2");
            kafkaProducerService.sendMessageWithCallback(Uuid.randomUuid().toString(), "key3", "topic3");
        }
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
