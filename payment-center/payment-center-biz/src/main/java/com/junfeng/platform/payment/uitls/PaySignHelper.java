package com.junfeng.platform.payment.uitls;

import com.junfeng.platform.payment.uitls.notify.common.PaymentCenterConst;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 业务系统请求支付中心接口参数签名工具类
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月14日 下午3:25:33
 */
public class PaySignHelper {
	private final static Logger LOGGER = LogManager.getLogger();
	private final static String SIGN_NAME = "sign";
	private final static String DEFAULT_SECRET = "junhe@323ddkkHHj&kk;l";

	/**
	 * 组装参数
	 *
	 * @param params2
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String concatParams(Map<String, String> params2)
		throws UnsupportedEncodingException {
		Object[] key_arr = params2.keySet().toArray();
		Arrays.sort(key_arr);
		String str = "";

		for (Object key : key_arr) {
			if (((String) key).equalsIgnoreCase(SIGN_NAME)) {
				continue;
			}
			String val = params2.get(key);
			if (val == null) {
				// 参数值为空，不参与签名
				continue;
			}
			key = URLEncoder.encode(key.toString(), "UTF-8");
			val = URLEncoder.encode(val, "UTF-8");
			str += "&" + key + "=" + val;
		}

		return str.replaceFirst("&", "");
	}

	/**
	 * byte To HEX
	 *
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer buf = new StringBuffer();
		int i;

		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}

		return buf.toString();
	}

	/**
	 * 签名
	 *
	 * @param params
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String genSig(Map<String, String> params)
		throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return genSig(params, DEFAULT_SECRET);
	}

	/**
	 * 签名
	 *
	 * @param params
	 * @param consumerSecret
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String genSig(Map<String, String> params,
								String consumerSecret) {
		try {
			String str = concatParams(params);
			// System.out.println("字符串A="+str);
			str = str + consumerSecret;
			// System.out.println("字符串B="+str);
			MessageDigest md = MessageDigest.getInstance("SHA1");
			// System.out.println("字符串C="+byte2hex(str.getBytes("UTF-8")));
			String sign = byte2hex(
				md.digest(byte2hex(str.getBytes("UTF-8")).getBytes()));
			LOGGER.info("sign:{}", sign);
			return sign;

		} catch (Exception e) {
			LOGGER.error("byte2hex exception:{}", e.getMessage());
			return "";
		}
	}

	/**
	 * 验证收银台POST请求签名
	 *
	 * @param vo
	 * @param sign
	 * @return
	 */
	public static Boolean checkRequestSign(Object vo, String sign) {
		Map<String, String> params = new HashMap<String, String>();

		// params.put("timestamp", String.valueOf(timestamp));

		try {
			Class<?> voClass = (Class<?>) vo.getClass();
			//Field[] fs = voClass.getDeclaredFields();
			List<Field> fieldList = new ArrayList<>();
			while (voClass != null && !voClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
				fieldList.addAll(Arrays.asList(voClass.getDeclaredFields()));
				voClass = voClass.getSuperclass(); //得到父类,然后赋给自己
			}
			for (int i = 0; i < fieldList.size(); i++) {
				Field f = fieldList.get(i);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(vo);// 得到此属性的值

				if (val != null) {
					if (val instanceof Date) {
						params.put(f.getName(),
							String.valueOf(((Date) val).getTime()));
					} else {
						params.put(f.getName(), String.valueOf(val));
					}
				}

			}
			for (Map.Entry<String, String> value : params.entrySet()) {
				LOGGER.info("name={},value={}", value.getKey(),
					value.getValue());
			}
			if (StringUtils.equals(sign, PaySignHelper.genSig(params))) {
				return true;
			} else {
				LOGGER.error("checkRequestSign,sign={},vo={}", sign,
					vo.toString());
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("checkRequestSign error：{}", e.getMessage());
			return false;
		}
	}

	public static Boolean checkRequestSign(Object vo) {
		String sign = "";
		Map<String, String> params = new HashMap<String, String>();

		// params.put("timestamp", String.valueOf(timestamp));

		try {
			Class<?> voClass = (Class<?>) vo.getClass();
			//Field[] fs = voClass.getDeclaredFields();
			List<Field> fieldList = new ArrayList<>();
			while (voClass != null && !voClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
				fieldList.addAll(Arrays.asList(voClass.getDeclaredFields()));
				voClass = voClass.getSuperclass(); //得到父类,然后赋给自己
			}
			for (int i = 0; i < fieldList.size(); i++) {
				Field f = fieldList.get(i);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(vo);// 得到此属性的值
				if (f.getName().equals(SIGN_NAME)) {
					sign = String.valueOf(val);
					continue;
				}
				if (val != null) {

					if (val instanceof Date) {
						params.put(f.getName(),
							String.valueOf(((Date) val).getTime()));
					} else {
						params.put(f.getName(), String.valueOf(val));
					}
				}

			}
			for (Map.Entry<String, String> value : params.entrySet()) {
				LOGGER.info("name={},value={}", value.getKey(),
					value.getValue());
			}
			if (StringUtils.equals(sign, PaySignHelper.genSig(params, PaymentCenterConst.KEY))) {
				return true;
			} else {
				LOGGER.error("checkRequestSign,sign={},vo={}", sign,
					vo.toString());
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("checkRequestSign error：{}", e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
		UnsupportedEncodingException {
		Long timestamp = System.currentTimeMillis();
		SortedMap<String, String> param = new TreeMap<>();
		param.put("payMchId", "19");
		param.put("nonceString", "ckgok4x6");
		param.put("timestamp", "1556523515893");
		param.put("mchOrderNo", "WX_BARCODE2019042903383092356637");
		param.put("tradeOrderNo", "201904291538328246095806");
		param.put("payOrderNo", "201904291538328246095806");
		param.put("paySuccessTimestamp", null);
		param.put("paymentModeCode", "WX_BARCODE");

		System.out.println(genSig(param, "12345abc"));
//	 TestObject obj = new TestObject();
//	 obj.setId(123);
//	 obj.setTimestamp(timestamp);
//	 System.out.println(checkRequestSign(obj,genSig(map)));
	}

}
