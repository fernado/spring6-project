package pr.iceworld.fernando.spring6.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    Random random = new Random(10);
    public void sendMessageNormal(String message, String key, String topic) {
        int ranPartition = random.nextInt(3);
        kafkaTemplate.send(createRecord(message, ranPartition, key, topic));
        log.info("Normal sent topic={}, message={}, partition={}", topic, message, ranPartition);
    }

    public void sendMessageWithHeaders(String message, String key, String topic) {
        int ranPartition = random.nextInt(3);
        Message<String> providedMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, key)
                .setHeader(KafkaHeaders.PARTITION, ranPartition)
                .build();
        kafkaTemplate.send(providedMessage);
        log.info("Header normal sent topic={}, message={}, partition={}", topic, message, ranPartition);
    }

    public void sendMessageWithCallback(String message, String key, String topic) {
        final int ranPartition = random.nextInt(3);
        final ProducerRecord<String, String> record = createRecord(message, ranPartition, key, topic);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(result, message);
            } else {
                handleFailure(record, ex);
            }
        });
    }

    private void handleFailure(ProducerRecord<String, String> record, Throwable ex) {
        log.info("Fail to send topic={}, message={} due to : {}", record.topic(), record.value(), ex.getMessage());
    }

    private void handleSuccess(SendResult<String, String> sendResult, String message) {
        log.info("Sent topic={}, message={}, offset={}, partition={}",
                sendResult.getRecordMetadata().topic(),
                message,
                sendResult.getRecordMetadata().offset(),
                sendResult.getRecordMetadata().partition());
     }

    private ProducerRecord<String, String> createRecord(String message, int ranPartition, String key, String topic) {
        return new ProducerRecord(topic, ranPartition, key, message);
    }

}
