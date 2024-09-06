package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luyan.entity.domain.Article;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper extends BaseMapper<Article> {
    // 获取 uid 读过的文章
    IPage<Article> getReadArticles(IPage<Article> page, @Param("uid") int uid);

    // 获取 uid 收藏的文章
    IPage<Article> getCollectionArticles(IPage<Article> page, @Param("uid") int uid);

    // 根据标签 id 与页码查询文章列表
    Page<Article> getArticlesByTag(IPage<Article> page, @Param("tagId") Integer tagId);
}
