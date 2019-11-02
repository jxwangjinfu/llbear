package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.Phone;

/**
 * 话机信息表
 *
 * @author lw
 * @date 2019-09-29 10:37:52
 */
public interface PhoneService extends IService<Phone> {

	/**
	 * 话机信息表简单分页查询
	 *
	 * @param phone 话机信息表
	 * @return
	 */
	IPage<Phone> getPhonePage(Page<Phone> page, Phone phone);
	/**
	 * 功能描述:
	 * @author: 验证并保存
	 * @createTime: 2019/10/31 9:39
	 * @param entity
	 * @return boolean
	 */
	public boolean saveWithCheck(Phone entity);
	/**
	 * 功能描述: 验证并更新
	 * @author: lw
	 * @createTime: 2019/10/31 9:40
	 * @param entity
	 * @return boolean
	 */
	public boolean updateByIdWithCheck(Phone entity);
	/**
	 * 功能描述: 根据id使话机占线
	 *
	 * @param id
	 * @return boolean
	 * @author: lw
	 * @createTime: 2019/10/8 9:25
	 */
	boolean onBusy(Long id);

	/**
	 * 功能描述: 根据id释放话机占线
	 * @author: lw
	 * @createTime: 2019/10/8 9:48
	 * @param id
	 * @return boolean
	 */
	boolean onFree(Long id);

	/**
	 * 功能描述: 上线
	 * @author: lw
	 * @createTime: 2019/10/8 14:06
	 * @param id
	 * @param userid
	 * @return boolean
	 */
	boolean onLine(Long id,Long userid);
	/**
	 * 功能描述: 下线
	 * @author: lw
	 * @createTime: 2019/10/8 14:06
	 * @param id
	 * @param userid
	 * @return boolean
	 */
	boolean offLine(Long id,Long userid);
}
