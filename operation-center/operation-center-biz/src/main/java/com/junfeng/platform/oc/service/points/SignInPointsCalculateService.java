package com.junfeng.platform.oc.service.points;

import cn.hutool.core.date.DateUtil;
import com.junfeng.platform.oc.api.vo.SignInConfigVO;
import com.junfeng.platform.oc.api.vo.SignInVO;
import com.junfeng.platform.oc.entity.PointsMemberFlow;
import com.junfeng.platform.oc.service.PointsMemberFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/21 15:04
 * @projectName pig
 */
@Service
public class SignInPointsCalculateService extends AbstractPointsCalculateService<SignInVO, SignInConfigVO> {

    @Autowired
    private PointsMemberFlowService pointsMemberFlowService;

    @Override
    protected Class<SignInConfigVO> getConfigClass() {
        return SignInConfigVO.class;
    }

    @Override
    protected Integer getPoints(SignInConfigVO config, SignInVO calculatePoints) {

        List<PointsMemberFlow> pointsMemberFlowList = pointsMemberFlowService.getPointsMemberFlowList(
                calculatePoints.getClientId(), calculatePoints.getMemberId(), DateUtil.today() + " 00:00:00",
                DateUtil.today() + " 23:59:59");

        if (!CollectionUtils.isEmpty(pointsMemberFlowList)) {
            return 0;
        }

        return 10;
    }

    @Override
    protected String getRemark(SignInVO calculatePoints) {
        return "登录获取积分，第" + calculatePoints.getCount() + "次登录";
    }

}
