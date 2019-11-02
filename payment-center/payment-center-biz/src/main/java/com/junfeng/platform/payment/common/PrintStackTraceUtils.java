package com.junfeng.platform.payment.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintStackTraceUtils {

	/**
	 * 打印错误信息
	 * @param e
	 * @return
	 */
	public static String getErrorInfoFromException(Throwable e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

	/**
	 * 打印错误信息
	 * @param e
	 * @return
	 */
	public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
