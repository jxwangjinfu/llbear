package com.junfeng.platform.payment.uitls.notify.common;

import com.junfeng.platform.payment.uitls.notify.request.PaymentCenterBaseRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HTTP工具类
 * <p>
 * chenyx
 */
public final class PaymentCenterWebUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCenterWebUtils.class);
	private static final String CONTENT_TYPE = "application/json";
	private static final String RESPONSE_NULL = "response is null";
	public static String doPost(PaymentCenterBaseRequest request) throws IOException {
		return post(request.getUrl(), request.getRequestParamJsonString());

	}

	/**
	 * 发起普通POST请求
	 *
	 * @throws IOException
	 *
	 */
	public static String post(String notifyUrl,String jsonData) throws IOException {

		// 创建post请求
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost post = new HttpPost(notifyUrl);
		// 添加请求体参数
		StringEntity stringEntity = new StringEntity(jsonData, "utf8");
		stringEntity.setContentType(CONTENT_TYPE);
		post.setEntity(stringEntity);
		return PaymentCenterWebUtils.getResult(httpClient, notifyUrl, post);
	}

	/**
	 * 发起请求,返回响应的json串
	 *
	 * @param httpClient
	 *            HttpClient
	 * @param url
	 *            请求的url
	 * @param method
	 *            请求方式,包括GET和POST
	 * @return json字符串, 错误信息或者请求结果, 示例: "{"data":{}}", "{"error":{}}"
	 * @throws IOException
	 */
	private static String getResult(CloseableHttpClient httpClient, String url, HttpRequestBase method)
			throws IOException {
		CloseableHttpResponse response = null;
		String result = null;
		long beginTimeMillis = System.currentTimeMillis();
		try {
			// 接收response
			response = httpClient.execute(method);
			if(response == null) {
				return RESPONSE_NULL;
			}
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
			    LOGGER.error("statusCode={}", statusCode);
				return String.valueOf(statusCode);
			}

			// 获取返回体
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(response.getEntity(), "utf-8");

			// 释放资源
			EntityUtils.consume(entity);
		} catch (IOException ioe) {
			throw new IOException("IOException in request. Request Url:" + url, ioe);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOGGER.error("Response close failed. Request Url:" + url, e);
				}
			}
		}
		long endTimeMillis = System.currentTimeMillis();
		LOGGER.info("http post time : {}", (endTimeMillis-beginTimeMillis));
		LOGGER.info("getResult={}", StringUtils.isBlank(result) ? "null" : result);
		return result;
	}



}
