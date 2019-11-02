/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.common.core.constant;

/**
 * @author lengleng
 * @date 2018年06月22日16:41:01 服务名称
 */
public interface ServiceNameConstants {
    /**
     * 认证服务的SERVICEID
     */
    String AUTH_SERVICE = "pig-auth";

    /**
     * UMPS模块
     */
    String UMPS_SERVICE = "pig-upms";

    /**
     * WECHAT_CENTER模块
     */
    String WECHAT_CENTER_SERVICE = "wechat-center";

    /**
     * MEMBER_CENTER模块
     */
    String MEMBER_CENTER_SERVICE = "member-center";

	/**
	 * DEVICE_CENTER模块
	 */
	String DEVICE_CENTER_SERVICE = "device-center";

    /**
     * 定时任务中心
     */
    String QUARTZ_CENTER_SERVICE = "quartz-center";

    /**
     * 运营中心
     */
    String OPERACTION_CENTER_SERVICE = "operation-center";

    /**
     * 支付中心
     */
    String PAYMENT_CENTER_SERVICE = "payment-center";

    /**
     * 交易中心
     */
    String TRADE_CENTER_SERVICE = "trade-center";

	/**
	 * 商品中心
	 */
    String PRODUCT_CENTER_SERVICE="product-center";

}
