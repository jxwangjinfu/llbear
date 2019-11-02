package com.junfeng.platform.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author fuh
 * @version 1.0 2019/10/23 16:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @ExcelProperty
    private String isbn;

    @ExcelProperty
    private String title;

    @ExcelProperty
	private LocalDateTime publishTime;

}
