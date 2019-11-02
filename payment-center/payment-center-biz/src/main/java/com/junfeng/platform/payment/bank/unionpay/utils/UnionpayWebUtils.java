package com.junfeng.platform.payment.bank.unionpay.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.junfeng.platform.payment.bank.unionpay.model.base.BaseBean;
import com.junfeng.platform.payment.bank.unionpay.request.UnionpayBaseRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayConstant;

/**
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月28日 上午11:29:12
 * @version 1.0
 */
public class UnionpayWebUtils {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String HTTP_STATUS_FAIL = "请求返回http status 错误";

    /**
     * 普通POST请求
     *
     * @param request
     * @return
     * @author:xionghui
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws URISyntaxException
     * @throws IOException
     * @createTime:2018年8月28日 上午11:28:58
     */
    public static String doPost(UnionpayBaseRequest request, String auth)
            throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {

        String jsonString = JSONObject.toJSONString(request.getRequestParam());
        LOGGER.info("param:{}", jsonString);
        return post(UnionpayConstant.HOST + request.getUri(), jsonString, auth);
    }

    /**
     * 普通POST请求
     *
     * @param url
     * @param param
     * @param auth
     * @return
     * @author:xionghui
     * @throws URISyntaxException
     * @throws IOException
     * @createTime:2018年8月28日 上午11:31:55
     */
    private static String post(String url, String jsonString, String auth) throws URISyntaxException, IOException {
        URI target = new URI(url);
        if (!target.isAbsolute() || URIUtils.extractHost(target) == null) {
            BaseBean baseBean = new BaseBean();
            baseBean.setErrCode("-1");
            baseBean.setErrInfo("指定url不合法");
            return JSONObject.toJSONString(baseBean);
        }

        // 创建post请求
        HttpClient httpClient = wrapClient(url);
        HttpPost post = new HttpPost(url);
        post.setHeader("Authorization", auth);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");

        // 添加请求体参数
        if (jsonString != null) {
            // 添加请求体参数
           StringEntity entity = new StringEntity(jsonString,"utf-8");
           entity.setContentEncoding("UTF-8");
           entity.setContentType(ContentType.APPLICATION_JSON.toString());

           post.setEntity(entity);
       }
       LOGGER.info("post url={}",url);
       LOGGER.info("body={}",jsonString);

        return getResult(httpClient, url, post);
    }

    private static String getResult(HttpClient httpClient, String url, HttpPost post) throws IOException {
        HttpResponse response = null;
        String result = null;

        try {
            // 接收response
            response = httpClient.execute(post);


            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) {
//                BaseBean baseBean = new BaseBean();
//                baseBean.setErrCode("-1");
//                baseBean.setErrInfo(HTTP_STATUS_FAIL+statusCode);
//                LOGGER.error("statusCode:{}",statusCode);
//                return JSONObject.toJSONString(baseBean);
//            }

            // 获取返回体
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(response.getEntity());
            LOGGER.info("result is {}", result);

            // 释放资源
            EntityUtils.consume(entity);
        } catch (IOException ioe) {
            throw new IOException("IOException in request. Request Url:" + url, ioe);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL 创建ConnectionManager，添加Connection配置信息
     *
     * @return HttpClient 支持https
     */
    private static HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx,
                    NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取 HttpClient
     *
     * @param host
     * @param path
     * @return
     */
    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        if (host != null && host.startsWith("https://")) {
            return sslClient();
        }
        return httpClient;
    }

}
