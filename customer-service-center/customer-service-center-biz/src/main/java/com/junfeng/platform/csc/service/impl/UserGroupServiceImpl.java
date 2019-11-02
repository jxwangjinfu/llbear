package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.UserGroup;
import com.junfeng.platform.csc.mapper.UserGroupMapper;
import com.junfeng.platform.csc.service.UserGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 客服组信息
 *
 * @author lw
 * @date 2019-10-12 11:44:30
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {

	/**
	 * 客服组信息简单分页查询
	 *
	 * @param userGroup 客服组信息
	 * @return
	 */
	@Override
	public IPage<UserGroup> getUserGroupPage(Page<UserGroup> page, UserGroup userGroup) {
		return baseMapper.getUserGroupPage(page, userGroup);
	}

	@Override
	public boolean saveWithCheck(UserGroup entity) {
		checkUserGroup(entity);
		return super.save(entity);
	}

	private void checkUserGroup(UserGroup entity){
		UserGroup userGroup = this.getOne(new QueryWrapper<UserGroup>().lambda().eq(UserGroup::getGroupName
			, entity.getGroupName()));
		Assert.isNull(userGroup,"组名称已存在!");
	}
}
