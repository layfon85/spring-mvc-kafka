package com.loca.common.kafka;

import com.loca.common.kafka.LocaKafkaActionRouter;
import com.loca.common.spring.ApplicationContextManager;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class LocaKafkaMessageListener implements MessageListener<String, String> {


	private ApplicationContextManager applicationContextManager;

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		StringBuilder sb = new StringBuilder();
		sb.append(data.topic());
		sb.append(".");
		sb.append("ROUTER");

		LocaKafkaActionRouter locaKafkaActionRouter = ApplicationContextManager.getInstance().getBean(sb.toString(), LocaKafkaActionRouter.class);
		locaKafkaActionRouter.execute(data.topic(), data);
	}
}
