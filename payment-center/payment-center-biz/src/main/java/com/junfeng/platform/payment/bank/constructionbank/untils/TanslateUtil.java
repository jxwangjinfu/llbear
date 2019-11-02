package com.junfeng.platform.payment.bank.constructionbank.untils;

import java.io.IOException;
 import java.io.StringWriter;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import java.util.TreeMap;

 import org.apache.commons.lang.StringUtils;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.io.XMLWriter;

 /**
 * map与xml互转
 *
 * @projectName:payment-temp
 * @author:xionghui
 * @date:2018年8月15日 下午2:25:59
 * @version 1.0
 */
public class TanslateUtil {

    private final static Logger LOGGER = LogManager.getLogger();

    public static String map2Serialization(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Entry<String, String> entry : map.entrySet()) {

            if (StringUtils.isNotBlank(entry.getValue())) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }

        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    public static String map2xml(Map<String, String> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            // root.addElement(key).addCDATA(map.get(key));
            if (StringUtils.isNotEmpty(map.get(key))) {
                root.addElement(key).addText(map.get(key));
            }

        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }

    public static String map2xmlNoNCDATA(Map<String, String> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            root.addElement(key).addText(map.get(key));
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }

    public static String mapToString(Map<String, String> map) {
        // 参数排序
        map = sortParams(map);
        String params = "";
        // 拼接参数
        Set<Entry<String, String>> setme = map.entrySet();
        for (Iterator<Entry<String, String>> it = setme.iterator(); it.hasNext();) {
            Entry<String, String> me = it.next();
            if (StringUtils.isNotEmpty(me.getValue())) {
                if (it.hasNext()) {
                    params += me.getKey() + "=" + me.getValue() + "&";
                } else {
                    params += me.getKey() + "=" + me.getValue();
                }

            }

        }
        LOGGER.info("mapToString:{}", params);
        return params;
    }

    public static String toSimpleXml(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Set<Entry<String, String>> es = map.entrySet();
        Iterator<Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            String k = entry.getKey();
            sb.append("<" + k + ">" + map.get(k) + "</" + k + ">\n");
        }
        return sb.toString();
    }

    public static String toXml(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\" ?>");
        sb.append("<TX>");
        Set<Entry<String, String>> es = map.entrySet();
        Iterator<Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            String k = entry.getKey();
            sb.append("<" + k + ">" + map.get(k) + "</" + k + ">\n");
        }
        sb.append("</TX>");
        return sb.toString();
    }

    private static Map<String, String> sortParams(Map<String, String> map) {
        if (map == null) {
            return null;
        }

        Map<String, String> m = new TreeMap<String, String>();
        for (Entry<String, String> o : map.entrySet()) {
            m.put(o.getKey(), o.getValue());
        }
        return m;
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

    }

}
