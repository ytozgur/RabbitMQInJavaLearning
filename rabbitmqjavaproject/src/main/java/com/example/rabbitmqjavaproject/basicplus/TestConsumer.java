package com.example.rabbitmqjavaproject.basicplus;

import com.example.rabbitmqjavaproject.CommonConfigs;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class TestConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection(CommonConfigs.AMQP_URL);
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(consumerTag);
            System.out.println(new String(message.getBody(), "UTF-8"));
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag);
        };
        //ACQ da alır mesajı çünkü key ve exchange aynı
        channel.basicConsume("LightQ", true, deliverCallback, cancelCallback);
    }
}