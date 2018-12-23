package com.preety.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {


	public static void main(String[] args) {
		String bootstrapServers= "127.0.0.1:9092";
		//System.out.println("hello world");
		Properties prop= new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		prop.setProperty("key.serializer", StringSerializer.class.getName());
		prop.setProperty("value.serializer", StringSerializer.class.getName());
		
		// create producer
		
		KafkaProducer<String, String> kafkaProducer= new KafkaProducer<String, String>(prop);
		
		// create producer record
		ProducerRecord<String, String> record= new ProducerRecord<String, String> ("first_topic", "Hello");
		
		// send data asynchronous
		kafkaProducer.send(record);
		kafkaProducer.flush();
		kafkaProducer.close();
	}

}
