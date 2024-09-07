package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.ArticleDetail;

public interface ArticleDetailService extends IService<ArticleDetail> {
    // 根据文章 id 获取文章细节
    ArticleDetail getArticleDetail(int articleId);

    // 根据文章 id 删除文章
    void deleteArticle(int articleId);

    // 通过 articleId 更新文章详细信息
    void updateArticleDetail(ArticleDetail articleDetail);
}
