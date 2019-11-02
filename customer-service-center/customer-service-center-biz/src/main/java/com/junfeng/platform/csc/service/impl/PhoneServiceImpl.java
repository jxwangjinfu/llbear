package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.Phone;
import com.junfeng.platform.csc.entity.PhoneRecord;
import com.junfeng.platform.csc.entity.PhoneState;
import com.junfeng.platform.csc.entity.User;
import com.junfeng.platform.csc.service.PhoneRecordService;
import com.junfeng.platform.csc.service.PhoneService;
import com.junfeng.platform.csc.mapper.PhoneMapper;
import com.junfeng.platform.csc.service.PhoneStateService;
import com.junfeng.platform.csc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 话机信息表
 *
 * @author lw
 * @date 2019-09-29 10:37:52
 */
@AllArgsConstructor
@Service("phoneService")
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {

	private final PhoneStateService phoneStateService;

	private final PhoneRecordService phoneRecordService;

	private final UserService userService;
	/**
	 * 话机信息表简单分页查询
	 *
	 * @param phone 话机信息表
	 * @return
	 */
	@Override
	public IPage<Phone> getPhonePage(Page<Phone> page, Phone phone) {
		return baseMapper.getPhonePage(page, phone);
	}

	/**
	 * 功能描述: 占用话机
	 * @author: lw
	 * @createTime: 2019/10/8 13:57
	 * @param id
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean onBusy(Long id) {
		return phoneStateChange(id,1);
	}

	/**
	 * 功能描述: 释放话机
	 * @author: lw
	 * @createTime: 2019/10/8 13:57
	 * @param id
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean onFree(Long id) {
		return phoneStateChange(id, 0);
	}

	/**
	 * 功能描述: 在线
	 * @author: lw
	 * @createTime: 2019/10/8 15:34
	 * @param id
	 * @param userid
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean onLine(Long id, Long userid) {
		return phoneOnlineChange(id, userid, "1");
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveWithCheck(Phone entity) {
		checkPhone(entity);
		Assert.isTrue(super.save(entity),"话机保存失败!");
		PhoneState phoneState = new PhoneState();
		phoneState.setPhoneCode(entity.getId());
		phoneState.setState(0);
		return phoneStateService.save(phoneState);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateByIdWithCheck(Phone entity) {
		checkPhone(entity);
		return super.updateById(entity);
	}

	/**
	 * 功能描述: 下线
	 * @author: lw
	 * @createTime: 2019/10/8 15:34
	 * @param id
	 * @param userid
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean offLine(Long id, Long userid) {
		return phoneOnlineChange(id, userid, "0");
	}

	/**
	 * 功能描述: 话机状态变更
	 * @author: lw
	 * @createTime: 2019/10/8 9:59
	 * @param id
	 * @param intPhoneState 0 释放话机 1 占用话机
	 * @return boolean
	 */
	private boolean phoneStateChange(Long id, int intPhoneState) {
		Phone phone = baseMapper.selectById(id);
		Assert.notNull(phone,"话机不存在");
		Assert.isTrue("1".equals(phone.getOnlineFlag()),"话机未上线");
		//获取话机状态
		PhoneState phoneState = phoneStateService.getById(id);
		// 话机当前状态与改变状态一致 进行二次校验 释放话机返回成功，占用话机返回失败
		if(phoneState.getState() == intPhoneState){
			return intPhoneState==0;
		}

		//话机状态变更
		phoneState.setState(intPhoneState);
		Assert.isTrue(phoneStateService.updateById(phoneState),"话机状态变更失败");

		//Todo 新增一条话机通话记录移出去统一处理
		PhoneRecord phoneRecord = new PhoneRecord();
		phoneRecord.setPhoneCode(id);
		phoneRecord.setPhoneType(1-intPhoneState);
		phoneRecord.setUserCode(phone.getWorkCode());
		phoneRecordService.save(phoneRecord);

		return true;
	}
	/**
	 * 功能描述: 话机在线状态变更
	 * @author: lw
	 * @createTime: 2019/10/8 15:33
	 * @param id
	 * @param userid
	 * @param charPhoneOnLineFlag
	 * @return boolean
	 */
	private boolean phoneOnlineChange(Long id, Long userid, String charPhoneOnLineFlag) {
		Phone phone = baseMapper.selectById(id);
		Assert.notNull(phone,"话机不存在");
		Assert.isTrue(userid == phone.getWorkCode(),"话机与业务员不对应");
		phone.setOnlineFlag(charPhoneOnLineFlag);
		return super.updateById(phone);
	}
	/**
	 * 功能描述: 验证话机合理性
	 * @author: lw
	 * @createTime: 2019/10/30 10:33
	 * @param entity
	 * @return void
	 */
	private void checkPhone(Phone entity) {
		User user = userService.getById(entity.getWorkCode());
		Assert.notNull(user, "客服人员编号错误");
	}
}
