package com.preety.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoAssignSeek {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(ConsumerDemoAssignSeek.class);
		
		String bootstrapServer= "127.0.0.1:9092";
		String topic= "second_topic";
		
		Properties props= new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		
		//create consumer
		KafkaConsumer<String, String> consumer= new KafkaConsumer<String, String>(props);
		
		//assign and seek are mostly used to replay data or fetch a message
		TopicPartition partitionToReadFrom= new TopicPartition(topic, 0);
		
		consumer.assign(Arrays.asList(partitionToReadFrom));
		long offsetToReadFrom= 5L;
		//seek
		consumer.seek(partitionToReadFrom, offsetToReadFrom);
		
		int numOfMessagesToRead= 5;
		boolean keepreading=true;
		int readSoFar=0;
		
		//poll for new data
		while(keepreading) {
			ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record: records) {
				readSoFar++;
				logger.info(" key: "+ record.key() +" value: "+  record.value() +"\n partition: "+  record.partition() + " offset: "+ record.offset());
				if(readSoFar==numOfMessagesToRead) {
					keepreading=false;
					break;
				}
			}
		}
	}

}
