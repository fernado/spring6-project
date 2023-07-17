package pr.iceworld.fernando.spring6.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(
            topics = "topic1",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "local"
    )
    public void receiveMessage(String message, ConsumerRecord consumerRecord) {
        log.info("====> Topic={}, Partition={}, offset={}, Received message={}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), message);
    }

    @KafkaListener(
            topics = "topic2",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "local"
    )
    public void receiveMessageWithHeaders(@Payload String message,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                          @Header(KafkaHeaders.RECEIVED_KEY) String messageKey,
                                          @Header(KafkaHeaders.TIMESTAMP_TYPE) String  timestampType,
                                          @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp,
                                          @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("====> Headers topic={}, partition={}, offset={}, messageKey={}, "
                        + "timestampType={}, timestamp={}, Received message={}",
                 topic, partition, offset, messageKey, timestampType, timestamp, message);
    }

    @KafkaListener(
            containerFactory = "kafkaListenerContainerFactoryAutoCommitFalse",
            groupId = "localCommitFalse",
            topicPartitions = {
                    @TopicPartition(topic = "topic3",
                            partitions = {"0", "1", "2"}
                    )
            }
    )
    public void receiveMessageBySpecificPartition(String message, ConsumerRecord consumerRecord, Acknowledgment ack) {
        log.info("====> Specific topic={}, partition partition={}, offset={}, Received message={}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), message);
        ack.acknowledge();
    }

}
