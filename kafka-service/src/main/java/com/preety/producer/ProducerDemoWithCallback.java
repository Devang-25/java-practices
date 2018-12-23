package com.preety.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithCallback {

	public static void main(String[] args) {
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
			// create producer record
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("second_topic", "Hello"+i);

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

			});
		}
		kafkaProducer.flush();
		kafkaProducer.close();
	}

}
