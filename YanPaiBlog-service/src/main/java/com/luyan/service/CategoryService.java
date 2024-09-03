package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    // 获取所有存在文章的分类
    List<Category> getCategoriesHavingArticles();
}
