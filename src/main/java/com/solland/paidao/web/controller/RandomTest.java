package com.solland.paidao.web.controller;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class RandomTest {

	public static void main(String[] args) {
		// 生成一个 4 位数的随机数字串
		String num = RandomStringUtils.random(4, false, true);
		// 生成一个 4 位的随机字符串（含字母，字母不区分大小写）
		String str = RandomStringUtils.random(4, true, false);
		// 生成一个 4 位的随机字符串（含数字、字母，字母不区分大小写）
		String str2 = RandomStringUtils.random(4, true, true);
		// 生成一个 4 位的 ASCII 码字符串
		String ascii = RandomStringUtils.randomAscii(4);
		// 生成一个 4 位的随机字母串
		String alphabetic = RandomStringUtils.randomAlphabetic(4);
		
		System.out.println(num);
		System.out.println(str);
		System.out.println(str2);
		System.out.println(ascii);
		System.out.println(alphabetic);
		
		int max=20;
        int min=10;
        Random random = new Random();

        /**
         * random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
		 * 以生成[10,20]随机数为例，首先生成0-20的随机数，然后对(20-10+1)取模得到[0-10]之间的随机数，然后加上min=10，最后生成的是10-20的随机数
         */
        int str10_20 = random.nextInt(max)%(max-min+1) + min;
        System.out.println(str10_20);
	}

}
