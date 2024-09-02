package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luyan.entity.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    IPage<Article> getReadArticles(IPage<Article> page, @Param("uid") int uid);
    IPage<Article> getCollectionArticles(IPage<Article> page, @Param("uid") int uid);
}
