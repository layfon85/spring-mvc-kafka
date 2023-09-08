package com.loca.common.kafka.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:/config/kafka.properties")
public class LocaKafkaProperties {

	@Value("${loca.kafka.bootStrapServers}")
	private List<String> bootstrapServers;
	@Value("${loca.kafka.groupId}")
	private String groupId;
	@Value("${loca.kafka.max.poll.interval.ms}")
	private Integer maxPollIntervalMs;
	@Value("${loca.kafka.consumer.topics}")
	private String[] topics;
	@Value("${loca.kafka.consumer.concurrency}")
	private Integer concurrency;

	public List<String> getBootstrapServers() {
		return bootstrapServers;
	}

	public String getGroupId() {
		return groupId;
	}

	public Integer getMaxPollIntervalMs() {
		return maxPollIntervalMs;
	}

	public String[] getTopics() {
		return topics;
	}

	public Integer getConcurrency() {
		return concurrency;
	}
}
