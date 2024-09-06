package com.luyan.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.Article;
import com.luyan.entity.dto.ArticleDto;
import com.luyan.entity.dto.SaveArticleDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService extends IService<Article> {
    // 获取 uid 对应用户发布的文章数
    long getPublishNum(int uid);

    // 文件用户头像或文章封面
    String uploadFile(String type, MultipartFile file);

    // 删除文件，传过来的是一个 URL
    void fileDelete(String path);

    // 保存文章，返回文章 id
    int saveArticle(SaveArticleDto saveArticleDto);

    // 根据类别和页码查询文章列表
    Page<ArticleDto> getArticlesByCategory(int categoryId, int currentPage);

    // 查询当前用户发表的文章列表
    Page<ArticleDto> getPublishedArticles(int currentPage);

    // 查询当前用户阅读过的文章列表
    Page<ArticleDto> getReadArticles(int currentPage);

    // 查询当前用户收藏的文章列表
    Page<ArticleDto> getCollectionArticles(int currentPage);

    // 根据 id 查询文章的详细信息
    ArticleDto getArticleById(int articleId);

    // 获取前十的热门文章
    List<Object> getHotArticles();

    // 根据 id 删除文章
    void deleteArticle(int articleId);

    // 根据标签 id 与页码查询文章列表
    Page<ArticleDto> getArticlesByTag(Integer tagId, Integer currentPage);

    // 按照标题搜索文章
    Page<ArticleDto> search(String key, Integer currentPage);
}
