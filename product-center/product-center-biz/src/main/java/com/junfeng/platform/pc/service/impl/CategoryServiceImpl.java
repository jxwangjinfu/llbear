package com.junfeng.platform.pc.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.Category;
import com.junfeng.platform.pc.mapper.CategoryMapper;
import com.junfeng.platform.pc.service.CategoryService;
import com.junfeng.platform.pc.service.CategorySpecificationTemplateService;

import lombok.AllArgsConstructor;

/**
 * 类目表
 *
 * @author lw
 * @date 2019-10-14 15:19:07
 */
@Service("categoryService")
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

	private  final CategorySpecificationTemplateService categorySpecificationTemplateService;

	/**
	 * 类目表简单分页查询
	 *
	 * @param category 类目表
	 * @return
	 */
	@Override
	public IPage<Category> getCategoryPage(Page<Category> page, Category category) {
		return baseMapper.getCategoryPage(page, category);
	}

	@Transactional(readOnly =  false)
	public boolean removeById(Serializable id) {
		categorySpecificationTemplateService.removeById(id);
		return super.removeById(id);
	}
}

