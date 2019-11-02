package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceDeployInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 设备部署信息
 *
 * @author hanyx
 * @date 2019-10-25 11:07:42
 */
public interface DeviceDeployInfoMapper extends BaseMapper<DeviceDeployInfo> {
  /**
    * 设备部署信息简单分页查询
    * @param deviceDeployInfo 设备部署信息
    * @return
    */
  IPage<DeviceDeployInfo> getDeviceDeployInfoPage(Page page, @Param("deviceDeployInfo") DeviceDeployInfo deviceDeployInfo);


}
