package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    // 获取 uid 读过的文章
    IPage<Article> getReadArticles(IPage<Article> page, @Param("uid") int uid);

    // 获取 uid 收藏的文章
    IPage<Article> getCollectionArticles(IPage<Article> page, @Param("uid") int uid);
}
