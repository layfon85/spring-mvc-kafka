package com.loca.common.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface LocaKafkaActionRouter {

	void execute(String topic, ConsumerRecord<String, String> data);
}
