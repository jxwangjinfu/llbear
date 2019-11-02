package com.junfeng.platform.manager.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.junfeng.platform.manager.result.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试数据配置；
 *
 * @author daiysh
 * @date 2019-10-16 10:42
 **/
public class TestDataConfig {

	public static List<CarouselResult> carouseList() {
		List<CarouselResult> list = new ArrayList<>();

		list.add(new CarouselResult("/static/temp/banner3.jpg","rgb(203, 87, 60)"));
		list.add(new CarouselResult("/static/temp/banner2.jpg","rgb(205, 215, 218)"));
		list.add(new CarouselResult("/static/temp/banner4.jpg","rgb(183, 73, 69)"));

		return list;
	}

	public static List<OrderResult> orderResultList() {
//		List<OrderResult> list = new ArrayList<>();
//
//		OrderResult orderResult = new OrderResult();
//		orderResult.setTime("2019-04-06 11:37");
//		orderResult.setState("1");
//		orderResult.setOrderNo("3652813821448192");
//
//		List<OrderItemResult> goodsModelList = Lists.newArrayList();
//		OrderItemResult orderGoodsModel = new OrderGoodsModel();
//		orderGoodsModel.setTitle("回力女鞋高帮帆布鞋女学生韩版鞋子女2019潮鞋女鞋新款春季板鞋女");
//		orderGoodsModel.setPrice(6.0);
//		orderGoodsModel.setImage("https://img.alicdn.com/imgextra/i3/2128794607/TB2gzzoc41YBuNjy1zcXXbNcXXa_!!2128794607.jpg_430x430q90.jpg");
//		orderGoodsModel.setNumber("1");
//		orderGoodsModel.setAttr("白色-高帮 39");
//
//		goodsModelList.add(orderGoodsModel);
//		orderResult.setGoodsModelList(goodsModelList);
//
//		list.add(orderResult);

		return null;
	}

	public static List<CartResult> cartList() {
		List<CartResult> list = new ArrayList<>();
		CartResult cartVo = new CartResult();
		cartVo.setId(1L);
		cartVo.setAttr_val("春装款 L");
		cartVo.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553005139&di=3368549edf9eee769a9bcb3fbbed2504&imgtype=jpg&er=1&src=http%3A%2F%2Fimg002.hc360.cn%2Fy3%2FM01%2F5F%2FDB%2FwKhQh1T7iceEGRdWAAAAADQvqk8733.jpg");
		cartVo.setNumber(1);
		cartVo.setPrice(278.00f);
		cartVo.setTitle("OVBE 长袖风衣");
		cartVo.setStock(15);

		return list;
	}

	public static List<ShareResult> shareList() {
		List<ShareResult> list = new ArrayList<>();
		list.add(new ShareResult(1,"/static/temp/share_wechat.png","微信好友"));
		list.add(new ShareResult(2,"/static/temp/share_moment.png","朋友圈"));
		return list;
	}

	public static List<GoodsResult> goodsResultList() {
		List<GoodsResult> list = new ArrayList<>();
		String image1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553187020783&di=bac9dd78b36fd984502d404d231011c0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201609%2F26%2F20160926173213_s5adi.jpeg";
		String image2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4031878334,2682695508&fm=11&gp=0.jpg";
		String image3 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1620020012,789258862&fm=26&gp=0.jpg";
		String title1 = "古黛妃 短袖t恤女夏装2019新款韩版宽松";
		String title2 = "潘歌针织连衣裙";
		String title3 = "巧谷2019春夏季新品新款女装";
		list.add(new GoodsResult(1l,image1,title1,179,61));
		list.add(new GoodsResult(2l,image2,title2,78,16));
		list.add(new GoodsResult(3l,image3,title3,108.8,5));
		return list;
	}

//	public static void main(String[] args) {
//		goodsDetail();
//	}

