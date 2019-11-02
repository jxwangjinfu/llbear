/**
 * 
 */
package com;

import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.entity.OutQuart;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月25日 下午1:16:35
 * @version 1.0
 */
public class TestMain1 {

    public static void main(String[] args) {

        OutQuartVO outQuartVO = new OutQuartVO();
        outQuartVO.setCallbackUrl("callbackUrl");
        outQuartVO.setJobName("jobName");
        
        OutQuart outQuart = new OutQuart();

        BeanUtil.copyProperties(outQuartVO, outQuart, CopyOptions.create().setIgnoreNullValue(true));

        System.out.println(outQuart);
        
    }

}
