package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.Article;
import com.luyan.entity.dto.SaveArticleDto;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService extends IService<Article> {
    // 获取 uid 对应用户发布的文章数
    long getPublishNum(int uid);

    // 文件用户头像或文章封面
    String uploadFile(String type, MultipartFile file);

    // 删除文件，传过来的是一个 URL
    void delete(String path);

    // 保存文章，返回文章 id
    int saveArticle(SaveArticleDto saveArticleDto);
}
