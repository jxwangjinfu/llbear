package com.junfeng.platform.payment.uitls.notify.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junfeng.platform.payment.common.AsJsonMapper;
import com.junfeng.platform.payment.common.RandomUtils;
import com.junfeng.platform.payment.uitls.PaySignHelper;
import com.junfeng.platform.payment.uitls.notify.common.PaymentCenterConst;
import com.junfeng.platform.payment.uitls.notify.common.PaymentCenterWebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 支付中心通知业务系统请求模板抽象类
 * @projectName:tobacco-cloud-common
 * @author:chenyx
 * @date:2018年8月16日 下午2:51:36
 * @version 1.0
 */
public abstract class PaymentCenterBaseRequest {
	private final static Logger LOGGER = LoggerFactory.getLogger(PaymentCenterBaseRequest.class);

	/**
	 * 支付中心商户ID
	 */
	protected String payMchId;
	/**
	 * 请求URL
	 */
	protected String url;
	/**
	 * 时间戳，单位：毫秒
	 */
	protected String timestamp = String.valueOf(System.currentTimeMillis());
	/**
	 * 随机数：8 位
	 */
	protected String nonceString = RandomUtils.generateLowerString(8);
	/**
	 * 获取request独有参数
	 */
	public abstract Map<String, String> getParams();
	/**
	 * 钩子方法：判断是否参数中包含NonceString和timestamp
	 * <pre>
	 * 默认包含，如果不需要，由子类覆盖该方法
	 * </pre>
	 * @return
	 */
	public boolean containsNonceStringTimestamp() {
		return true;
	}

	/**
	 * 获取需要签名的参数
	 */
	private SortedMap<String, String> getShareParams() {
		SortedMap<String, String> sysParams = new TreeMap<String, String>();
		sysParams.put("payMchId", payMchId);
		if(containsNonceStringTimestamp()) {
			sysParams.put("nonceString", nonceString);
			sysParams.put("timestamp", timestamp);
		}
		return sysParams;
	}

	/**
	 * 根据公共参数和每个request独有的参数计算sign值
	 */
	public String getSign() {

		SortedMap<String, String> params = this.getShareParams();
		params.putAll(this.getParams());
		String sign= PaySignHelper.genSig(params, PaymentCenterConst.KEY);
		return sign;
	}

	/**
	 * 请求参数（包含签名）
	 *
	 * @return
	 */
	public Map<String, String> getRequestParams() {
		SortedMap<String, String> map = this.getShareParams();
		map.putAll(this.getParams());
		String sign = getSign();
		map.put("sign", sign);

		return map;
	}
	/**
	 * 请求参数(转为Json的string类型)
	 * @return
	 */
	public String getRequestParamJsonString() {

		try {
			String requestParamJsonString = AsJsonMapper.toJson(getRequestParams());
			LOGGER.info("getRequestParamJsonString={}",requestParamJsonString);
			return requestParamJsonString;
		} catch (JsonProcessingException e) {
			LOGGER.error("getRequestParamString error={}", e.getMessage());
			return "";
		}
	}
//	/**
//	 * 请求参数加密
//	 * @return
//	 */
//	public String getEncrypt() {
//		return Des64Utils.des64(getRequestParamJsonString(), PaymentCenterConst.IV1, PaymentCenterConst.IV2);
//	}
//	/**
//	 * 获取最终请求参数
//	 * @return
//	 */
//	public String getFinallyRequestParamString() {
//
//		Long startTime = System.currentTimeMillis();
//		SortedMap<String, String> params = new TreeMap<String, String>();
//		params.put("productId", DEFAULT_PRODUCT_ID);
//		params.put("storeId", storeId);
//		try {
//			params.put("Encrypt", URLEncoder.encode(getEncrypt(), "utf-8"));
//			String paramsStr = AsJsonMapper.toJson(params);
//			LOGGER.info("getFinallyRequestParamString time : {}", System.currentTimeMillis() - startTime);
//			return paramsStr;
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.error("dodopay getFinalRequestParamString error={}", PrintStackTraceUtils.getErrorInfoFromException(e));
//			return "";
//		} catch (JsonProcessingException e) {
//			LOGGER.error("dodopay getFinalRequestParamString error={}", PrintStackTraceUtils.getErrorInfoFromException(e));
//			return "";
//		}
//	}
	/**
	 * 得到请求结果(字符串格式)
	 *
	 * @return
	 * @throws IOException
	 */
	public String doRequest() throws IOException {
		return PaymentCenterWebUtils.doPost(this);
	}
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }



//	/**
//	 * 得到请求结果(对象格式)
//	 *
//	 * @return
//	 * @throws IOException
//	 */
//	public BaiduResultBody doRequestReturnBody() throws IOException {
//		String result = DodoPayWebUtils.doPost(this);
//		logger.info("result={}", result);
//		BaiduResultBody body = new BaiduResultBody();
//		if (StringUtils.isNotBlank(result)) {
//			BaiduResult baiduResult = AsJsonMapper.fromJson(result, BaiduResult.class);
//			body = baiduResult.getBody();
//		} else {
//			body.setErrno(BaiduResultErrorEnum.REQUEST_ERROR.getErrno());
//			body.setError(BaiduResultErrorEnum.REQUEST_ERROR.getError());
//		}
//		return body;
//	}
}
