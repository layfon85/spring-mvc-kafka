package com.loca.common.kafka.config;

import com.loca.common.kafka.LocaKafkaMessageListener;
import com.loca.common.kafka.config.properties.LocaKafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LocaKafkaConfig {


	@Autowired
	private LocaKafkaProperties locaKafkaProperties;

	@Bean
	public ConcurrentMessageListenerContainer<String, String> concurrentMessageListenerContainer(ConsumerFactory consumerFactory, LocaKafkaMessageListener kafkaMessageListener) {
		ConcurrentMessageListenerContainer<String, String> messageListenerContainer = null;
		ContainerProperties containerProperties = new ContainerProperties(locaKafkaProperties.getTopics());
		containerProperties.setMessageListener(kafkaMessageListener);
		messageListenerContainer = new ConcurrentMessageListenerContainer<>(consumerFactory,
				containerProperties);
		messageListenerContainer.setAutoStartup(true);
		messageListenerContainer.setConcurrency(locaKafkaProperties.getConcurrency());
		return messageListenerContainer;
	}


	@Bean
	public DefaultKafkaConsumerFactory kafkaConsumerFactory() {
		return new DefaultKafkaConsumerFactory(consumerProperties());
	}


	@Bean
	public LocaKafkaMessageListener locaKafkaMessageListener() {
		return new LocaKafkaMessageListener();
	}

	public Map<String, Object> consumerProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, locaKafkaProperties.getBootstrapServers());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, locaKafkaProperties.getGroupId());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, locaKafkaProperties.getMaxPollIntervalMs());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}
}
