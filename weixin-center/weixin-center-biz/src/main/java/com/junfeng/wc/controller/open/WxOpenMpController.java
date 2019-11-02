package com.junfeng.wc.controller.open;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发平台代公众号管理服务能力
 *
 * @author daiysh
 * @date 2019-09-26 15:06
 **/
@Api(tags = {"开发平台"})
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/openmp/{appid}")
public class WxOpenMpController {
}
