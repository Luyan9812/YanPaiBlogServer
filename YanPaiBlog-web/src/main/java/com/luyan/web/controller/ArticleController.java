package com.luyan.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luyan.entity.dto.ArticleDto;
import com.luyan.entity.dto.SaveArticleDto;
import com.luyan.entity.utils.R;
import com.luyan.service.ArticleService;
import com.luyan.service.UserFootService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin({"http://47.96.95.64"})
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserFootService userFootService;


    @GetMapping("/list")
    public R<Page<ArticleDto>> getArticles(Integer categoryId, Integer currentPage) {
        log.info("getArticles - {}, {}", categoryId, currentPage);
        Page<ArticleDto> page = articleService.getArticlesByCategory(categoryId, currentPage);
        return R.ok(page);
    }

    @GetMapping("published")
    public R<Page<ArticleDto>> getPublishedArticles(Integer currentPage) {
        log.info("getPublishedArticles - {}", currentPage);
        return R.ok(articleService.getPublishedArticles(currentPage));
    }

    @GetMapping("foot")
    public R<Page<ArticleDto>> getReadArticles(Integer currentPage) {
        log.info("getReadArticles - {}", currentPage);
        return R.ok(articleService.getReadArticles(currentPage));
    }

    @GetMapping("collection")
    public R<Page<ArticleDto>> getCollectionArticles(Integer currentPage) {
        log.info("getCollectionArticles - {}", currentPage);
        return R.ok(articleService.getCollectionArticles(currentPage));
    }

    @GetMapping("byTag")
    public R<Page<ArticleDto>> getArticlesByTag(Integer tagId, Integer currentPage) {
        log.info("getArticlesByTag - {}, {}", tagId, currentPage);
        return R.ok(articleService.getArticlesByTag(tagId, currentPage));
    }

    @PostMapping("search")
    public R<Page<ArticleDto>> search(String title, Integer currentPage) {
        log.info("search - {}", title);
        return R.ok(articleService.search(title, currentPage));
    }

    @GetMapping("/details/{articleId}")
    public R<ArticleDto> getArticleById(@PathVariable Integer articleId) {
        log.info("getArticleById - {}", articleId);
        return R.ok(articleService.getArticleById(articleId));
    }

    @GetMapping("hot")
    public R<Object> getHotArticles() {
        log.info("getHotArticles");
        return R.ok(articleService.getHotArticles());
    }

    @GetMapping("/changePraiseState")
    public R<Object> changePraiseState(Integer articleId, Boolean praiseState) {
        log.info("changePraiseState - {}, {}", articleId, praiseState);
        userFootService.setArticlePraiseState(articleId, praiseState);
        return R.ok(null);
    }

    @GetMapping("/changeCollectionState")
    public R<Object> changeCollectionState(Integer articleId, Boolean collectionState) {
        log.info("changeCollectionState - {}, {}", articleId, collectionState);
        userFootService.setArticleCollectionState(articleId, collectionState);
        return R.ok(null);
    }

    @PostMapping("save")
    public R<Object> save(@RequestBody SaveArticleDto saveArticleDto) {
        log.info("save - {}", saveArticleDto);
        int id = articleService.saveArticle(saveArticleDto);
        return R.okPairs(R.Pair.of("articleId", id));
    }

    @DeleteMapping("{articleId}")
    public R<Object> delete(@PathVariable Integer articleId) {
        articleService.deleteArticle(articleId);
        return R.ok(null);
    }

    @PostMapping("upload/{type}")
    public R<Object> upload(@PathVariable String type, MultipartFile file) {
        log.info("upload - {}", type);
        if (!type.equals("headers") && !type.equals("covers")) {
            return R.error(String.format("上传路径 /upload/%s 不存在", type));
        }
        String path = articleService.uploadFile(type, file);
        return R.okPairs(R.Pair.of("url", path));
    }

    @GetMapping("upload/delete")
    public R<Object> delete(String path) {
        log.info("delete - {}", path);
        articleService.deleteFile(path);
        return R.ok(null);
    }

    @DeleteMapping("upload/deleteFiles")
    public R<Object> deleteFiles(@RequestBody List<String> paths) {
        log.info("deleteFiles - {}", paths);
        articleService.deleteFiles(paths);
        return R.ok(null);
    }

    @PutMapping("update")
    public R<Object> updateArticle(@RequestBody SaveArticleDto saveArticleDto) {
        log.info("updateArticle - {}", saveArticleDto);
        articleService.updateArticle(saveArticleDto);
        return R.ok(null);
    }
}
