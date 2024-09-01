package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.domain.Tag;

import java.util.List;

public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    List<Tag> getTagByArticle(int articleId);
}
