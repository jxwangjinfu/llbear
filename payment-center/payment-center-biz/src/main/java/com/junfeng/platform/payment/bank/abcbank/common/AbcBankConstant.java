package com.junfeng.platform.payment.bank.abcbank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

/**
 * 农业银行常量
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月7日 上午11:02:19
 * @version 1.0
 */
public class AbcBankConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);


	/**
     * 农业银行请求接口
     */
    public static final String ABC_BANK_URL = paymentPropertites.getAbc_bank_host();
//		HaoposProperties.getInstance().getValue("abc_bank.host",
//            "http://202.108.144.34:9000/upay/UPTP.ebf");

    /**
     * iso8583xml文件路径
     */
    public static final String XML_FILE_PATH = "com/junhe/payment/bank/abcbank/utils/iso8583/resource/";

    /**
     * 省市代码
     */
    public static final String PROVINCE_CODE = "14";

    /**
     * 目标系统接入方式
     */
    public static final String ACCESS_TYPE = "application/json";

    // public static final String ACCESS_TYPE = "application/x-www-form-urlencoded";

    /**
     *
     */
    public static final String PACKET_TYPE = "base64";

    /**
     * 操作系统
     */
    public static final String OS = "android";

    /**
     *
     */
    public static final String APP_VERSION = "1.0.4";

    /**
     * 平台注册时系统分配的appId
     */
    public static final String APP_ID = paymentPropertites.getAbc_bank_appid();
//		HaoposProperties.getInstance().getValue("abc_bank.appid", "1001");

    public static final String HTTP_WEB_PATH = "abcah/ycbmp/vineSpeaker.action";

    /**
     * 银行的公钥
     */
    public static final String bankPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgYbfWxWs4vIbRWEyGZtHMOqBX\r\n"
            + "YHNX5zIn72m72tFRc5s3go6ev3apYHh5XjciBo/GMF/kf6NaCD8o1OkBeHw/C7ZE\r\n"
            + "kQXWp4tusIBsD626KCQOp//UUxovrXiBEF1Rbgmq5H0VDap1pzL0bFUrpAliHGDl\r\n" + "IW8+8l3Vgt48gejQIQIDAQAB";

}
