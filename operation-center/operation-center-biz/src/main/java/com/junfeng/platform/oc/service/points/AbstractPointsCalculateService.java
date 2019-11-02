package com.junfeng.platform.oc.service.points;

import com.pig4cloud.pig.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;

import com.junfeng.platform.mc.api.feign.RemoteMemberService;
import com.junfeng.platform.mc.api.vo.MemberPointsFlowVo;
import com.junfeng.platform.oc.api.vo.CalculatePointsVO;
import com.junfeng.platform.oc.entity.PointsConfig;
import com.junfeng.platform.oc.entity.PointsMemberFlow;
import com.junfeng.platform.oc.service.PointsConfigService;
import com.junfeng.platform.oc.service.PointsMemberFlowService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;

import cn.hutool.json.JSONUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分计算抽象类
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/21 11:20
 * @projectName pig
 */
public abstract class AbstractPointsCalculateService<P extends CalculatePointsVO, T> {

    @Autowired
    private PointsConfigService pointsConfigService;
    @Autowired
    private RemoteMemberService remoteMemberService;
    @Autowired
    private PointsMemberFlowService pointsMemberFlowService;

    /**
     * 进行各种验证，验证通过后调用具体的业务获取积分
     *
     * @author: wangjf
     * @createTime: 2019/10/21 14:17
     * @param calculatePoints
     * @return com.junfeng.platform.oc.entity.PointsConfig
     */
    public Integer calculatePoint(P calculatePoints) {
        PointsConfig pointsConfig = pointsConfigService.getById(calculatePoints.getConfigId());
        if (pointsConfig == null) {
            return 0;
        }
        if (!pointsConfig.getClientId().equals(calculatePoints.getClientId())) {
            return 0;
        }

        T config = JSONUtil.toBean(pointsConfig.getPointsRule(), getConfigClass());
        Integer points = getPoints(config, calculatePoints);
        // 保存积分
        savePoints(points, calculatePoints.getClientId(), calculatePoints.getMemberId(), getRemark(calculatePoints), pointsConfig);
        return points;
    }

    // 字符串规则转换成JAVA类
    protected abstract Class<T> getConfigClass();

    // 计算积分
    protected abstract Integer getPoints(T config, P calculatePoints);

    // 积分备注
    protected abstract String getRemark(P calculatePoints);

	@Transactional(rollbackFor = Exception.class)
    protected void savePoints(Integer points, String clientId, Long memberId, String remark,
            PointsConfig pointsConfig) {

        if (points == 0) {
            return;
        }
        MemberPointsFlowVo memberPointsFlowVo = new MemberPointsFlowVo();
        memberPointsFlowVo.setMemberId(memberId);
        memberPointsFlowVo.setPoints(points);
        memberPointsFlowVo.setOperationType(pointsConfig.getOperationType() + "");
        memberPointsFlowVo.setPointsType(pointsConfig.getPointsType() + "");
        memberPointsFlowVo.setRemark(remark);
        // 会员加积分,调用会员中心的远程服务
		R<Boolean> booleanR = remoteMemberService.addPointsflow(memberPointsFlowVo, SecurityConstants.FROM_IN);
		if(booleanR.getCode()!=0){
			return;
		}

		//会员积分流水
        PointsMemberFlow pointsMemberFlow = new PointsMemberFlow();
        pointsMemberFlow.setClientId(clientId);
        pointsMemberFlow.setMcMemberId(memberId);
        pointsMemberFlow.setPoints(points);
        pointsMemberFlow.setOcPointsConfigId(pointsConfig.getId());
        pointsMemberFlowService.save(pointsMemberFlow);
    }

}
