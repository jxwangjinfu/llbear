package com.junfeng.platform.errorwarner;

/**
 * @author fuh
 * @version 1.0 2019/10/23 10:27
 */
public final class ErrorVoBuilder {
	private ErrorVo errorVo;

	private ErrorVoBuilder() {
		errorVo = new ErrorVo();
	}

	public static ErrorVoBuilder anErrorVo() {
		return new ErrorVoBuilder();
	}

	public ErrorVoBuilder withServiceName(String serviceName) {
		errorVo.setServiceName(serviceName);
		return this;
	}

	public ErrorVoBuilder withCode(String code) {
		errorVo.setCode(code);
		return this;
	}

	public ErrorVoBuilder withMessage(String message) {
		errorVo.setMessage(message);
		return this;
	}

	public ErrorVoBuilder withNotifyType(NotifyType notifyType) {
		errorVo.setNotifyType(notifyType);
		return this;
	}

	public ErrorVoBuilder withSendMailTo(String sendMailTo) {
		errorVo.setSendMailTo(sendMailTo);
		return this;
	}

	public ErrorVoBuilder withDingTalkMessageType(DingTalkMessageType dingTalkMessageType) {
		errorVo.setDingTalkMessageType(dingTalkMessageType);
		return this;
	}

	public ErrorVoBuilder withUrl(String url) {
		errorVo.setUrl(url);
		return this;
	}

	public ErrorVoBuilder withPictureUrl(String pictureUrl) {
		errorVo.setPictureUrl(pictureUrl);
		return this;
	}

	public ErrorVoBuilder withWebhook(String webhook) {
		errorVo.setWebhook(webhook);
		return this;
	}

	public ErrorVo build() {
		return errorVo;
	}
}
