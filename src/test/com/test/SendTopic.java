package com.test;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * Created by dev on 16-8-25.
 */
public class SendTopic {
    private static final int SEND_NUMBER = 5;
    public static void sendMessage(Session session, MessageProducer producer)
        throws Exception {
        for (int i = 1; i <=SEND_NUMBER; i++) {
            TextMessage message = session
                .createTextMessage("ActiveMq���͵���Ϣ" + i);
            //������Ϣ��Ŀ�ĵط�
            System.out.println("������Ϣ��" + "ActiveMq ���͵���Ϣ" + i);
            producer.send(message);
        }
    }

    public static void main(String[] args) {
        // ConnectionFactory�����ӹ�����JMS������������
        ConnectionFactory connectionFactory;
        // Connection��JMS�ͻ��˵�JMS Provider������
        Connection connection = null;
        // Session��һ�����ͻ������Ϣ���߳�
        Session session;
        // Destination����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.
        Destination destination;
        // MessageProducer����Ϣ������
        MessageProducer producer;
        // TextMessage message;
        //����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��jar
        connectionFactory = new ActiveMQConnectionFactory(
            ActiveMQConnection.DEFAULT_USER,
            ActiveMQConnection.DEFAULT_PASSWORD,
            "tcp://10.20.8.198:61616");
        try {
            //����ӹ����õ����Ӷ���
            connection = connectionFactory.createConnection();
            //����
            connection.start();
            //��ȡ��������
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //��ȡsessionע�����ֵFirstTopic��һ����������topic(��queue��Ϣ�ķ�����ȣ�������Ψһ�Ĳ�ͬ)
            destination = session.createTopic("FirstTopic");
            //�õ���Ϣ�����ߡ������ߡ�
            producer = session.createProducer(destination);
            //���ò��־û����˴�ѧϰ��ʵ�ʸ�����Ŀ����
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //������Ϣ���˴�д������Ŀ���ǲ��������߷�����ȡ
            sendMessage(session, producer);
            session.commit();
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