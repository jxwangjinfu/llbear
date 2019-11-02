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

package com.junfeng.platform.dc.api.feign.factory;

import com.junfeng.platform.dc.api.feign.RemoteDeviceService;
import com.junfeng.platform.dc.api.feign.fallback.RemoteDeviceServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Component
public class RemoteDeviceServiceFallbackFactory implements FallbackFactory<RemoteDeviceService> {

	@Override
	public RemoteDeviceService create(Throwable throwable) {
		RemoteDeviceServiceFallbackImpl remoteDeviceFallback = new RemoteDeviceServiceFallbackImpl();
		remoteDeviceFallback.setCause(throwable);
		return remoteDeviceFallback;
	}
}
