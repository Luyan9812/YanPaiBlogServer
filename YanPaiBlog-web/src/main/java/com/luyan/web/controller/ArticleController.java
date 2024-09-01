package com.luyan.web.controller;

import com.luyan.entity.domain.Category;
import com.luyan.entity.domain.Tag;
import com.luyan.entity.dto.SaveArticleDto;
import com.luyan.entity.utils.R;
import com.luyan.service.ArticleService;
import com.luyan.service.CategoryService;
import com.luyan.service.TagService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private TagService tagService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ArticleService articleService;

    @GetMapping("categories")
    public R<List<Category>> getAllCategories() {
        return R.ok(categoryService.list());
    }

    @GetMapping("tags")
    public R<List<Tag>> getTagsByCategory(int categoryId) {
        log.info("getTagsByCategory - {}", categoryId);
        return R.ok(tagService.getTagsByCategory(categoryId));
    }

    @PostMapping("save")
    public R<Object> save(@RequestBody SaveArticleDto saveArticleDto) {
        log.info("save");
        int id = articleService.saveArticle(saveArticleDto);
        return R.okPairs(R.Pair.of("articleId", id));
    }

    @PostMapping("upload/{type}")
    public R<Object> upload(@PathVariable String type, MultipartFile file) {
        if (!type.equals("headers") && !type.equals("covers")) {
            return R.error(String.format("上传路径 /upload/%s 不存在", type));
        }
        String path = articleService.uploadFile(type, file);
        return R.okPairs(R.Pair.of("url", path));
    }

    @GetMapping("upload/delete")
    public R<Object> delete(String path) {
        log.info("delete - {}", path);
        articleService.delete(path);
        return R.ok(null);
    }
}
