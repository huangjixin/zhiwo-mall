package com.zwo.modules.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MixCodeUtil {

	// 生成商品编号
	public static String createOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		Random r = new Random();
		int x = r.nextInt(999999 - 100000 + 1) + 100000;
		key = key + x;
		return key;
	}

}