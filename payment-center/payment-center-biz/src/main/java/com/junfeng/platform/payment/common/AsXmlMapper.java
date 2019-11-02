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

/**
 *
 * 简单封装xstream，实现xml String<->Java Object的Mapper.
 *
 */
public class AsXmlMapper {

	private static XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {
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

	public AsXmlMapper() {
		super();
	}

	public static <T> String toXml(T object, Class<T> clazz) throws XStreamException {
		notNull(object);
		xstream.processAnnotations(clazz);
		return xstream.toXML(object);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xmlString, Class<T> clazz) throws XStreamException {
		notNull(xmlString);
		xstream.processAnnotations(clazz);
//		SpringBoot环境下，XML转对象时，同一个类无法进行转换，原因是因为SpringBoot重新加载了对象；若未指定classloader的时候，SpringBoot未正确使用classloader，需要指定classloader，需要在方法中指定加载的类，添加如下代码：
        xstream.setClassLoader(clazz.getClassLoader());
		xstream.ignoreUnknownElements();
		return (T) xstream.fromXML(xmlString);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(File file, Class<T> clazz) throws XStreamException {
		notNull(file);

		xstream.processAnnotations(clazz);
		xstream.ignoreUnknownElements();
		return (T) xstream.fromXML(file);
	}

}
