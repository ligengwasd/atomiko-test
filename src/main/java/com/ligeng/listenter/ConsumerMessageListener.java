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
        System.out.println("接收到一个纯文本消息");
        try {
            System.out.println("消息内容是：" + textMsg.getText());
//            outcomeMapper.addOutcome();
//            if (1 == 1) {
//                throw new RuntimeException("Error");
//            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

//    public void onMessage(Message message) {
//        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换，或者直接把onMessage方法的参数改成Message的子类TextMessage
//        TextMessage textMsg = (TextMessage) message;
//        System.out.println("接收到一个纯文本消息。");
//        try {
//            System.out.println("消息内容是：" + textMsg.getText());
//            if (1 == 1) {
//                throw new RuntimeException("Error");
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }

}

