package com.preety.messaging.mqtt;

public class MqttSubscriberApplication {

	private static final String TOPIC = "iot_data";

	public static void main(String[] args) {
		 System.out.println("== START SUBSCRIBER ==");
		 Subscriber.subscribe(TOPIC);

	}

}
