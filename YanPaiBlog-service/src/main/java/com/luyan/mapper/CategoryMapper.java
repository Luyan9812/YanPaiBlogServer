package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luyan.entity.domain.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getCategoriesHavingArticles();
}
