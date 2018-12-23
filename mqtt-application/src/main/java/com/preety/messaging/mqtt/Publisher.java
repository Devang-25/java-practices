package com.preety.messaging.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

	public static void publish(String topic, String message) {
		try {
			MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
			client.connect();

			MqttMessage mqttMessage = new MqttMessage();

			mqttMessage.setPayload(message.getBytes());

			client.publish(topic, mqttMessage);

			client.disconnect();

		} catch (MqttException e) {
			System.out.println("error: " + e);
		}
	}
}