	public static GoodsDetailResult goodsDetail() {
		GoodsDetailResult goodsDetailResult = new GoodsDetailResult();
		goodsDetailResult.setId(10l);
		goodsDetailResult.setTitle("daiysh 测试 title");
		goodsDetailResult.setPrice(488);
		goodsDetailResult.setDiscountPrice(341.6);
		goodsDetailResult.setSales(50);
		goodsDetailResult.setRepertory(30);
		goodsDetailResult.setPageView(100);

		List<String> imgList = Lists.newArrayList();
		imgList.add("http://img0.imgtn.bdimg.com/it/u=2396068252,4277062836&fm=26&gp=0.jpg");
		imgList.add("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1309/06/c4/25310541_1378426131583.jpg");
		imgList.add("http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1610/26/c4/28926240_1477451226577_mthumb.jpg");
		goodsDetailResult.setImgList(imgList);

		String specification = "[[\"红色\",\"黄色\",\"绿色\"],[\"S\",\"M\",\"L\",\"XL\"]]";
//		String specification = "{\"color\":[\"红色\",\"黄色\",\"绿色\"],\"size\":[\"S\",\"M\",\"L\",\"XL\"]}";
		String sku = "{\n" +
			"      \"0_0\": {\n" +
			"        \"price\": \"300\",\n" +
			"        \"id\": 1\n" +
			"      }}";
		String sku2 = "{\n" +
			"      \"0_1\": {\n" +
			"        \"price\": \"400\",\n" +
			"        \"id\": 1\n" +
			"      }}";
//		String sku2 = "{\"id\":1,\"index\":{\"color\":\"1\",\"size\":\"1\"},\"price\":\"200\"}";
//		String sku3 = "{\"id\":1,\"index\":{\"color\":\"2\",\"size\":\"2\"},\"price\":\"100\"}";
//		String sku4 = "{\"id\":1,\"index\":{\"color\":\"2\",\"size\":\"2\"},\"price\":\"100\"}";
//		String sku = "{\"id\":1,\"index\":{\"color\":\"0\",\"size\":\"0\"},\"price\":\"300\"}";
//		String sku2 = "{\"id\":1,\"index\":{\"color\":\"1\",\"size\":\"1\"},\"price\":\"200\"}";
//		String sku3 = "{\"id\":1,\"index\":{\"color\":\"2\",\"size\":\"2\"},\"price\":\"100\"}";
//		String sku4 = "{\"id\":1,\"index\":{\"color\":\"1\",\"size\":\"3\"},\"price\":\"400\"}";

		List<Object> skuList = Lists.newArrayList();

		skuList.add(JSON.parseObject(sku));
		skuList.add(JSON.parseObject(sku2));
//		skuList.add(JSON.parseObject(sku3));

		goodsDetailResult.setSpecificatin(JSON.parseArray(specification));
		goodsDetailResult.setSkuList(skuList);


//		List<SkuModel> specList = Lists.newArrayList();
//		List<SkuModel> specChildList = Lists.newArrayList();
//		specList.add(new SkuModel(1,0,"尺寸"));
//		specList.add(new SkuModel(2,0,"颜色"));
//
//		specChildList.add(new SkuModel(1,1,"XS"));
//		specChildList.add(new SkuModel(2,1,"S"));
//		specChildList.add(new SkuModel(3,1,"M"));
//		specChildList.add(new SkuModel(4,1,"L"));
//		specChildList.add(new SkuModel(5,1,"XL"));
//		specChildList.add(new SkuModel(6,1,"XXL"));
//		specChildList.add(new SkuModel(7,2,"白色"));
//		specChildList.add(new SkuModel(8,2,"红色"));
//		specChildList.add(new SkuModel(9,2,"黄色"));
//
//		goodsDetailResult.setSpecList(specList);
//		goodsDetailResult.setSpecChildList(specChildList);

		return goodsDetailResult;
	}

	public static List<CateResult> cateResultList() {
		List<CateResult> list = new ArrayList<>();
		list.add(new CateResult(1,0,"手机数码",null));
		list.add(new CateResult(2,0,"礼品鲜花",null));
		list.add(new CateResult(3,0,"男装女装",null));
		list.add(new CateResult(4,0,"母婴用品",null));
		list.add(new CateResult(5,1,"手机通讯",null));
		list.add(new CateResult(6,1,"运营商",null));
		list.add(new CateResult(8,5,"全面屏手机","/static/temp/cate2.jpg"));

		return list;
	}

}
