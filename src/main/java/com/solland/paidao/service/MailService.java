package com.solland.paidao.service;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * 用户
 * @author Shibo-Sun
 *
 * 2016年1月5日 下午6:02:33
 */
public interface MailService {

	public boolean sendVerificationCodeForSignUp(String to , String verificationCode ) throws IOException, MessagingException;
}
