package com.test;

import com.ligeng.mapper.income.IncomeMapper;
import com.ligeng.service.IProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.org.mozilla.javascript.internal.debug.DebuggableScript;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class ProducerConsumerTest {

	  @Autowired
	  private IProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination queueDestination;
    @Autowired
    private IncomeMapper incomeMapper;

    @Test
    public void sendTraMsg(){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        incomeMapper.addIncome();
        producerService.sendMessage(queueDestination, "ligeng");
//        if (1==1){
//            throw new RuntimeException("sendTraMsg error");
//        }
    }

}
