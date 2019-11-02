package com.junfeng.platform.payment.bank.constructionbank.request;

import com.junfeng.platform.payment.bank.constructionbank.common.ConstructionBankConstant;
import com.junfeng.platform.payment.bank.constructionbank.untils.CcBankSignUtils;
import com.junfeng.platform.payment.bank.constructionbank.untils.CcBankValidUtils;
import com.junfeng.platform.payment.bank.constructionbank.untils.CcBankWebUtils;
import com.junfeng.platform.payment.common.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public abstract class ConstructionBankBaseRequest {

	private final static Logger LOGGER = LogManager.getLogger();

	/**
	 * 商户ID
	 */
	protected String mchId;

	/**
	 * 加密key
	 */
	protected String key;

	/**
	 * 商户订单号
	 */
	protected String outTradeNo;

	/**
	 * 柜台号
	 */
	protected String posId;

	/**
	 * 随机数
	 */
	protected String nonceStr = RandomUtils.generateLowerString(8);

	/**
	 * 签名
	 */
	protected String sign;


	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}


	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/**
	 * 公共参数
	 *
	 * @return
	 * @author:xionghui
	 * @createTime:2018年8月23日 上午9:16:12
	 */
	public Map<String, String> getSharedParams() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MERFLAG", "1");
		map.put("MERCHANTID", this.mchId);
		map.put("POSID", this.posId);
		map.put("BRANCHID", ConstructionBankConstant.BRANCHID);
		map.put("ORDERID", this.outTradeNo);
		return map;
	}

	/**
	 * 加密签名
	 *
	 * @return
	 * @author:xionghui
	 * @createTime:2018年8月23日 上午9:16:40
	 */
	public String getSign() {
		Map<String, String> params = getSharedParams();
		if (getParams() != null) {
			params.putAll(getParams());
		}
		return CcBankSignUtils.getSign(params, this.key);
	}

	/**
	 * 请求参数（包含签名）
	 *
	 * @return
	 */
	public Map<String, String> getRequestParams() {
		Map<String, String> params = this.getSharedParams();
		params.put("MERCHANTID", this.mchId);
		params.put("POSID", this.posId);
		params.put("BRANCHID", ConstructionBankConstant.BRANCHID);
		params.put("ccbParam", this.getSign());
		return params;
	}

	/**
	 * 参数
	 *
	 * @return
	 * @author:xionghui
	 * @createTime:2018年8月23日 上午9:18:01
	 */
	public abstract Map<String, String> getParams();

	/**
	 * 参数格式验证
	 */
	public String valid() {
		return CcBankValidUtils.valid(this);
	}

	/**
	 * 根据request中指定的请求方式发送http请求
	 *
	 * @return String json字符串, 错误信息或者请求结果, 示例: "{"data":{}}", "{"data":"errorMsg"}"
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws RuntimeException   如果缺少必要参数,会抛出该异常
	 */
	public String doRequest() throws IOException, URISyntaxException {
		String errorMsg = this.valid();
		if (errorMsg != null && !"".equals(errorMsg)) {
			throw new RuntimeException("参数校验错误: " + errorMsg);
		}
		return CcBankWebUtils.doPost(this);

	}

}
