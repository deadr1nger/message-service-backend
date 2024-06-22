package ru.sinara.messageservicebackend.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.sinara.messageservicebackend.model.dto.RegistrationRequestDto;


import java.util.HashMap;
import java.util.Map;


import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
@Configuration
@EnableKafka
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaConfig {

    @Value("${spring.kafka.consumer.max-partition}")
    int maxPartitions;
    @Value("${spring.kafka.consumer.max-bytes}")
    int maxBytes;
    @Value("${spring.kafka.topic.in}")
    String topicIn;
    @Value("${spring.kafka.topic.out}")
    String topicOut;
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(baseConfigs());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        return props;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(baseConfigs());
        props.put(MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitions);
        props.put(FETCH_MAX_BYTES_CONFIG, maxBytes);
        props.put(ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        return props;
    }

    private Map<String, Object> baseConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return props;
    }

    private ProducerFactory<String, RegistrationRequestDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }


    public ConsumerFactory<String, RegistrationRequestDto> consumerFactory() {
        Map<String, Object> config = new HashMap<>(consumerConfigs());
        return new DefaultKafkaConsumerFactory<>(
                config, new StringDeserializer(),
                new JsonDeserializer<>(RegistrationRequestDto.class));
    }

    @Bean
    public KafkaTemplate<String, RegistrationRequestDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>(baseConfigs());
        return new KafkaAdmin(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RegistrationRequestDto> activationListener() {
        ConcurrentKafkaListenerContainerFactory<
                String, RegistrationRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}


