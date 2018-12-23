package com.preety.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);
		
		String bootstrapServer= "127.0.0.1:9092";
		String groupId= "my-fourth-application";
		String topic= "second_topic";
		
		Properties props= new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		
		//create consumer
		KafkaConsumer<String, String> consumer= new KafkaConsumer<String, String>(props);
		
		
		
		// subscribe consumer to our topics
		
		//consumer.subscribe(Collections.singleton(topic));
		consumer.subscribe(Arrays.asList("second_topic"));
		
		//poll for new data
		while(true) {
			ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record: records) {
				logger.info(" key: "+ record.key() +" value: "+  record.value() +"\n partition: "+  record.partition() + " offset: "+ record.offset());
			}
		}
	}

}
