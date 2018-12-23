package com.preety.messaging.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback {

	public void connectionLost(Throwable throwable) {

		System.out.println("Connection lost to MQTT broker");

	}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

		System.out.println("Message received:\t" + new String(mqttMessage.getPayload()));
	}

	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("is delivery completed "+ iMqttDeliveryToken.isComplete());
	}

}
