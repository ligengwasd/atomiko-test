package com.ligeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.jms.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;

@Service
public class IProducerServiceImpl implements IProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;
//	@Autowired
//	@Qualifier("responseQueue")
//	private Destination responseDestination;
	
	public void sendMessage(Destination destination, final String message) {
//		System.out.println("---------------生产者发送消息-----------------");
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				/*TextMessage textMessage = session.createTextMessage(message);
				textMessage.setJMSReplyTo(responseDestination);
				return textMessage;*/
          System.out.println("---------------生产者发送了一条消息： " + message);
				return session.createTextMessage(message);
			}
		});
      jmsTemplate.execute(new ProducerCallback<Object>() {
          @Override
          public Object doInJms(Session session, MessageProducer messageProducer) throws JMSException {
              System.out.println("ProducerCallback");
              return null;
          }
      });
    jmsTemplate.execute(new SessionCallback<Object>() {
        @Override
        public Object doInJms(Session session) throws JMSException {
            System.out.println("SessionCallback execute");
            return null;
        }
    });


	}

    @Override
//    public void sendMessage(Destination destination, Serializable obj) {
////        jmsTemplate.receive();
//    }
//
	public void sendMessage(final Destination destination, final Serializable obj) {
        //未使用MessageConverter的情况
		/*jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objMessage = session.createObjectMessage(obj);
				return objMessage;
			}

		});*/
        //使用MessageConverter的情况
        jmsTemplate.convertAndSend(destination, obj);
        jmsTemplate.execute(new SessionCallback<Object>() {

            public Object doInJms(Session session) throws JMSException {
                MessageProducer producer = session.createProducer(destination);
                Message message = session.createObjectMessage(obj);
                producer.send(message);
                return null;
            }

        });
        jmsTemplate.execute(new ProducerCallback<Object>() {

            public Object doInJms(Session session, MessageProducer producer)
                throws JMSException {
                Message message = session.createObjectMessage(obj);
                producer.send(destination, message);
                return null;
            }

        });
    }
	
}
