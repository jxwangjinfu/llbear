package com.junfeng.platform.tc.api.order.state;

import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

/**
 * 所有的状态转换都可以概括为：
 * F(S, E) -> (A, S')，即如果当前状态为S，接收到一个事件E，则执行动作A，同时状态转换为S‘。
 *
 * +-------------+
 * |             |
 * |状态1： 待付款 |--------------------------------------------------+(事件6 取消订单)
 * |             |                                                  |
 * +------+------+                                                  |
 *        |                                                         |
 *        |                                                         |
 * (事件1：用户支付）                                                 |
 *        |                                                         |
 *        |                                                         |
 * +------v------+                                                  |
 * |             |                                                  |
 * |状态2： 已付款 +----+                                             |
 * |  （待发货)   |    |                                             |
 * +------+------+    |                                             |
 *        |           |                                             |
 *   (事件2：商家发货)  |                                             |
 *        |           +----------------+ (事件4 用户退款）            |
 *        |           |                |                            |
 *        |           |                |                            |
 * +------v------+    |         +------v------+                     |
 * |             |    |         |             |                     |
 * |状态3： 已发货 +----+         |状态5. 退款中 |                     |
 * |             |              |             |                     |
 * +------+------+              +------+------+                     |
 *        |                            |                            |
 *        |                            |                            |
 *   (事件3：用户确认收货)                |（事件5：商户确认退款）        |
 *        |                            |                            |
 *        |                            |                            |
 * +------v------+              +------v------+                     |
 * |             |              |             |                     |
 * |状态4. 订单完成|              |状态6. 订单关闭|<-------------------+
 * |             |              |             |
 * +-------------+              +-------------+
 *
 *
 * 订单中的基本状态包括6种：
 * 状态1. 待付款
 * 状态2. 已付款（待发货）
 * 状态3. 已发货
 * 状态4. 订单完成
 *
 * 注意，在状态2，3中。可以开始退款，此时状态变为
 * 状态5. 退款中
 *
 * 退款完成之后。状态变成
 * 状态6. 订单关闭
 *
 * TODO 用户取消退款这一行为未考虑！
 *
 * 事件包括6种：(事件触发执行动作 handle 同样也是6种)
 * (事件1：用户支付）
 * (事件2：商家发货)
 * (事件3：用户确认收货)
 * (事件4 用户退款)
 * (事件5：商户确认退款)
 * (事件6：取消订单)
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-11 21:13
 * @projectName pig
 */
public class OrderContext {

    @Accessors(chain = true)
    @Setter(value = AccessLevel.PRIVATE)
    private OrderState state;

    private OrderContext() {
    }

    public static OrderContext create() {
        // 默认是待支付状态
        return create(OrderState.WAIT_FOR_PAY);
    }

    public static OrderContext create(String code) {
		return new OrderContext().setState(OrderState.getStateByCode(code));
    }

    String changeState(OrderState state) {
        this.state = state;
        return this.getStateCode();
    }

    public String getStateCode() {
        return this.state.getCode();
    }

    public String getStateDesc() {
        return this.state.getDesc();
    }

    /**
    * @Author wangk
    * @Description //触发事件
    * @Date 15:30 2019-10-12
    * @Param
    * @return
    **/
    public String trigger(OrderEvent orderEvent) {
        return this.state.handle(this, orderEvent);
    }
}
