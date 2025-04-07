package org.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscribeSample {
    public static void main(String[] args) {
        String broker = "tcp://test.mosquitto.org:1883";
        String topic = "bharath/test";
        //String username = "emqx";
        //String password = "public";
        String clientid = "bharath_subscribe_client";
        int qos = 0;
        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            // connect options
            MqttConnectOptions options = new MqttConnectOptions();
            //options.setUserName(username);
            //options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // setup callback
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + message.getQos());
                    System.out.println("message content: " + new String(message.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }
            });
            client.connect(options);
            client.subscribe(topic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//        String topic = "MQTT Examples";
//        String content = "Message from MqttPublishSample";
//        int qos = 2;
//        String broker = "tcp://test.mosquitto.org:1883";
//        String clientId = "JavaClient";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//            System.out.println("Sample : " + sampleClient);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//            System.out.println("Connecting to broker: " + broker);
//            sampleClient.connect(connOpts);
//            System.out.println("Connected");
//            sampleClient.subscribe(topic, qos);
//            connOpts.setConnectionTimeout(60);
//            // setup callback
//            sampleClient.setCallback(new MqttCallback() {
//                public void connectionLost(Throwable cause) {
//                    System.out.println("connectionLost: " + cause);
//                }
//                public void messageArrived(String topic, MqttMessage message) {
//                    System.out.println("topic: " + topic);
//                    System.out.println("Qos: " + message.getQos());
//                    System.out.println("message content: " + new String(message.getPayload()));
//                }
//                public void deliveryComplete(IMqttDeliveryToken token) {
//                    System.out.println("deliveryComplete---------" + token.isComplete());
//                }
//            });
//            //System.exit(0);
//        } catch (MqttException me) {
//            System.out.println("reason " + me.getReasonCode());
//            System.out.println("msg " + me.getMessage());
//            System.out.println("loc " + me.getLocalizedMessage());
//            System.out.println("cause " + me.getCause());
//            System.out.println("excep " + me);
//            me.printStackTrace();
//        }
//    }
//}
