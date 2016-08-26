package com.ligeng.listenter;

import com.ligeng.mapper.outcome.OutcomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Created by dev on 16-8-25.
 */
@Component("consumerMessageListener")
public class ConsumerMessageListener implements MessageListener {
    @Autowired
    private OutcomeMapper outcomeMapper;
    @Override
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("���յ�һ�����ı���Ϣ");
        try {
            System.out.println("��Ϣ�����ǣ�" + textMsg.getText());
//            outcomeMapper.addOutcome();
//            if (1 == 1) {
//                throw new RuntimeException("Error");
//            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

//    public void onMessage(Message message) {
//        //��������֪�������߷��͵ľ���һ�����ı���Ϣ�������������ֱ�ӽ���ǿ��ת��������ֱ�Ӱ�onMessage�����Ĳ����ĳ�Message������TextMessage
//        TextMessage textMsg = (TextMessage) message;
//        System.out.println("���յ�һ�����ı���Ϣ��");
//        try {
//            System.out.println("��Ϣ�����ǣ�" + textMsg.getText());
//            if (1 == 1) {
//                throw new RuntimeException("Error");
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }

}

