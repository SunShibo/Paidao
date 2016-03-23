package com.solland.paidao.test;

import com.solland.paidao.service.impl.MailServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by sunshibo on 2016/3/23.
 */
public class Test {
    public static void main(String[] args) throws IOException, MessagingException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/spring/application-context.xml") ;
        MailServiceImpl app = (MailServiceImpl) ac.getBean("mailService") ;
        app.sendVerificationCodeForSignUp("395831708@qq.com" , "4654") ;
    }
}
