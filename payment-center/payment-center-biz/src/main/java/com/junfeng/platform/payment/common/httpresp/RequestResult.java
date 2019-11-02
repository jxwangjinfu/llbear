package com.junfeng.platform.payment.common.httpresp;


import org.apache.commons.lang.builder.ToStringBuilder;

public class RequestResult {
	private Integer errorCode = 0;
	private String errorMessage;
	private Object data;

	public RequestResult() {
		super();
	}

	public RequestResult(RequestResultCode code){
		super();
		this.errorCode = code.getErrorCode();
		this.errorMessage = code.getErrorMessage();
	}

	public RequestResult(RequestResultCode code,Object data){
		super();
		this.errorCode = code.getErrorCode();
		this.errorMessage = code.getErrorMessage();
		this.data = data;
	}

	public RequestResult(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public RequestResult(Integer errorCode, String errorMessage, Object data) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setErrorResultType(RequestResultCode code){
		this.errorCode = code.getErrorCode();
		this.errorMessage = code.getErrorMessage();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
