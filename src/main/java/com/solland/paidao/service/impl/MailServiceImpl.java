package com.solland.paidao.service.impl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.solland.paidao.dao.EmailDAO;
import com.solland.paidao.entity.EmailDO;
import com.solland.paidao.service.MailService;
import com.solland.paidao.util.MailUtil;
import com.solland.paidao.util.env.Env;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月6日 上午10:29:13
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
	@Resource
    private EmailDAO emailDAO;

	@Override
	public boolean sendVerificationCodeForSignUp(String to , String verificationCode ) throws IOException, MessagingException {

		MailUtil mailUtil = new MailUtil() ;
		Env env = new Env() ;

		String html = env.getProperty("mail.sendCode") ;
		String subject = env.getProperty("mail.register.sendVerificationCode") ;

		String format = html.replace("{0}", verificationCode);
		mailUtil.sendHTMLMail(subject ,format , to );

		this.insertEmail(format , to , "success" , subject , "") ;
		return true ;
	}

	private int insertEmail (String content , String to , String result , String subject , String description) {
		EmailDO emailDO = new EmailDO() ;
		emailDO.setContent(content);
		emailDO.setToAddress(to);
		emailDO.setResult(result);
		emailDO.setSubject(subject);
		emailDO.setDescription(description);
		return emailDAO.insert(emailDO) ;
	}


	public static void main(String[] args) throws IOException, MessagingException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/spring/application-context.xml") ;
		MailServiceImpl app = (MailServiceImpl) ac.getBean("mailService") ;
		app.sendVerificationCodeForSignUp("395831708@qq.com" , "4654") ;


	}

	private static String fillStringByArgs(String str,String[] arr){
		Matcher m= Pattern.compile("\\{(\\d)\\}").matcher(str);
		while(m.find()){
			str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
		}
		return str;
	}
}
