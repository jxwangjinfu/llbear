package com.junfeng.platform.manager.controller;

import com.junfeng.platform.manager.config.TestDataConfig;
import com.junfeng.platform.manager.result.GoodsResult;
import com.junfeng.platform.manager.result.OrderResult;
import com.junfeng.platform.manager.service.GoodsService;
import com.junfeng.platform.manager.service.OrderService;
import com.junfeng.platform.manager.service.UserService;
import com.junfeng.platform.manager.vo.LoginVo;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台
 *
 * @author daiysh
 * @date 2019-10-14 13:50
 **/
@Api(tags = {"后端管理"})
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "会员注册", notes = "参数： 会员对象")
    @PostMapping("/member/register")
    public R<Boolean> register(@RequestBody MemberVo memberVo) {
        return userService.handleRegister(memberVo);
    }

    @ApiOperation(value = "会员登陆", notes = "参数： username，password")
    @PostMapping("/member/login")
    public R<Object> userInfo(@RequestBody LoginVo loginVo) {
        return userService.login(loginVo.getUsername(), loginVo.getPassword());
    }

    @ApiOperation(value = "绑定手机", notes = "参数： userId，mobile")
    @PostMapping("/member/bind/mobile/{userId}/{mobile}")
    public R<Boolean> bindMobile(@PathVariable("userId") Integer userId, @PathVariable("mobile") String mobile) {
        return userService.bindMobile(userId, mobile);
    }

    @ApiOperation(value = "绑定实名", notes = "参数： userId，identityId")
    @PostMapping("/member/bind/identity/{userId}/{identityId}")
    public R<Boolean> bindIdentityId(@PathVariable("userId") Integer userId,
            @PathVariable("identityId") String identityId) {
        return userService.bindIdentity(userId, identityId);
    }

    @ApiOperation(value = "轮播图", notes = "参数： 无")
    @GetMapping("/carousel")
    public R<Object> carouselList() {
        return R.ok(TestDataConfig.carouseList());
    }

    @ApiOperation(value = "商品列表", notes = "参数： 无")
    @GetMapping("/goods/list")
    public R<List<GoodsResult>> goodsList(Spu spu) {
        return goodsService.getGoodsList(spu);
    }

    @ApiOperation(value = "商品详情", notes = "参数： goodsId")
    @GetMapping("/goods/detail/{goodsId}")
    public R<ProductDetailVo> goodsDetail(@PathVariable("goodsId") Long goodsId) {
        return goodsService.getGoodsDetail(goodsId);
    }

    @ApiOperation(value = "购物车", notes = "参数： userId")
    @GetMapping("/cart/{userId}")
    public R<Object> cartList() {
        return R.ok(TestDataConfig.cartList());
    }

    @ApiOperation(value = "分享列表", notes = "参数： 无")
    @GetMapping("/share/list")
    public R<Object> shareList() {
        return R.ok(TestDataConfig.shareList());
    }

    @ApiOperation(value = "懒加载列表", notes = "参数： 无")
    @GetMapping("/lazyLoad/list")
    public R<Object> lazyLoadList() {
        List<Object> list = new ArrayList<>();

        return R.ok(list);
    }

    @ApiOperation(value = "创建订单", notes = "参数： request对象")
    @PostMapping("/order/add")
    public R<Order> createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @ApiOperation(value = "创建预支付单", notes = "参数： request对象")
    @GetMapping("/order/prepay/{orderNo}")
    public R<String> orderPrepay(@PathVariable String orderNo) {
        return orderService.orderPrepay(orderNo);
    }

    @ApiOperation(value = "订单列表", notes = "参数： userId,orderStatus,pageNum,pageSize")
    @GetMapping("/order/list/{userId}")
    public R<List<OrderResult>> orderList(@PathVariable("userId") int userId, @RequestParam(required = false) String orderState,
										  @RequestParam int pageNum, @RequestParam int pageSize) {
        return orderService.getOrderList(userId, orderState);
    }

    @ApiOperation(value = "类别列表", notes = "参数： 无")
    @GetMapping("/cate/list")
    public R<Object> cateList() {
        return R.ok(goodsService.getCateList());
    }
}
