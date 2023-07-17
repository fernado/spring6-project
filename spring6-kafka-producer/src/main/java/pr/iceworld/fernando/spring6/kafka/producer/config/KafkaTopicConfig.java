package pr.iceworld.fernando.spring6.kafka.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic1")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic2")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name("topic3")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    //
    //@Bean
    //public NewTopic topic3() {
    //    return TopicBuilder.name("thing3")
    //            .assignReplicas(0, Arrays.asList(0, 1))
    //            .assignReplicas(1, Arrays.asList(1, 2))
    //            .assignReplicas(2, Arrays.asList(2, 0))
    //            .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
    //            .build();
    //}
    //
    //@Bean
    //public NewTopic topic4() {
    //    return TopicBuilder.name("defaultBoth")
    //            .build();
    //}
    //
    //@Bean
    //public NewTopic topic5() {
    //    return TopicBuilder.name("defaultPart")
    //            .replicas(1)
    //            .build();
    //}
    //
    //@Bean
    //public NewTopic topic6() {
    //    return TopicBuilder.name("defaultRepl")
    //            .partitions(3)
    //            .build();
    //}
    //
    //@Bean
    //public KafkaAdmin.NewTopics topics456() {
    //    return new KafkaAdmin.NewTopics(
    //            TopicBuilder.name("defaultBoth")
    //                    .build(),
    //            TopicBuilder.name("defaultPart")
    //                    .replicas(1)
    //                    .build(),
    //            TopicBuilder.name("defaultRepl")
    //                    .partitions(3)
    //                    .build());
    //}

}