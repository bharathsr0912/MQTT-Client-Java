package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {
    public static void main(String[] args) {
        String broker = "tcp://test.mosquitto.org:1883";
        String topic = "bharath/test";
        //String username = "emqx";
        //String password = "public";
        String clientid = "bharath_publish_client";
        String content = "Hello MQTT";
        int qos = 0;
        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            //options.setUserName(username);
            //options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            // create message and setup QoS
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            // publish message
            client.publish(topic, message);
            System.out.println("Message published by Bharath");
            System.out.println("topic: " + topic);
            System.out.println("message content: " + content);
            // disconnect
            //client.disconnect();
            // close client
            //client.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
//        String topic        = "MQTT Examples";
//        String content      = "Message from MqttPublishSample";
//        int qos             = 2;
//        String broker       = "tcp://test.mosquitto.org:1883";
//        String clientId     = "JavaClient";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//            System.out.println("Sample : " + sampleClient);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(false);
//            System.out.println("Connecting to broker: "+broker);
//            sampleClient.connect(connOpts);
//            connOpts.setConnectionTimeout(60);
//            System.out.println("Connected");
//            System.out.println("Publishing message: "+content);
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.publish(topic, message);
//            System.out.println("Message published");
//            //sampleClient.disconnect();
//            //System.out.println("Disconnected");
//            //System.exit(0);
//        } catch(MqttException me) {
//            System.out.println("reason "+me.getReasonCode());
//            System.out.println("msg "+me.getMessage());
//            System.out.println("loc "+me.getLocalizedMessage());
//            System.out.println("cause "+me.getCause());
//            System.out.println("excep "+me);
//            me.printStackTrace();
//        }
//    }
//}