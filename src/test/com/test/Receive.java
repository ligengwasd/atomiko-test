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
        // ConnectionFactory�����ӹ�����JMS������������
        ConnectionFactory connectionFactory;
        // Connection��JMS�ͻ��˵�JMS Provider������
        Connection connection = null;
        // Session��һ�����ͻ������Ϣ���߳�
        Session session;
        // Destination����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.
        Destination destination;
        //�����ߣ���Ϣ������
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
//            "failover:(tcp://10.42.220.72:61617,tcp://10.42.220.72:61618)"
        try {
            connection = connectionFactory.createConnection();
            //����
            connection.start();
            //��ȡ��������
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //��ȡsession
            destination = session.createQueue("queue");
            consumer =session.createConsumer(destination);

            while (true) {
                //���ý����߽�����Ϣ��ʱ�䣬Ϊ�˱��ڲ��ԣ�����˭��Ϊ100s
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(1);
                TextMessage message =(TextMessage) consumer.receive(1);
//                if (null != message) {
                System.out.println("�յ���Ϣ��" + message.getText());
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
