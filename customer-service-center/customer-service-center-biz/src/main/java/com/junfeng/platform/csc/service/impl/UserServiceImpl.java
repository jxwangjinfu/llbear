package com.junfeng.platform.csc.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.User;
import com.junfeng.platform.csc.mapper.UserMapper;
import com.junfeng.platform.csc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 客服人员信息
 *
 * @author lw
 * @date 2019-10-12 11:21:31
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	/**
	 * 客服人员信息简单分页查询
	 *
	 * @param user 客服人员信息
	 * @return
	 */
	@Override
	public IPage<User> getUserPage(Page<User> page, User user) {
		return baseMapper.getUserPage(page, user);
	}

	@Override
	public User getUserByUserCode(Integer userCode) {
		return new LambdaQueryChainWrapper<User>(baseMapper).eq(User::getUserCode, userCode).one();
	}

	public boolean saveWithCheck(User user){
		checkUser(user);
		//前端取来，无需验证
		return save(user);
	}

	private void checkUser (User user) {
		User localUser =new LambdaQueryChainWrapper<User>(baseMapper).eq(User::getUserCode, user.getUserCode()).one();
		Assert.isNull(localUser,"该用户已存在客服信息");
	}
}
