package com.test;

import com.ligeng.service.ITraService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dev on 16-8-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class TraTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ITraService bankAccountServiceProxy = (ITraService)context.getBean("traServiceProxy");
        System.out.println(bankAccountServiceProxy);
        bankAccountServiceProxy.test(1);
    }

    @Autowired
    private ITraService traService;

    @org.junit.Test
    public void test11(){
        traService.test(1);
    }
}
