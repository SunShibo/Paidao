package com.solland.paidao.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 从资源文件中读取数据 该文件为系统中所放置的汉字文字
 * 
 * @author  Shibo_Sun
 */
public class Message {

	private static final String BUNDLE_NAME = "message_text";

	private static final String BUNDLE_PATH_NAME = "message_path";

	/**
	 * 文本文件
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * 路径文件
	 */
	private static final ResourceBundle RESOURCE_PTTH_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_PATH_NAME);

	private Message() {
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

			return RESOURCE_BUNDLE.getString(key);

		} catch (MissingResourceException e) {
			return "no " + key + " key!";
		}
	}

	/**
	 * 获取路径文件值
	 * 
	 * @author sun
	 * @version 2014-12-4 上午10:55:08
	 * @param key
	 *            (key值不包含后缀名)
	 * @return
	 */
	public static String getPath(String key) {
		try {
			// 获取版本
			String version = Message.getPathString("version");

			key = key + version;

			return getPathString(key);

		} catch (MissingResourceException e) {
			return "no " + key + " key!";
		}
	}

	/**
	 * 获取路径文件的数据
	 * 
	 * @author sun
	 * @version 2014-12-4 上午10:30:25
	 * @param key
	 * @return
	 */
	private static String getPathString(String key) {

		try {

			return RESOURCE_PTTH_BUNDLE.getString(key);

		} catch (MissingResourceException e) {

			return "no " + key + " key!";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Message.getPath("doctor_head_image"));
	}
}
