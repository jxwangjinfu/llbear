package com.junfeng.platform.csc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.controller.model.OrderRecordVo;
import com.junfeng.platform.csc.controller.model.WorkOrderVo;
import com.junfeng.platform.csc.entity.User;
import com.junfeng.platform.csc.entity.UserGroup;
import com.junfeng.platform.csc.entity.WorkOrder;
import com.junfeng.platform.csc.entity.WorkOrderRecord;
import com.junfeng.platform.csc.mapper.WorkOrderMapper;
import com.junfeng.platform.csc.service.UserGroupService;
import com.junfeng.platform.csc.service.UserService;
import com.junfeng.platform.csc.service.WorkOrderRecordService;
import com.junfeng.platform.csc.service.WorkOrderService;
import com.junfeng.platform.csc.util.ContextHolderUtil;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 工单信息
 *
 * @author lw
 * @date 2019-10-12 13:57:38
 */
@Service("workOrderService")
@AllArgsConstructor
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements WorkOrderService {
	private final UserGroupService userGroupService;
	private final WorkOrderRecordService workOrderRecordService;
	private final UserService userService;
  /**
   * 工单信息简单分页查询
   * @param workOrder 工单信息
   * @return
   */
  @Override
  public IPage<WorkOrder> getWorkOrderPage(Page<WorkOrder> page, WorkOrder workOrder){
      return baseMapper.getWorkOrderPage(page,workOrder);
  }

	/**
	 * 功能描述: 获取工单
	 * @author: lw
	 * @createTime: 2019/10/9 14:18
	 * @return com.junfeng.platform.csc.entity.WorkOrder
	 */
	@Override
	public WorkOrder getFirstWorkOrderbyGroupId() {
		User user = userService.getUserByUserCode(SecurityUtils.getUser().getId());
		Assert.notNull(user,"客服人员信息不存在!");
		JSONArray jsonArray = JSON.parseArray(user.getRoles());
	/*	if (jsonArray.contains(RoleType.WorkOrder.getRoleId())==false) {
			return null;
		}*/
		WorkOrderRecord workOrderRecord;
		synchronized (this) {
			//获取最早的一条未分配工单记录
			QueryWrapper<WorkOrderRecord> queryWrapper = new QueryWrapper<WorkOrderRecord>();
			queryWrapper.lambda().eq(WorkOrderRecord::getGroupCode, user.getGroupCode())
				.eq(WorkOrderRecord::getState, 0)
				.orderByDesc(WorkOrderRecord::getCreateTime)
				.last("limit 1");
			workOrderRecord = workOrderRecordService.getOne(queryWrapper);
			if (workOrderRecord != null) {
				//对它进行分配
				workOrderRecord.setState(1);
				workOrderRecord.setUserCode(user.getId());
				workOrderRecord.setUserName(user.getUserName());
				workOrderRecord.setUpdateBy(user.getUserName());
				//设置
				workOrderRecordService.updateById(workOrderRecord);
			}
		}
		if (workOrderRecord == null) {
			return null;
		} else {
			return getById(workOrderRecord.getWorkOrderCode());
		}
	}
	/**
	 * 功能描述: 工单完结
	 * @author: lw
	 * @createTime: 2019/10/9 14:39
	 * @param orderRecordVo
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean orderRecordOver(OrderRecordVo orderRecordVo) {
		orderRecordVo.setNextGroupId(0L);
		return orderRecordNext(orderRecordVo,null);
	}

	/**
	 * 功能描述: 工单流转
	 * @author: lw
	 * @createTime: 2019/10/9 15:12
	 * @param orderRecordVo
	 * @param workOrderRecord
	 * @return boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean orderRecordTurn(OrderRecordVo orderRecordVo) {
		UserGroup userGroup = checkUserGroup(orderRecordVo.getNextGroupId());
		return orderRecordNext(orderRecordVo,userGroup);
	}

	@Override
	@Transactional(rollbackFor =  Exception.class)
	public boolean orderCreate(WorkOrderVo workOrderVo) {
		UserGroup userGroup = checkUserGroup(workOrderVo.getGroupCode());
		WorkOrder workOrder = new WorkOrder();
		workOrder.setContent(workOrderVo.getContent());
		workOrder.setCreateBy(ContextHolderUtil.getUsername());
		workOrder.setTitle(workOrderVo.getTitle());
		Assert.isTrue(save(workOrder), "工单保存失败");

		WorkOrderRecord workOrderRecord = new WorkOrderRecord();
		workOrderRecord.setGroupCode(workOrderVo.getGroupCode());
		workOrderRecord.setGroupName(userGroup.getGroupName());
		workOrderRecord.setUpdateBy(ContextHolderUtil.getUsername());
		workOrderRecord.setOrderSort(1);
		workOrderRecord.setWorkOrderCode(workOrder.getId());

		Assert.isTrue(workOrderRecordService.save(workOrderRecord),"工单记录生成失败");
		return true;
	}

	private UserGroup checkUserGroup(Long groupId) {
		UserGroup userGroup = userGroupService.getById(groupId);
		Assert.notNull(userGroup,"客服组信息不存在!");
		return userGroup;
	}

	private WorkOrderRecord checkWorkOrderRecord(OrderRecordVo orderRecordVo){
		WorkOrderRecord workOrderRecord= workOrderRecordService.getById(orderRecordVo.getOrderRecordId());
		Assert.notNull(workOrderRecord,"工单记录不存在");
		return workOrderRecord;
	}
	private boolean orderRecordNext(OrderRecordVo orderRecordVo,UserGroup userGroup){
		WorkOrderRecord workOrderRecord = checkWorkOrderRecord(orderRecordVo);
		String UserName = ContextHolderUtil.getUsername();
		workOrderRecord.setState(2);
		workOrderRecord.setUpdateBy(UserName);
		workOrderRecord.setReasonTypeCode(orderRecordVo.getReasonTypeCode());
		workOrderRecord.setOrderDetail(orderRecordVo.getOrderDetail());
		Assert.isTrue(workOrderRecordService.updateById(workOrderRecord),"工单完成失败!");
		boolean isOk;
		if(userGroup==null){
			isOk =  new LambdaUpdateChainWrapper<WorkOrder>(baseMapper)
				.eq(WorkOrder::getId,workOrderRecord.getWorkOrderCode())
				.set(WorkOrder::getUpdateBy,UserName)
				.set(WorkOrder::getState,2)
				.update();
		}else{
			WorkOrderRecord newWorkOrderRecord = new WorkOrderRecord();
			newWorkOrderRecord.setWorkOrderCode(workOrderRecord.getWorkOrderCode());
			newWorkOrderRecord.setOrderSort((workOrderRecord.getOrderSort()+1));
			newWorkOrderRecord.setCreateBy(UserName);
			newWorkOrderRecord.setGroupCode(userGroup.getId());
			newWorkOrderRecord.setGroupName(userGroup.getGroupName());
			isOk = workOrderRecordService.save(newWorkOrderRecord);
		}
		Assert.isTrue(isOk,"工单完成失败!");

		return isOk;
	}
}
