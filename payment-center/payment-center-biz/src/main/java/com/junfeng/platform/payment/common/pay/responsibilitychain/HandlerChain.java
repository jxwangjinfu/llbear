package com.junfeng.platform.payment.common.pay.responsibilitychain;

import com.google.common.collect.Lists;
import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 责任链任务处理
 * @author hujie
 *
 * @param <T>
 */
public class HandlerChain<T> {

	private final static Logger logger = LoggerFactory.getLogger(HandlerChain.class);
	private List<AbstractHandler<T>> handlers = Lists.newArrayList();

	public HandlerChain<T> addHandler(AbstractHandler<T> handler) {
		this.handlers.add(handler);
		return this;
	}

	public T doHandler(T handlerParam) {
		if(handlers == null) {
			throw new NullPointerException("handlers not null");
		}

		T result = handlerParam;
		for(AbstractHandler<T> handler : handlers) {
			try {
				result = handler.handleRequest(result);
			} catch (Exception e) {
				logger.error(PrintStackTraceUtils.getErrorInfoFromException(e));
			}
		}
		return result;
	}
}
