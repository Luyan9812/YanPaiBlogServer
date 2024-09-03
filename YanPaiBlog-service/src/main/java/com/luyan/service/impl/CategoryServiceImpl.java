package com.luyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Category;
import com.luyan.mapper.CategoryMapper;
import com.luyan.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoriesHavingArticles() {
        return categoryMapper.getCategoriesHavingArticles();
    }
}
