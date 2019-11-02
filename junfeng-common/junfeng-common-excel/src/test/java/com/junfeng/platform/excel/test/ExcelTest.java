package com.junfeng.platform.excel.test;

import com.junfeng.platform.excel.ExcelUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author fuh
 * @version 1.0 2019/10/25 10:38
 */
public class ExcelTest {

    @Test
    public void testExcel() throws FileNotFoundException {
    	//定义列表
		List<Book> books = Arrays.asList(new Book("isbn-1", "SpringMVC", LocalDateTime.now()), new Book("isbn-2", "Mybatis",LocalDateTime.now()));

		//写入excel
		ExcelUtil.writeListTo(new FileOutputStream("E:/testExcels/books_write.xlsx"),books,Book.class,Arrays.asList("isbn号","书名","出版时间"));

		//读取excel
		List<Book> readBooks = ExcelUtil.readListFrom(new FileInputStream("E:/testExcels/books_write.xlsx"), Book.class);

		System.out.println(readBooks);
	}



}
