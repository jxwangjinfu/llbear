package com.junfeng.platform.payment.bank.chinabank.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 功能：支付宝MD5签名处理核心文件，不需要修改 版本：3.3 修改日期：2012-08-17 说明： 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 */

public class MD5 {

	/**
	 * 签名字符串
	 *
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		return sign(text + key, input_charset);
	}

	/**
	 * 签名字符串
	 *
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String input_charset) {
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * 签名字符串
	 *
	 * @param text 需要签名的字符串
	 * @param sign 签名结果
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String input_charset) {
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 签名字符串
	 *
	 * @param text 需要签名的字符串
	 * @param sign 签名结果
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		return verify(text + key, sign, input_charset);
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	public static String getSignContent(Object model) {
		StringBuffer sb = new StringBuffer();
		Field[] fields = model.getClass().getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (Field field : fields) {
			fieldList.add(field.getName());
		}
		// 排序
		Collections.sort(fieldList);

		try {
			String methodName;
			for (String fieldName : fieldList) {
				// 将属性的首字符大写，方便构造
				methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method m = model.getClass().getMethod("get" + methodName);
				// 获取属性的类型
				Object v = m.invoke(model);
				if (v != null && v != "")
					sb.append(fieldName).append("=").append(v).append("&");
			}
		} catch (Exception e) {
		}

		return sb.toString();
	}

	public static String encrypt16MD5(String paramString) {
		String str = encryptMD5(paramString);
		return str.substring(16);
	}

	public static String encryptMD5(String paramString) {
		char[] arrayOfChar1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] arrayOfByte = paramString.getBytes();
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(arrayOfByte);
			arrayOfByte = localMessageDigest.digest();
			int i = arrayOfByte.length;
			char[] arrayOfChar2 = new char[i * 2];
			int j = 0;
			for (int k = 0; k < arrayOfByte.length; ++k) {
				int l = arrayOfByte[k];
				arrayOfChar2[(j++)] = arrayOfChar1[(l >>> 4 & 0xF)];
				arrayOfChar2[(j++)] = arrayOfChar1[(l & 0xF)];
			}
			return new String(arrayOfChar2);
		} catch (Exception localException) {
		}
		return null;
	}

	public static void main(String[] args) {
		// Demo d = new Demo();
		// d.setAoo("aaaa");
		// d.setBoo("asdasd");
		// d.setOpr("sadasd");
		// d.setZc("zzzzz");
		// d.setZoo("aaaaaaaaa");
		//
		// String signKey = "!)(@*&#$$A$$@";
		//
		// System.out.println("XML:" + JaxbMapper.toXml(d));
		//
		// String signContent = getSignContent(d);
		// System.out.println("Sign Content:" + signContent + signKey);
		//
		// String signRs = MD5.sign(signContent + signKey, "UTF-8");
		// System.out.println("Sign Result:" + signRs);
		//
		// d.setSign(signRs);
		//
		// System.out.println("XML:" + JaxbMapper.toXml(d));
		//
		// String testStr = "73c3f9c41f23845618a705e6eed01086";
		// System.out.println("Test Result:" + MD5.verify(signContent + signKey, testStr, "UTF-8"));

		String str = "{\"txn_id\": \"154\", \"vp_systrace\": \"908872\", \"mall_code\": \"0202A501\", \"mobile\": \"15017906325\", \"chnl\": \"001\", \"mem_id\": \"001\", \"org_code\": \"0202A501\"}&&1AC$@!BGT98aw#C";
		System.out.println("Test Result:" + MD5.sign(str, "UTF-8"));
	}
}
