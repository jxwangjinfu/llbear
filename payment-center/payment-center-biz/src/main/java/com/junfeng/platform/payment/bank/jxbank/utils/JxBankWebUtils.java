package com.junfeng.platform.payment.bank.jxbank.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.junfeng.platform.payment.bank.jxbank.model.base.BaseBean;
import org.apache.commons.lang.StringUtils;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.junfeng.platform.payment.bank.jxbank.common.JxBankConstant;
import com.junfeng.platform.payment.bank.jxbank.request.JxBankBaseRequest;
import com.junfeng.platform.payment.common.AsXmlMapper;

/**
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月17日 下午5:02:48
 * @version 1.0
 */
public class JxBankWebUtils {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String HTTP_STATUS_FAIL = "请求返回http status 错误";
    /**
     * 普通POST的请求
     *
     * @param request
     * @return
     * @author:xionghui
     * @throws URISyntaxException
     * @throws IOException
     * @createTime:2018年8月17日 下午4:12:47
     */
    public static String doPost(JxBankBaseRequest request) throws URISyntaxException, IOException {
        String params = TanslateUtil.map2xml(request.getRequestParams());
        return post(JxBankConstant.BANK_HOST+request.getUri(), params);
    }

    /**
     * xml参数请求
     *
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @author:xionghui
     * @createTime:2018年8月17日 下午5:02:16
     */
    private static String post(String url, String params) throws URISyntaxException, IOException {

        URI target = new URI(url);
        if (!target.isAbsolute() || URIUtils.extractHost(target) == null) {
            BaseBean baseBean = new BaseBean();
            baseBean.setRetcode(-1);
            baseBean.setRetmsg("指定url不合法");
            return AsXmlMapper.toXml(baseBean, BaseBean.class);
        }

        // 创建post请求
        HttpClient httpClient = wrapClient(url);
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "text/xml; charset=UTF-8");

        if (StringUtils.isNotEmpty(params)) {
            StringEntity entityParams = new StringEntity(params, "utf-8");
            post.setEntity(entityParams);
        }

        return JxBankWebUtils.getResult(httpClient, url, post);
    }

    /**
     * 发起请求,返回响应的结果
     *
     * @param httpClient
     * @param url
     * @param method
     * @return
     * @throws IOException
     * @author:xionghui
     * @createTime:2018年8月17日 下午5:01:17
     */
    private static String getResult(HttpClient httpClient, String url, HttpPost method) throws IOException {
        HttpResponse response = null;
        String result = null;

        try {
            // 接收response
            response = httpClient.execute(method);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                BaseBean baseBean = new BaseBean();
                baseBean.setRetcode(-1);
                baseBean.setRetmsg(HTTP_STATUS_FAIL+statusCode);
                return AsXmlMapper.toXml(baseBean, BaseBean.class);
            }

            // 获取返回体
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(response.getEntity());
            LOGGER.info("result is {}", result.toString());

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
