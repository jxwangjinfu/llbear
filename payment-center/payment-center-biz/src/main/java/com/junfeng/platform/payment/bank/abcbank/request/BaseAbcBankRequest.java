package com.junfeng.platform.payment.bank.abcbank.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.abcbank.common.AbcBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.common.AbcBankConstant;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankCallbackResult;
import com.junfeng.platform.payment.bank.abcbank.utils.AbcBankSignUtils;
import com.junfeng.platform.payment.bank.abcbank.utils.AbcBankValidUtils;
import com.junfeng.platform.payment.bank.abcbank.utils.AbcBankWebUtils;
import com.junfeng.platform.payment.common.AsXmlMapper;
import jodd.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 农业银行支付请求基础类
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月9日 下午3:18:02
 */
public abstract class BaseAbcBankRequest {

	/**
	 * 商户提供的私钥
	 */
	protected String mchPrivateKey;

	/**
	 * 交易类型
	 */
	protected String transType;

	/**
	 * 公共参数
	 *
	 * @return
	 * @throws Exception
	 * @author:xiongh
	 * @createTime:2019年3月9日 下午3:25:38
	 */
	public Map<String, String> getSharedParams() throws Exception {
		// sendPackage参数获得
		Map<String, String> platformMap = getPlatformParams();
		String jsonValue = JSON.toJSONString(platformMap);
		jsonValue = String.format("%06d", jsonValue.getBytes().length) + jsonValue;
		jsonValue = Base64.encodeToString(jsonValue);
		// 封装sendPackage的内容
		Map<String, String> sendPackageMap = new HashMap<>();
		sendPackageMap.put("sendpacktype", AbcBankConstant.PACKET_TYPE);
		sendPackageMap.put("sendData", jsonValue);
		String sendPackageData = JSON.toJSONString(sendPackageMap);
		Map<String, String> map = new HashMap<>();
		map.put("os", AbcBankConstant.OS);
		map.put("appVersion", AbcBankConstant.APP_VERSION);
		map.put("reqDate", DateFormatUtils.format(new Date(), "yyyyMMdd"));
		map.put("reqTime", DateFormatUtils.format(new Date(), "HHmmss"));
		map.put("provCode", AbcBankConstant.PROVINCE_CODE);
		map.put("srcAreaCode", AbcBankConstant.PROVINCE_CODE);
		map.put("accessType", AbcBankConstant.ACCESS_TYPE);
		map.put("httpWebPath", AbcBankConstant.HTTP_WEB_PATH);
		map.put("charset", "GB2312");
		map.put("retCharset", "GB2312");
		map.put("sysId", "httpSite");
		// map.put("packetType", AbcBankConstant.PACKET_TYPE);
		map.put("sendPackage", URLEncoder.encode(sendPackageData, "UTF-8"));
		return map;
	}

	/**
	 * 平台参数
	 *
	 * @return
	 * @throws Exception
	 * @author:xiongh
	 * @createTime:2019年3月14日 上午10:46:12
	 */
	public Map<String, String> getPlatformParams() throws Exception {
		Map<String, String> tradeMap = getParams();
		Map<String, String> map = new HashMap<>();
		map.put("trans_type", this.transType);
		map.put("app_id", AbcBankConstant.APP_ID);
		map.put("format", "json");
		map.put("charset", "UTF-8");
		map.put("encrypt_type", "RSA");
		map.put("sign_type", "RSA2");
		map.put("timestamp", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		map.put("biz_content", encryptParams(tradeMap));
		map.put("sign", getSign(tradeMap));
		return map;
	}

	public abstract Map<String, String> getParams();

	/**
	 * 给交易参数设置签名
	 *
	 * @return
	 * @throws Exception
	 * @author:xiongh
	 * @createTime:2019年3月14日 上午11:01:45
	 */
	public String getSign(Map<String, String> map) throws Exception {
		String jsonValue = JSON.toJSONString(map);
		jsonValue = AbcBankSignUtils.sign(jsonValue, this.mchPrivateKey);
		return jsonValue;
	}

	/**
	 * 对交易参数做RSA加密
	 *
	 * @return
	 * @throws Exception
	 * @author:xiongh
	 * @createTime:2019年3月14日 上午11:00:12
	 */
	public String encryptParams(Map<String, String> map) throws Exception {
		byte[] encryptValue = AbcBankSignUtils.encrypt(map, AbcBankConstant.bankPublicKey);
		encryptValue = Base64.encodeToByte(encryptValue);
		return new String(encryptValue);
	}

	/**
	 * 参数格式验证
	 */
	public String valid() {
		return AbcBankValidUtils.valid(this);
	}

	/**
	 * 根据request中指定的请求方式发送http请求
	 *
	 * @return String json字符串, 错误信息或者请求结果, 示例: "{"data":{}}", "{"data":"errorMsg"}"
	 * @throws Exception
	 * @throws RuntimeException 如果缺少必要参数,会抛出该异常
	 */
	public String doRequest() throws Exception {
		String errorMsg = this.valid();
		if (errorMsg != null && !"".equals(errorMsg)) {
			throw new RuntimeException("参数校验错误: " + errorMsg);
		}
		return AbcBankWebUtils.doPost(this);
	}

	/**
	 * 获得返回结果
	 *
	 * @return
	 * @throws Exception
	 * @author:xiongh
	 * @createTime:2019年3月14日 下午2:22:06
	 */
	@SuppressWarnings("unchecked")
	public String getRecvMessage() throws Exception {
		String result = doRequest();
		System.out.println(result);
		AbcBankCallbackResult abcBankCallbackResult = AsXmlMapper.fromXml(result, AbcBankCallbackResult.class);
		String recvPackage = abcBankCallbackResult.getRecvPackage();
		String explain = abcBankCallbackResult.getRespExplain();
		// 获得返回结果
		if ("Success".equals(explain)) {
			if (StringUtils.isNotBlank(recvPackage)) {
				recvPackage = URLDecoder.decode(recvPackage, "utf-8");
				Map<String, String> map = JSON.parseObject(recvPackage, Map.class);
				// 返回成功才做解析
				if (AbcBankCallbackResultCodeEnum.SUCCESS.getValue().equals(map.get("recvCode"))) {
					recvPackage = map.get("recvData");
					recvPackage = Base64.decodeToString(recvPackage);
				} else {
					return AbcBankCallbackResultCodeEnum.FAIL.getValue();
				}
			} else {
				return AbcBankCallbackResultCodeEnum.FAIL.getValue();
			}
		} else {
			return AbcBankCallbackResultCodeEnum.FAIL.getValue();
		}
		// 前面有6位是表示该json多少个字节数，要把他去掉
		recvPackage = recvPackage.substring(6, recvPackage.length());
		Map<String, String> map = JSONObject.parseObject(recvPackage, Map.class);
		if (StringUtils.isNotBlank(map.get("result_code"))) {
			if (StringUtils.equals(AbcBankCallbackResultCodeEnum.SUCCESS.getValue(), map.get("result_code"))) {
				String content = String.valueOf(map.get("response_biz_content"));
				// base64解密，然后再用私钥解密
				byte[] responseResult = Base64.decode(content);
				return AbcBankSignUtils.decrypt(responseResult, this.mchPrivateKey);
			} else {
				return AbcBankCallbackResultCodeEnum.FAIL.getValue();
			}
		} else {
			return AbcBankCallbackResultCodeEnum.FAIL.getValue();
		}
	}

}
