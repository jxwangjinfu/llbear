package com.junfeng.platform.payment.controller.pay.vo;

import javax.validation.constraints.NotNull;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;

import lombok.Data;

/**
 * 条码支付参数
 *
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月16日 下午4:58:07
 * @version 1.0
 */
@Data
public class BarcodePayVo {

    @NotNull(message = "支付中心商户号不能为空", groups = {AddVo.class, UpdateVo.class})
    private Long payMchId; // 支付中心商户号

    @NotNull(message = "支付条码不能为空", groups = {AddVo.class, UpdateVo.class})
    private String payBarcode; // 支付条码（临时使用，不入库）<br>扫码支付授权码<br>设备读取用户微信或支付宝中的条码或者二维码信息

    @NotNull(message = "业务系统支付号 不能为空", groups = {AddVo.class, UpdateVo.class})
    private String mchOrderNo; // 业务系统支付号

    @NotNull(message = "支付方式编码不能为空", groups = {AddVo.class, UpdateVo.class})
    private String paymentModeCode; // 支付方式编码<br>微信条码 `WX_BARCODE`<br>支付宝条码 `ALIPAY_BARCODE`

    @NotNull(message = "支付金额不能为空", groups = {AddVo.class, UpdateVo.class})
    private Long amount; // 支付金额, 单位:分

    @NotNull(message = "客户端IP不能为空", groups = {AddVo.class, UpdateVo.class})
    private String clientIp; // 客户端IP

    @NotNull(message = "商品描述不能为空", groups = {AddVo.class, UpdateVo.class})
    private String body; // 商品描述信息

    @NotNull(message = "通知URL不能为空", groups = {AddVo.class, UpdateVo.class})
    private String notifyUrl; // 支付成功后的通知URL

    private String remark;
}
