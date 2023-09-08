package com.loca.biz.router;

import com.loca.common.kafka.LocaKafkaActionRouter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service("DEV.TEST.1.ROUTER")
public class SampleRouter implements LocaKafkaActionRouter {


	@Override
	public void execute(String topic, ConsumerRecord<String, String> data) {
		System.out.println("topic :" + topic);
		System.out.println("data :" + data.value());
	}
}
