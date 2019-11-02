package com.junfeng.platform.csc.entity;

import java.security.Principal;
import java.util.List;


import lombok.Data;

@Data
public class OnLineServiceUser {
	private User serviceUser;
	private Principal principal;
	private List<OnLineUser> onLineUsers;
}
