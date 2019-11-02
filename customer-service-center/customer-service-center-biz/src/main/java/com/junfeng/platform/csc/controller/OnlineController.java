package com.junfeng.platform.csc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.junfeng.platform.csc.entity.*;
import com.junfeng.platform.csc.service.OnlineRecordService;
import com.junfeng.platform.csc.service.UserService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@MessageMapping("/talk")
public class OnlineController {

	private static HashMap<Long, OnLineServiceUser> customserviceuserList = new HashMap<Long, OnLineServiceUser>();
	private static BlockingQueue<OnLineUser> onLineUsers = new LinkedBlockingQueue<>();
	private final SimpMessagingTemplate messagingTemplate;
	private final OnlineRecordService onlineRecordService;
	private final UserService userService;
	private final String msgPath = "/msg";
	private final String userPath = "/user";

	/**
	 * 功能描述: 客服人员上线
	 * @author: lw
	 * @createTime: 2019/10/9 17:11
	 * @param principal
	 * @param userid
	 * @return com.pig4cloud.pig.common.core.util.R<?>
	 */
	@MessageMapping("/customerserviceuser/{userid}/connect/")
/*	@PreAuthorize("@cscpms.hasOnlinePermission(#userid)")*/
	public R connectWithCustomerServiceUser(Principal principal,@DestinationVariable Long userid) {
		OnLineServiceUser onLineServiceUser;
		if (customserviceuserList.containsKey(userid)) {
			onLineServiceUser = customserviceuserList.get(userid);
			onLineServiceUser.setPrincipal(principal);
			customserviceuserList.put(userid, onLineServiceUser);
			return R.ok();
		}

		User user = userService.getUserByUserCode(userid.intValue());
		if (user == null ){
			return R.failed("无效账户");
		}

		JSONArray jsonArray = JSON.parseArray(user.getRoles());
		if (jsonArray.contains(RoleType.Online.getRoleId())==false) {
			return R.failed("权限不足");
		}

		onLineServiceUser = new OnLineServiceUser();
		onLineServiceUser.setPrincipal(principal);
		onLineServiceUser.setServiceUser(user);
		onLineServiceUser.setOnLineUsers(new ArrayList<OnLineUser>());

		customserviceuserList.put(userid, onLineServiceUser);
		return R.ok();
	}

	/**
	 * 客服人员获取客户
	 *
	 * @param principal
	 * @param userid
	 * @return
	 */
	@MessageMapping("/customerserviceuser/{userid}/getuser/")
	/*@PreAuthorize("@cscpms.hasOnlinePermission(#userid)")*/
	public R<OnLineUser> getUser(Principal principal, @DestinationVariable Long userid) {
		if (!customserviceuserList.containsKey(userid)) {
			return R.failed("登录失效");
		}
		OnLineUser onLineUser = onLineUsers.poll();

		if (onLineUser == null) {
			return R.failed("无等待用户");
		}

		OnLineServiceUser onLineServiceUser = customserviceuserList.get(userid);
		onLineServiceUser.getOnLineUsers().add(onLineUser);
		messagingTemplate.convertAndSendToUser(onLineUser.getPrincipal().getName(), userPath, userid);
		messagingTemplate.convertAndSendToUser(onLineUser.getPrincipal().getName(), msgPath, "您好！编号" + userid + "为您服务。");
		messagingTemplate.convertAndSendToUser(principal.getName(),userPath,onLineUser.getMemberId());
		//增加记录
		OnlineRecord onlineRecord = new OnlineRecord();
		onlineRecord.setMemberCode(onLineUser.getMemberId());
		onlineRecord.setUserCode(userid);
		onlineRecord.setOnlineType(0);
		onlineRecordService.save(onlineRecord);

		return R.ok(onLineUser);
	}

