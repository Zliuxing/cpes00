package com.atguigu.cpes.util;

import java.util.Random;

public class NumberUtil {

	public static String getNumber(int len) {
		StringBuilder builder = new StringBuilder();
		String key = "sadljsdakjdsalkjfsalkjq3245452236790lkslkjda4sadsada";
		for ( int i = 0; i < len; i++ ) {
			int index = new Random().nextInt(key.length());
			builder.append(key.charAt(index));
		}
		
		return builder.toString();
	}
}
