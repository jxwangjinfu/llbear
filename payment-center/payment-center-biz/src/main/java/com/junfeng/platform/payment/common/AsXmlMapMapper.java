package com.junfeng.platform.payment.common;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 简单封装xstream，实现xml String<->Java Object的Mapper.
 *
 */
public class AsXmlMapMapper {
    private static Map<String, XStream> xstreamMap = new HashMap<String, XStream>();

    static {
        for(ParseXmlClass obj : ParseXmlClass.values()) {
            XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {
                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out,super.getNameCoder()) {
                        // 对所有xml节点的转换都增加CDATA标记
                        boolean cdata = true;

                        @SuppressWarnings("rawtypes")
                        public void startNode(String name, Class clazz) {
                            super.startNode(name);
                        }

                        protected void writeText(QuickWriter writer, String text) {

                            if (cdata && !StringUtils.isNumeric(text)) {
                                writer.write("<![CDATA[");
                                writer.write(text);
                                writer.write("]]>");
                            } else {
                                writer.write(text);
                            }
                        }
                    };
                }
            });
            xstream.processAnnotations(obj.getClasz());
//          SpringBoot环境下，XML转对象时，同一个类无法进行转换，原因是因为SpringBoot重新加载了对象；若未指定classloader的时候，SpringBoot未正确使用classloader，需要指定classloader，需要在方法中指定加载的类，添加如下代码：
            xstream.setClassLoader(obj.getClasz().getClassLoader());
            xstream.ignoreUnknownElements();
            xstreamMap.put(obj.name(), xstream);
        }
    }

	/**
	 * 判断object是否为null。为null则抛出异常
	 *
	 * @param object
	 */
	private static void notNull(Object object) {
		if (object == null || object.equals("")) {
			throw new IllegalArgumentException("object is null");
		}
	}

	public AsXmlMapMapper() {
		super();
	}

//	public static <T> String toXml(String className,T object, Class<T> clazz) throws XStreamException {
//		notNull(object);
//		XStream xstream = xstreamMap.get(className);
//		xstream.processAnnotations(clazz);
//		return xstream.toXML(object);
//	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String className,String xmlString, Class<T> clazz) throws XStreamException {
		notNull(xmlString);
		XStream xstream = xstreamMap.get(className);
		xstream.processAnnotations(clazz);
//		SpringBoot环境下，XML转对象时，同一个类无法进行转换，原因是因为SpringBoot重新加载了对象；若未指定classloader的时候，SpringBoot未正确使用classloader，需要指定classloader，需要在方法中指定加载的类，添加如下代码：
        xstream.setClassLoader(clazz.getClassLoader());
		xstream.ignoreUnknownElements();
		return (T) xstream.fromXML(xmlString);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String className,File file, Class<T> clazz) throws XStreamException {
		notNull(file);
		XStream xstream = xstreamMap.get(className);
		xstream.processAnnotations(clazz);
		xstream.ignoreUnknownElements();
		return (T) xstream.fromXML(file);
	}

	enum ParseXmlClass {
	    JxBankMicroPayResult(com.junfeng.platform.payment.bank.jxbank.model.JxBankMicroPayResult.class),
	    JxBankOrderQueryResult(com.junfeng.platform.payment.bank.jxbank.model.JxBankOrderQueryResult.class),
	    JxBankRefundOrderResult(com.junfeng.platform.payment.bank.jxbank.model.JxBankRefundOrderResult.class),
	    CiticBankMircoPayResult(com.junfeng.platform.payment.bank.citicbank.model.CiticBankMircoPayResult.class),
	    CiticBankQueryOrderResult(com.junfeng.platform.payment.bank.citicbank.model.CiticBankQueryOrderResult.class),
	    CiticBankRefundResult(com.junfeng.platform.payment.bank.citicbank.model.CiticBankRefundResult.class),
	    CmBankMircoPayResult(com.junfeng.platform.payment.bank.cmbank.model.CmBankMircoPayResult.class),
	    CmBankQueryOrderResult(com.junfeng.platform.payment.bank.cmbank.model.CmBankQueryOrderResult.class),
	    ConstructionBankRefundResult(com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankRefundResult.class),
	    CiticBankMiniPaymentResult(com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniPaymentResult.class),
	    CiticBankMiniRefundResult(com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniRefundResult.class),
	    CiticBankMiniQueryResult(com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniQueryResult.class),
;
	    private Class<?> clasz;

        private ParseXmlClass(Class<?> clasz) {
            this.clasz = clasz;
        }

        public Class<?> getClasz() {
            return clasz;
        }

        public void setClasz(Class<?> clasz) {
            this.clasz = clasz;
        }


	}

}
