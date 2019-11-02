package com.junfeng.platform.csc.entity;

import java.security.Principal;

import lombok.Data;

@Data
public class OnLineUser {
	Long memberId;
	Principal principal;
}
