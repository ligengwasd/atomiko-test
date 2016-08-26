package com.test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-8-25.
 */
public class Receive {

    public static void main(String[] args) {
        // ConnectionFactory：连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory;
        // Connection：JMS客户端到JMS Provider的连接
        Connection connection = null;
        // Session：一个发送或接收消息的线程
        Session session;
        // Destination：消息的目的地;消息发送给谁.
        Destination destination;
        //消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
//            "failover:(tcp://10.42.220.72:61617,tcp://10.42.220.72:61618)"
        try {
            connection = connectionFactory.createConnection();
            //启动
            connection.start();
            //获取操作连接
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //获取session
            destination = session.createQueue("queue");
            consumer =session.createConsumer(destination);

            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(1);
                TextMessage message =(TextMessage) consumer.receive(1);
//                if (null != message) {
                System.out.println("收到消息：" + message.getText());
//                } else {
//                    break;
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
