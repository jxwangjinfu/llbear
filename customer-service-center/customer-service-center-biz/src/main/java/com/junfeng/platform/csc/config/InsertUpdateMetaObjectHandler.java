package com.junfeng.platform.csc.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.junfeng.platform.csc.util.ContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自动填充创建者和修改者的信息
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/29 15:25
 * @projectName pig
 */
@Component
@Slf4j
public class InsertUpdateMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject
     *            元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (StrUtil.isNotBlank(ContextHolderUtil.getUsername())) {
            this.setInsertFieldValByName("createBy", ContextHolderUtil.getUsername(), metaObject);
        }
        this.setInsertFieldValByName("delFlag","0",metaObject);
    }



    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject
     *            元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (StrUtil.isNotBlank(ContextHolderUtil.getUsername())) {
            this.setUpdateFieldValByName("updateBy", ContextHolderUtil.getUsername(), metaObject);
        }
    }
}
