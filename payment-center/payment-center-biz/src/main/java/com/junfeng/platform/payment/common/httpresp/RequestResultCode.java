package com.junfeng.platform.payment.common.httpresp;

public enum RequestResultCode {
    SUCCESS(0,"成功"),
    ERROR(1,"错误"),
    COMET_TIMEOUT(2,"comet_timeout"),
    MEMBER_COMET_TIMEOUT(2,"网络异常,请重试"),
    TIMEOUT(3, "timeout"),
    NETWORK_ERROR(4, "网络异常,请稍候再试"),
    SYSTEM_ERROR(5, "服务异常,请联系客服!"),
    ALLOW_AMOUNT_ERROR(6, "单笔支付金额超过限制!"),
    UPLOAD_FILE_READ_ERROR(7, "上传文件读取失败"),
    UPLOAD_FILE_READ_FORMAT_ERROR(8, "上传文件格式错误"),
    GET_NULL(9,"没有找到数据"),
    SIGN_ERROR(10,"签名错误"),



    TAKEOUT_ERROR(100,"调用外卖平台接口返回错误"),

    // exception define
    UNKNOWN_EXCEPTION(550,"未定义异常"),
    ARRAYINDEXOUTOFBOUNDS_EXCEPTION(551,"数组越界异常"),
    SQL_EXCEPTION(552,"SQL执行异常"),
    IO_EXCEPTION(553,"IO异常"),
    NULLPOINTER_EXCEPTION(554,"空指针异常"),
    ILLEGALSTATE_EXCEPTION(555,"客户端写入异常"),



    PARAM_ERROR(10001,"参数异常"),
    DELETE_NOT_FOUND(10002,"删除记录不存在"),
    GET_NOT_FOUND(10003,"查询记录不存在"),
    ILLEGAL_OPERATION(10004,"非法操作"),
    NAME_EXISTS(10005,"名称已存在"),
    CODE_EXISTS(10006,"code已存在"),
    ID_NULL(10007,"ID为空"),
    PUSH_DEVICE_WAKEUP_ERROR(10008,"同步失败，请检查网络是否连接 "),
    POS_NOT_INIT_IN_PC(10009,"收银台还未初始化"),
    UPDATE_NOT_FOUND(10010,"该记录已被删除"),
    SELECT_BEGIN_END_TIME_ERROR(10011,"结束时间必须大于开始时间"),
    SYSTEM_BUSY(10012,"系统繁忙,请稍后再试!"),


    PAY_ORDER_CREATION_FAILED(100001,"订单创建失败！"),
    PAY_ORDER_PAY_BARCODE_FAILED(100002,"条码支付失败！"),
    PAY_ORDER_PAY_BARCODE_NEED_PASSWORD(100003,"需要输入密码！"),
    PAY_ORDER_PAY_MCH_ORDERNO_IS_USED(100004,"此业务系统支付单号已被使用！"),
    PAY_ORDER_MINI_PAY_FAILED(100005,"下单失败!"),

    PAY_MCH_CHANNEL_RELATION_DISABLE(110001,"商户未启用支付通道！"),

    PAY_MCH_NO_EXIST(110002,"商户未申请开通支付"),

    PAY_MCH_NO_WORK(110003,"该商户未设置支付通道!"),

    PAY_MCH_MODE_CODE_INVALID(110004,"商户没有开通此支付功能"),

    PAY_MCH_QUERY_ORDER_NUM_NO_EXIST(110005,"所查询的支付信息不存在！"),

    PAY_MCH_OPEN_ALREADY(110006,"商户已申请开通"),

    PAY_MCH_NOT_OPEN(110007,"商户未开通支付"),

    PAY_CHANNEL_FORBID_DELETE_FOR_USED(110008,"支付通道已被使用，禁止删除"),

    PAY_MCH_CHANNEL_RELATION_CHANGE(110009,"商户当前启用的支付通道不是付款时使用的支付通道！"),

    PAY_MCH_MINI_ACCOUNT_NO_EXIST(110010,"商户没有开通小程序账号"),

    PAY_CHANNEL_UNSUPPORT_THIS_PAY_MODE(110011,"此支付通道不支持云闪付和龙支付扫码支付!"),

    PAY_MCH_REFUND_ORDER_NUM_NO_PAY(120001,"该订单未支付！"),

    PAY_MCH_REFUND_ORDER_NUM_IS_REFUND(120002,"该订单已退款！"),
    PAY_MCH_REFUND_ORDER_NUM_IS_OUT_OF_DATE(120003,"只有支付成功当天的订单可以退款！"),
    PAY_MCH_QUERY_REFUND_ORDER_NUM_NO_EXIST(120004,"所查询的退款信息不存在！"),
    PAY_MCH_REFUND_ORDER_FAILED(120005,"退款失败！"),
    BANK_RETURN_ERROR(120006,"第三方接口返回结果错误！"),
    ;

    private Integer errorCode;
    private String errorMessage;

    private RequestResultCode(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

