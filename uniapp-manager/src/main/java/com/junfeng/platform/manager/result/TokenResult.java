package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 返回的token结果
 *
 * @author daiysh
 * @date 2019-10-16 13:05
 **/
@Data
public class TokenResult {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String user_id;
	private String username;
}
