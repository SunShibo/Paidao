package com.solland.paidao.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 从资源文件中读取数据
 * 该文件为系统中所放置的汉字文字
 * @author houzhiqing
 */
public class EmailMessage {

	private static final String BUNDLE_NAME = "email";
	private static final String VERSION = "version" ;

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private EmailMessage() {
	}

	/**
	 * get the value from the properties file
	 * 
	 * @param key
	 *            the key in the properties file
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key) ;
			
		} catch (MissingResourceException e) {
			return "no " + key + " key!";
		}
	}
	
	/**
	 * 不需要加后缀名,配置文件中后缀名会自动补全
	 * @author sun
	 * @version 2015-1-14 下午05:19:59
	 * @param key
	 * @return
	 */
	public static String getPath (String key){
		
		String version =  EmailMessage.getString(VERSION) ; //获取版本的猴后缀名
		
		try {
			return RESOURCE_BUNDLE.getString(key + version) ;
			
		} catch (MissingResourceException e) {
			return "no " + key + " key!";
		}
	}
}
