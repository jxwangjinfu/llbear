package com.junfeng.platform.payment.bank.cmbank.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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

    public static String map2xml(Map<String, String> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            //root.addElement(key).addCDATA(map.get(key));
            if(StringUtils.isNotEmpty(map.get(key))) {
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
            root.addElement(key).addText(   map.get(key) );
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub



    }

}
