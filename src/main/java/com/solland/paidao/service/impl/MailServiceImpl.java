package com.solland.paidao.service.impl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Resource
    private EmailDAO emailDAO;

	@Override
	public boolean sendVerificationCodeForSignUp(String to , String verificationCode ){
		try {
			MailUtil mailUtil = new MailUtil() ;
			Env env = new Env() ;
			String html = env.getProperty("mail.sendCodeForSignUp") ;
			String subject = env.getProperty("mail.register.sendVerificationCode") ;

			String format = html.replace("{0}", verificationCode);
			mailUtil.sendHTMLMail(subject ,format , to );
			this.insertEmail(format , to , "success" , subject , verificationCode) ;
			return true ;
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("[MailServiceImpl - sendVerificationCodeForSignUp] 邮件发送失败.error:" + e.getStackTrace());
		}
		return false ;
	}

	public boolean sendVeriCodeForResetPwd(String to , String verificationCode) {
		try {
			MailUtil mailUtil = new MailUtil() ;
			Env env = new Env() ;
			String html = env.getProperty("mail.sendCodeForResetPwdHTML") ;
			String subject = env.getProperty("mail.sendCodeForResetPwd.subject") ;

			String format = html.replace("{0}", verificationCode);
			mailUtil.sendHTMLMail(subject ,format , to );
			this.insertEmail(format , to , "success" , subject , verificationCode) ;
			return true ;
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("[MailServiceImpl - sendVerificationCodeForSignUp] 邮件发送失败.error:" + e.getStackTrace());
		}
		return false ;
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

	private static String fillStringByArgs(String str,String[] arr){
		Matcher m= Pattern.compile("\\{(\\d)\\}").matcher(str);
		while(m.find()){
			str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
		}
		return str;
	}
}
