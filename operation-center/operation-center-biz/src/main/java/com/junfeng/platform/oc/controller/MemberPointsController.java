package com.junfeng.platform.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.junfeng.platform.oc.api.vo.PointsVO;
import com.junfeng.platform.oc.api.vo.SignInVO;
import com.junfeng.platform.oc.service.points.SignInPointsCalculateService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员积分接口类
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/17 14:02
 * @projectName pig
 */
@Api(tags = {"会员积分"})
@RestController
@RequestMapping("/points")
public class MemberPointsController {

    @Autowired
    private SignInPointsCalculateService signInPointsCalculateService;

    /**
     * 会员积分计算
     *
     * @author: wangjf
     * @createTime: 2019/10/18 14:10
     * @param pointsVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @Inner
    @PutMapping("/calculate")
    public R<Boolean> orderCalculate(@RequestBody PointsVO pointsVO) {
        // TODO
        return R.ok(true);
    }

    /**
     * 会员登录积分计算接口
     *
     * @author: wangjf
     * @createTime: 2019/10/23 14:03
     * @param signInVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Integer>
     */
    @Inner
    @PostMapping("/singin")
    public R<Integer> signInPoints(@RequestBody SignInVO signInVO) {
        Integer integer = signInPointsCalculateService.calculatePoint(signInVO);
        return R.ok(integer);
    }

    /**
     * 通过GETAWAY进行调用
     * @author: wangjf
     * @createTime: 2019/10/25 10:37
     * @param signInVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Integer>
     */
	@ApiOperation(value = "会员登录积分计算，外部调用", notes = "参数： signInVO")
	@PostMapping("/out/singin")
	public R<Integer> signInPointsOut(@RequestBody SignInVO signInVO) {
		Integer integer = signInPointsCalculateService.calculatePoint(signInVO);
		return R.ok(integer);
	}

}
