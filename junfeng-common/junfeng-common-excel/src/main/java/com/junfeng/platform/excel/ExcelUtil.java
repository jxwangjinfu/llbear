package com.junfeng.platform.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fuh
 * @version 1.0 2019/10/25 13:43
 */
public class ExcelUtil {

	private ExcelUtil() {
	}

	/**
	 * 简洁方法读取excel中List对象，在类的字段注解@ExcelProperty
	 * @author: fuh
	 * @createTime: 2019/10/25 13:47
	 * @param is
	 * @param clz
	 * @return java.util.List<T>
	 */
	public static <T> List<T> readListFrom(InputStream is, Class<T> clz) {
		SyncReadListener tmpListener = new SyncReadListener();
		ReadSheet readSheet = new ReadSheet();
		readSheet.setClazz(clz);
		EasyExcel.read(is).registerReadListener(tmpListener).registerConverter(new LocalDateTimeConverter()).build().read(readSheet);
		return (List<T>) tmpListener.getList();
	}


	/**
	 * 简洁方法写入list对象，在类的字段注解@ExcelProperty
	 * @author: fuh
	 * @createTime: 2019/10/25 13:50
	 * @param os
	 * @param data
	 * @param clz
	 * @return void
	 */
	public static <T> void writeListTo(OutputStream os,List<T> data,Class<T> clz){
		WriteSheet writeSheet = new WriteSheet();
		writeSheet.setClazz(clz);
		writeSheet.setNeedHead(true);
		ExcelWriter write = EasyExcel.write(os).registerConverter(new LocalDateTimeConverter()).build();
		write.write(data, writeSheet);
		write.finish();
	}

	/**
	 * 简洁方法写入list对象，在类的字段注解@ExcelProperty，自定义单行表头
	 * @author: fuh
	 * @createTime: 2019/10/25 13:59
	 * @param os
	 * @param data
	 * @param clz
	 * @param simpleHead
	 * @return void
	 */
	public static <T> void writeListTo(OutputStream os,List<T> data,Class<T> clz,List<String> simpleHead){
		WriteSheet writeSheet = new WriteSheet();
		writeSheet.setClazz(clz);
		writeSheet.setNeedHead(true);

		List<List<String>> head=simpleHead.stream().map(a->Arrays.asList(a)).collect(Collectors.toList());
		writeSheet.setHead(head);
		ExcelWriter write = EasyExcel.write(os).registerConverter(new LocalDateTimeConverter()).build();
		write.write(data, writeSheet);
		write.finish();
	}

}
