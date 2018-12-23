package com.preety.messaging.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {
	
	public static void subscribe(String topic) {
		MqttClient client;
		try {
			client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
			client.setCallback(new SimpleMqttCallBack());
			client.connect();
			
			client.subscribe(topic);
			
		} catch (MqttException e) {
			System.out.println("error in connection: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