	/**
	 * 客服人员针对指定用户发送信息
	 *
	 * @param principal
	 * @param userid
	 * @param hashMap
	 * @return
	 */
	@MessageMapping("/customerserviceuser/{userid}/send/")
/*	@PreAuthorize("@cscpms.hasOnlinePermission(#userid)")*/
	public R<?> sendUser(Principal principal, @DestinationVariable Long userid, @RequestBody HashMap<String, String> hashMap) {
		if (!customserviceuserList.containsKey(userid)) {
			return R.failed("登录失效");
		}
		OnLineServiceUser onLineServiceUser = customserviceuserList.get(userid);
		Long memberid = Long.parseLong(hashMap.get("memberid"));
		String sendMessage = hashMap.get("msg");
		OnLineUser onLineUser = onLineServiceUser.getOnLineUsers().stream()
			.filter(a -> memberid.equals(a.getMemberId())).collect(Collectors.toList()).get(0);

		if (onLineUser == null) {
			return R.failed("无等待用户");
		}

		messagingTemplate.convertAndSendToUser(onLineUser.getPrincipal().getName(), msgPath, sendMessage);
		return R.ok();
	}

	/**
	 * 功能描述: 客服人员断开
	 * @author: lw
	 * @createTime: 2019/10/9 17:44
	 * @param userid
	 * @return void
	 */
	@MessageMapping("/customerserviceuser/{userid}/disconnect/")
	public void disconnectWithCustomerServiceUser(@DestinationVariable Long userid){
		OnLineServiceUser onLineServiceUser = customserviceuserList.get(userid);
		if(onLineServiceUser == null){
			return;
		}
		//向每个用户发送断开信息
		onLineServiceUser.getOnLineUsers().forEach(onLineUser -> {
			messagingTemplate.convertAndSendToUser(onLineUser.getPrincipal().getName(),msgPath,"已断开连接!");
		});
		//移除客服管理
		customserviceuserList.remove(userid);
	}
	/**
	 * 用户登录在线人工服务
	 *
	 * @param principal
	 * @param memberid
	 * @return
	 */
	@MessageMapping("/user/{memberid}/connect/")
	public R<?> connectWithUser(Principal principal, @DestinationVariable Long memberid) {
		OnLineUser onLineUser = new OnLineUser();
		onLineUser.setPrincipal(principal);
		onLineUser.setMemberId(memberid);
		onLineUsers.offer(onLineUser);
		messagingTemplate.convertAndSendToUser(principal.getName(), msgPath, "正在安排客服，请等待!");
		return R.ok();
	}

	/**
	 * 用户发送信息给处理自己诉求的在线人工客服
	 *
	 * @param principal
	 * @param memberid
	 * @param hashMap
	 * @return
	 */
	@MessageMapping("/user/{memberid}/send/")
	public R<?> send(Principal principal, @DestinationVariable Long memberid, @RequestBody HashMap<String, String> hashMap) {
		Long userid = Long.parseLong(hashMap.get("userid"));
		if (!customserviceuserList.containsKey(userid)) {
			return R.failed("断开连接，请重新申请!");
		}
		OnLineServiceUser onLineServiceUser = customserviceuserList.get(userid);
		OnLineUser onLineUser = onLineServiceUser.getOnLineUsers().stream()
			.filter(a -> memberid.equals(a.getMemberId())).collect(Collectors.toList()).get(0);
		if (onLineUser == null) {
			return R.failed("断开连接，请重新申请!");
		}
		String sendMessage = hashMap.get("msg");
		messagingTemplate.convertAndSendToUser(onLineServiceUser.getPrincipal().getName(), "/"+memberid+"/"+msgPath, sendMessage);
		return R.ok();
	}

	@MessageMapping("/user/{memberid}/disconnect/")
	public void disconnectWithUser(@DestinationVariable Long memberid,@RequestBody HashMap<String, String> hashMap)
	{
		Long userid = Long.parseLong(hashMap.get("userid"));
		if (!customserviceuserList.containsKey(userid)) {
			return;
		}
		OnLineServiceUser onLineServiceUser = customserviceuserList.get(userid);
		if(onLineServiceUser == null){
			return;
		}
		Iterator<OnLineUser> iteratorOnLineUser = onLineServiceUser.getOnLineUsers().iterator();
		while(iteratorOnLineUser.hasNext()){
			if(memberid.equals(iteratorOnLineUser.next().getMemberId())){
				iteratorOnLineUser.remove();
			}
		}
		messagingTemplate.convertAndSendToUser(onLineServiceUser.getPrincipal().getName(),"/"+memberid+"/"+msgPath,"用户已断开连接!");
	}
}
