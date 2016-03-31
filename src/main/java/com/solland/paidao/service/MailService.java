package com.solland.paidao.service;

/**
 * 用户
 * @author Shibo-Sun
 *
 * 2016年1月5日 下午6:02:33
 */
public interface MailService {

	public boolean sendVerificationCodeForSignUp(String to , String verificationCode );

	public boolean sendVeriCodeForResetPwd(String to , String verificationCode) ;
}
