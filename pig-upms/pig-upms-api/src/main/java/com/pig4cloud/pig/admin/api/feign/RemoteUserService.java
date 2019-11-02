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

package com.pig4cloud.pig.admin.api.feign;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr);

	/**
	 * 创建用户
	 *
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-11  13:49
	 **/
	@PostMapping("/user/add")
	R<Integer> createUser(@RequestBody SysUser sysUser,@RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 绑定用户信息
	 *
	 * @param sysUser
	 * @param from
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-24  16:29
	 **/
	@PostMapping("/user/update")
	R<Boolean> bindUser(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 增加一条用户角色记录
	 *
	 * @param userId
	 * @param from
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Integer>
	 * @author: daiysh
	 * @createTime: 2019-10-21  11:35
	 **/
	@GetMapping("/userrole/add/{userId}")
	R<Boolean> addUserRole(@PathVariable("userId") Integer userId, @RequestHeader(SecurityConstants.FROM) String from);
}
