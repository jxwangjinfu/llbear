package com.junfeng.platform.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fuh
 * @version 1.0 2019/10/29 16:33
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

	@Override
	public Class supportJavaTypeKey() {
		return LocalDateTime.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
								  GlobalConfiguration globalConfiguration) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return LocalDateTime.parse(cellData.getStringValue(),dateTimeFormatter);
	}

	@Override
	public CellData convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty,
									   GlobalConfiguration globalConfiguration) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return new CellData(dateTimeFormatter.format(value));
	}
}
