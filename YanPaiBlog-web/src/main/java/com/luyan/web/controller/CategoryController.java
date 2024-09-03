package com.luyan.web.controller;

import com.luyan.entity.domain.Category;
import com.luyan.entity.utils.R;
import com.luyan.service.CategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("all")
    public R<List<Category>> getAllCategories() {
        return R.ok(categoryService.list());
    }

    @GetMapping("haveArticles")
    public R<List<Category>> getCategoriesHavingArticles() {
        return R.ok(categoryService.getCategoriesHavingArticles());
    }
}
