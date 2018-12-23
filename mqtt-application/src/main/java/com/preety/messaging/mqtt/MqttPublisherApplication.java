package com.preety.messaging.mqtt;

public class MqttPublisherApplication {

	private static final String TOPIC = "iot_data";

	public static void main(String[] args) {
		 System.out.println("== START SUBSCRIBER ==");
		 Publisher.publish(TOPIC, "Hello user" );

	}

}
