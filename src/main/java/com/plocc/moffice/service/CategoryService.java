package com.plocc.moffice.service;

import java.util.List;

import com.plocc.moffice.entity.CategoryEntity;

public interface CategoryService {
    
    /**
     * 查询所有的产品分类
     * 
     * @return
     * @throws Exception
     */
    public List<CategoryEntity> findAll() throws Exception;
    
}
