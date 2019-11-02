package com.junfeng.platform.pc.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.junfeng.platform.pc.api.entity.*;
import com.junfeng.platform.pc.api.vo.ProductCreateVo;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.junfeng.platform.pc.service.*;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/15 11:04
 * @projectName pig
 */
@Service("productService")
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final SpuService spuService;
	private final SpuDetailService spuDetailService;
	private final SkuService skuService;
	private final SkuStockService skuStockService;
	private final SkuStockRecordService skuStockRecordService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean add(ProductCreateVo productVo) {
		String opertorName = SecurityUtils.getUser().getUsername();
		productVo.getSpu().setCreateBy(opertorName);
		productVo.getSpuDetail().setCreateBy(opertorName);

		spuService.save(productVo.getSpu());
		Long spuid = productVo.getSpu().getId();
		productVo.getSpuDetail().setSpuCode(spuid);
		spuDetailService.save(productVo.getSpuDetail());
		createSkubySkuVo(productVo);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(ProductCreateVo productVo) {
		String opertorName = SecurityUtils.getUser().getUsername();
		productVo.getSpu().setUpdateBy(opertorName);
		productVo.getSpuDetail().setUpdateBy(opertorName);

		spuService.updateById(productVo.getSpu());
		spuDetailService.updateById(productVo.getSpuDetail());

		List<Sku> skuList = new LambdaQueryChainWrapper<Sku>(skuService.getBaseMapper())
			.eq(Sku::getSpuCode,productVo.getSpu().getId())
			.eq(Sku::getDelFlag,"0").list();

		skuList.forEach(sku -> {
			skuService.removeById(sku.getId());
		});
		createSkubySkuVo(productVo);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long spuId) {
		spuService.removeById(spuId);
		spuDetailService.removeById(spuId);
		skuService.deleteBySpuId(spuId);
		return true;
	}

	@Override
	public IPage<Spu> getSpuPage(Page<Spu> page, Spu spu) {
		QueryWrapper<Spu> queryWrapper = new QueryWrapper<Spu>();
		if(spu.getClientId()!=null && "".equals(spu.getClientId())==false){
			queryWrapper.lambda().eq(Spu::getClientId,spu.getClientId());
		}
		if(spu.getBrandCode()!=null && "".equals(spu.getBrandCode())==false){
			queryWrapper.lambda().eq(Spu::getBrandCode,spu.getBrandCode());
		}
		if(spu.getCategoryCode()!=null && "".equals(spu.getCategoryCode())==false){
			queryWrapper.lambda().eq(Spu::getCategoryCode,spu.getCategoryCode());
		}
		if(spu.getTitle()!=null && "".equals(spu.getTitle())==false){
			queryWrapper.lambda().like(Spu::getTitle,spu.getTitle());
		}
		if(spu.getQuerySpecification() !=null && "".equals(spu.getQuerySpecification())==false){
			JSONObject jsonObject = JSON.parseObject(spu.getQuerySpecification());
			jsonObject.forEach((key,values)->{
				JSONArray jsonArray = JSON.parseArray(values.toString());
				queryWrapper.and(k->{
					jsonArray.forEach((value)->{
						k.eq("JSON_CONTAINS(query_specification,JSON_ARRAY('" + value + "'),'$." + key +
							"')","1");
						k.or();
					});
					return k;
				});

			});
			System.out.print(queryWrapper.lambda().getSqlSelect());
		}
		return spuService.page(page,queryWrapper);
	}

	@Override
	public ProductDetailVo getProductOnlySelfBySkuId(Long skuId) {
		Sku sku = skuService.getById(skuId);
		Assert.notNull(sku);

		return getProductBySpuId(sku.getSpuCode());

	}

	@Override
	public ProductDetailVo getProductBySpuId(Long spuId) {
		Spu spu = spuService.getById(spuId);
		Assert.notNull(spu);
		List<Sku> skuList = skuService.getSkuListBySpuId(spu.getId());

		//TODO 销量需要关联表查。
		skuList.forEach( sku -> sku.setStock(skuStockService.getById(sku.getId()).getStock()));

		SpuDetail spuDetail = spuDetailService.getById(spu.getId());
		return ProductDetailVo.create(spu, spuDetail, skuList);
	}

	@Override
	public boolean onProduct(Long spuId) {
		return spuService.on(spuId);
	}

	@Override
	public boolean offProduct(Long spuId) {
		return spuService.off(spuId);
	}

	/**
	 * 功能描述: 根据skuVo创建对应sku数据
	 * @author: lw
	 * @createTime: 2019/10/24 11:10
	 * @param productVo
	 * @return void
	 */
	private void createSkubySkuVo(ProductCreateVo productVo) {
		Long spuid = productVo.getSpu().getId();
		productVo.getSkuList().forEach(skuvo -> {
				Sku sku = new Sku();
				sku.setImages(skuvo.getImages());
				sku.setCreateBy(skuvo.getCreateBy());
				sku.setPrice(skuvo.getPrice());
				sku.setSkuCode(skuvo.getSkuCode());
				sku.setSpuCode(spuid);
				sku.setSpecialIndexs(skuvo.getSpecial_indexs());
				skuService.save(sku);

				Long skuId = sku.getId();

				SkuStock skuStock = new SkuStock();
				skuStock.setCreateBy(skuvo.getCreateBy());
				skuStock.setStock(skuvo.getStock());
				skuStock.setSkuCode(skuId);
				skuStockService.save(skuStock);

				SkuStockRecord skuStockRecord= new SkuStockRecord();
				skuStockRecord.setCount(skuvo.getStock());
				skuStockRecord.setCountAfter(skuvo.getStock());
				skuStockRecord.setCountBefore(0);
				skuStockRecord.setCreateBy(skuvo.getCreateBy());
				skuStockRecord.setSkuCode(skuId);
				skuStockRecord.setStockType("0");
				skuStockRecordService.save(skuStockRecord);
			}
		);
	}
}
