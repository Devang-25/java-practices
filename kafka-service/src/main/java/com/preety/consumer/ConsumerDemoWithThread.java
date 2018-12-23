package com.preety.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoWithThread {
	ConsumerDemoWithThread(){}

	public static void main(String[] args) {
		
		new ConsumerDemoWithThread().run();
		
		

	}
	private void run() {
		Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class);
		String bootstrapServer= "127.0.0.1:9092";
		String groupId= "my-seventh-application";
		String topic= "second_topic";
		
		//latch for dealing with multiple threads
		CountDownLatch latch= new CountDownLatch(1);
		
		Runnable consumerRunnable = new ConsumerRunnable(
				latch, bootstrapServer, groupId, topic);
		Thread consumerThread= new Thread(consumerRunnable);
		consumerThread.start();
		
		// add a shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			logger.info("caught shutdown hook");
			((ConsumerRunnable)consumerRunnable).shutdown();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				logger.info("logger has exited");
			}
		}));
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("app  got interrupted " + e);
		}finally {
			logger.info("App is closing. ");
		}
	}

	public class ConsumerRunnable implements Runnable {
		private CountDownLatch latch;
		private KafkaConsumer<String, String> consumer;
		private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

		ConsumerRunnable(CountDownLatch latch, String bootstrapServer, String groupId, String topic) {
			this.latch = latch;

			Properties props = new Properties();
			props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
			props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
			props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

			// create consumer
			this.consumer = new KafkaConsumer<String, String>(props);
			//subscribe consumer to topics
			consumer.subscribe(Arrays.asList(topic));
		}

		public void run() {
			// poll for new data
			try {
			while (true) {
				ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					logger.info(" key: " + record.key() + " value: " + record.value() + "\n partition: "
							+ record.partition() + " offset: " + record.offset());
					
					
				}
				throw new WakeupException();
			}
			}catch(WakeupException e) {
				logger.info("received shutdown signal: " + e);
			}finally {
				consumer.close();
				latch.countDown();
			}

		}
		public void shutdown() {
			consumer.wakeup();
		}

	}

}
