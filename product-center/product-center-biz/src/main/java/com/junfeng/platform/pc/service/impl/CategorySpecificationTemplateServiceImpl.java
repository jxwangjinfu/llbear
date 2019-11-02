package com.junfeng.platform.pc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.CategorySpecificationTemplate;
import com.junfeng.platform.pc.mapper.CategorySpecificationTemplateMapper;
import com.junfeng.platform.pc.service.CategorySpecificationTemplateService;

/**
 * 类目关联模板
 *
 * @author lw
 * @date 2019-10-14 15:19:25
 */
@Service("categorySpecificationTemplateService")
public class CategorySpecificationTemplateServiceImpl extends ServiceImpl<CategorySpecificationTemplateMapper, CategorySpecificationTemplate> implements CategorySpecificationTemplateService {

	/**
	 * 类目关联模板简单分页查询
	 *
	 * @param categorySpecificationTemplate 类目关联模板
	 * @return
	 */
	@Override
	public IPage<CategorySpecificationTemplate> getCategorySpecificationTemplatePage(Page<CategorySpecificationTemplate> page, CategorySpecificationTemplate categorySpecificationTemplate) {
		return baseMapper.getCategorySpecificationTemplatePage(page, categorySpecificationTemplate);
	}
}
