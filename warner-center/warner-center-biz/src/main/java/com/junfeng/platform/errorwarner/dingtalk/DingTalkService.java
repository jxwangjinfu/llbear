package com.junfeng.platform.errorwarner.dingtalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.request.OapiRobotSendRequest.Actioncard;
import com.dingtalk.api.request.OapiRobotSendRequest.At;
import com.dingtalk.api.request.OapiRobotSendRequest.Link;
import com.dingtalk.api.request.OapiRobotSendRequest.Text;
import com.junfeng.platform.errorwarner.DingTalkMessageType;
import com.junfeng.platform.errorwarner.ErrorVo;
import com.taobao.api.ApiException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * 钉钉接口工具类
 *
 * @author daiysh
 * @date 2019-10-22 13:15
 **/
public class DingTalkService {

	@Value("${webhooks.default}")
	@Getter
	private String defaultWebhook;


	public void sendMessage(ErrorVo errorVo) throws Exception {

		DingTalkMessageType msgTypeToUse = errorVo.getDingTalkMessageType() == null ? DingTalkMessageType.TEXT : errorVo.getDingTalkMessageType();

		if (errorVo.getCode() == null || errorVo.getMessage() == null) {
			throw new IllegalArgumentException("code和message不能为空");
		}


		if (DingTalkMessageType.TEXT.equals(msgTypeToUse)) {
			sendMessageText(errorVo.getCode(), errorVo.getMessage(), errorVo.getWebhook());
		} else if (DingTalkMessageType.ACTION_CARD.equals(msgTypeToUse)) {
			Assert.notNull(errorVo.getUrl(),"ACTION_CARD必须配置url");
			sendMessageActionCard(errorVo.getCode(), errorVo.getMessage(), errorVo.getUrl(), errorVo.getWebhook());
		} else if (DingTalkMessageType.LINK.equals(msgTypeToUse)) {
			Assert.notNull(errorVo.getUrl(),"LINK必须配置url");
			sendMessageLink(errorVo.getCode(), errorVo.getMessage(), errorVo.getUrl(), errorVo.getPictureUrl(), errorVo.getWebhook());
		} else {
			throw new IllegalArgumentException("wrong msgType");
		}

	}

	public void sendMessageText(String title, String message, String webhook) throws Exception {

		String webhookToUse = checkWebhookNull(webhook);

		DingTalkClient client = new DefaultDingTalkClient(webhookToUse);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		Text text = new Text();
		text.setContent(title + "\r\n" + message);
		request.setText(text);
		At at = new At();
		at.setIsAtAll(String.valueOf(true));
		client.execute(request);
	}

	public void sendMessageActionCard(String title, String message, String url, String webhook) throws ApiException {
		String webhookToUse = checkWebhookNull(webhook);

		DingTalkClient client = new DefaultDingTalkClient(webhookToUse);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("actionCard");
		Actioncard actioncard = new Actioncard();
		actioncard.setTitle(title);
		actioncard.setText(message);
		actioncard.setSingleTitle("阅读全文");
		actioncard.setSingleURL(url);
		request.setActionCard(actioncard);
		client.execute(request);
	}

	public void sendMessageLink(String title, String message, String messageUrl, String picUrl, String webhook) throws ApiException {
		String webhookToUse = checkWebhookNull(webhook);

		DingTalkClient client = new DefaultDingTalkClient(webhookToUse);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("link");
		Link link = new Link();
		link.setMessageUrl("日志分析平台");
		link.setPicUrl("");
		link.setTitle(title);
		link.setText(message);
		request.setLink(link);
		client.execute(request);
	}

	private String checkWebhookNull(String webhook) {
		if (webhook == null && defaultWebhook == null) {
			throw new IllegalArgumentException("webhook is null");
		}
		return webhook == null ? defaultWebhook : webhook;
	}
}
