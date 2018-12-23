package com.preety.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoKey {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
		String bootstrapServers = "127.0.0.1:9092";

		// System.out.println("hello world");
		Properties prop = new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		prop.setProperty("key.serializer", StringSerializer.class.getName());
		prop.setProperty("value.serializer", StringSerializer.class.getName());

		// create producer

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(prop);

		for (int i = 0; i < 10; i++) {
			String topic= "second_topic";
			String key= "_id"+ i;
			String value = "Hello" + i;
			// create producer record
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
			logger.info("key: " + key);
			// same key always go to same partition
			//key: _id0
			// topic: second_topic, partition: 1, offset: 3, timestamp: 1545210815206
			//key: _id1
			// topic: second_topic, partition: 0, offset: 3, timestamp: 1545210815268
			//key: _id2
			//topic: second_topic, partition: 1, offset: 4, timestamp: 1545210815273
			
			// send data asynchronous
			kafkaProducer.send(record, new Callback() {

				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e == null) {
						logger.info("Recieved new metadata: \n" + "topic: " + metadata.topic() + ", partition: "
								+ metadata.partition() + ", offset: " + metadata.offset() + ", timestamp: "
								+ metadata.timestamp());
					} else {
						logger.error("error while producing: " + e);
					}

				}

			}).get(); // block send to make it synchronous
		}
		kafkaProducer.flush();
		kafkaProducer.close();
	}

}
