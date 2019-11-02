package com.junfeng.platform.payment.bank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 2fx0one
 * 2019-09-26 15:08
 **/
@Component
@ConfigurationProperties(prefix = "payment")
@Data
@RefreshScope
public class PaymentPropertites {

	private String payment_center_key;
	private String jx_bank_host;
	private String citic_bank_host;
	private String cm_bank_host;
	private String abc_bank_host;
	private String abc_bank_appid;

	private String union_pay_host;
	private String union_pay_appid;
	private String union_pay_appkey;

	private String union_pay_mini_host;
	private String union_pay_mini_appid;
	private String union_pay_mini_appkey;
	private String nion_pay_mini_instmid;
	private String union_pay_mini_key;

	private String post_bank_host;

	private String china_bank_host;
	private String china_bank_key;

	private String construction_bank_branchid;
	private String construction_bank_url;
	private String construction_bank_socket_ip;
	private String construction_bank_socket_port;
	private String construction_bank_refund_big_mchid;
	private String construction_bank_refund_big_mchopera;
	private String construction_bank_refund_mchopera_trade_password;
}
