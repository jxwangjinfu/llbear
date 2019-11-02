package com.junfeng.platform.dc.api.result;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 分页查询设备结果
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/31 8:58
 * @projectName pig
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DevicePageResult extends BasePageResult<DeviceResult> {

}
