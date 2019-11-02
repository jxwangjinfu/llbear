package com.junfeng.platform.payment.common.pay.responsibilitychain;

public abstract class AbstractHandler<T> {

    //维持对下家的引用
	protected AbstractHandler<T> successor;


    public AbstractHandler<T> getSuccessor() {
		return successor;
	}

	public void setSuccessor(AbstractHandler<T> successor) {
		this.successor = successor;
	}

	public abstract T handleRequest(T requestParam) throws Exception;
}
