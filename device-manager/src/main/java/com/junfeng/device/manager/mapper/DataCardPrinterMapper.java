package com.junfeng.device.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.device.manager.entity.DataCardPrinter;
import org.apache.ibatis.annotations.Param;

/**
 * 制卡机状态信息
 *
 * @author hanyx
 * @date 2019-10-24 15:11:19
 */
public interface DataCardPrinterMapper {
  /**
    * 制卡机状态信息简单分页查询
    * @param dataCardPrinter 制卡机状态信息
    * @return
    */
  IPage<DataCardPrinter> getDataCardPrinterPage(Page page, @Param("dataCardPrinter") DataCardPrinter dataCardPrinter);


}
